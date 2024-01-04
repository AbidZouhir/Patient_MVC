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
	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			patientRepository.save(new Patient(null,"Mohamed",new Date(),false,13));
			patientRepository.save(new Patient(null,"Anass",new Date(),false,20));
			patientRepository.save(new Patient(null,"Khalid",new Date(),true,58));
			patientRepository.save(new Patient(null,"Rida",new Date(),false,1));
			patientRepository.save(new Patient(null,"Fatima",new Date(),false,67));
			patientRepository.findAll().forEach(p -> {
				System.out.println(p.getNom());
			});


		};
	}

}
