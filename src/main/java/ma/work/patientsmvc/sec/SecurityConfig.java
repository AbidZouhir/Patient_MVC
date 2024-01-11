package ma.work.patientsmvc.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
        UserDetails user1= User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build();
        UserDetails user2= User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build();
        UserDetails admin= User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build();
        return new InMemoryUserDetailsManager(user1,user2,admin);
    }
    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin((login) -> login.loginPage("/login").permitAll());
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/user/**").hasRole("USER"));
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/admin/**").hasRole("ADMIN"));
        http.authorizeHttpRequests((authorize)-> authorize.anyRequest().authenticated());
        http.exceptionHandling((exception)-> exception.accessDeniedPage("/notAuthorized"));
        return http.build();
    }

}
