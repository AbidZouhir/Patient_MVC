package ma.work.patientsmvc;

import ma.work.patientsmvc.entities.Patient;
import ma.work.patientsmvc.repositories.PatientRepository;
import ma.work.patientsmvc.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}
	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			patientRepository.save(new Patient(null,"Mohamed",new Date(),false,130));
			patientRepository.save(new Patient(null,"Anass",new Date(),false,200));
			patientRepository.save(new Patient(null,"Khalid",new Date(),true,580));
			patientRepository.save(new Patient(null,"Ridaa",new Date(),false,165));
			patientRepository.save(new Patient(null,"Fatima",new Date(),false,607));
			patientRepository.findAll().forEach(p -> {
				System.out.println(p.getNom());
			});


		};
	}
	//@Bean
	 CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		 PasswordEncoder passwordEncoder=passwordEncoder();
		return args -> {
			UserDetails us11=jdbcUserDetailsManager.loadUserByUsername("user11");
			if(us11==null) jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build());
			UserDetails us22=jdbcUserDetailsManager.loadUserByUsername("user22");
			if(us22==null) jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build());
			UserDetails ad2=jdbcUserDetailsManager.loadUserByUsername("admin2");
			if(ad2==null) jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("ADMIN","USER").build());
        };
	}
	//@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		return args -> {
			accountService.addNewRole("USER");
			accountService.addNewRole("ADMIN");
			accountService.addNewUser("user1","1234","user1@hotmail.com","1234");
			accountService.addNewUser("user2","1234","user2@hotmail.com","1234");
			accountService.addNewUser("admin1","1234","admin1@hotmail.com","1234");

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("admin1","ADMIN");
			accountService.addRoleToUser("admin1","USER");
		};
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
