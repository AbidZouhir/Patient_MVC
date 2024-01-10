package ma.work.patientsmvc.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
        UserDetails admin= User.withUsername("zouhir").password("1234").roles("ADMIN").build();
        UserDetails user= User.withUsername("john").password("1234").roles("USER").build();
        return new InMemoryUserDetailsManager(admin,user);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests((authorize)-> authorize.anyRequest().authenticated());
        return http.build();
    }

}
