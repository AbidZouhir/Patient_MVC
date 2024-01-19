package ma.work.patientsmvc.sec.repo;

import ma.work.patientsmvc.sec.entities.AppRole;
import ma.work.patientsmvc.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
