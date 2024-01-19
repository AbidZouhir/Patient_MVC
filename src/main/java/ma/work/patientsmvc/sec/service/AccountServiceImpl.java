package ma.work.patientsmvc.sec.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.work.patientsmvc.sec.entities.AppRole;
import ma.work.patientsmvc.sec.entities.AppUser;
import ma.work.patientsmvc.sec.repo.AppRoleRepository;
import ma.work.patientsmvc.sec.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional @AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;

    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser user=appUserRepository.findByUsername(username);
        if (user != null) throw new RuntimeException("This user already exist");
        if (!password.equals(confirmPassword)) throw new RuntimeException("Passwords not match");
        user=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedAppUser = appUserRepository.save(user);
        return savedAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if (appRole != null) throw new RuntimeException("This role already exist");
        appRole=appRoleRepository.save(AppRole.builder()
                .role(role)
                .build());
        return  appRole;
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser appUser=appUserRepository.findByUsername(username);
        return appUser;
    }
}
