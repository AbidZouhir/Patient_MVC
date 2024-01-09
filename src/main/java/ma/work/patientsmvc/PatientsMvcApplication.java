package ma.work.patientsmvc;

import ma.work.patientsmvc.entities.Patient;
import ma.work.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
