package com.tfm.bandas.usuarios.config;

import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.entity.Role;
import com.tfm.bandas.usuarios.model.entity.User;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.repository.RoleRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepo,
                                      RoleRepository roleRepo,
                                      InstrumentRepository instrumentRepo) {
        // Inyectar el codificador de contraseñas
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return args -> {

            // Crear roles si no existen
            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ADMIN", "System administrator")));
            Role musicianRole = roleRepo.findByName("MUSICIAN")
                    .orElseGet(() -> roleRepo.save(new Role("MUSICIAN", "Standard musician")));

            // Crear instrumentos
            Instrument trumpet1 = instrumentRepo
                    .findByInstrumentNameAndVoice("Trumpet", "1")
                    .orElseGet(() -> instrumentRepo.save(new Instrument("Trumpet", "1")));

            Instrument trumpet2 = instrumentRepo
                    .findByInstrumentNameAndVoice("Trumpet", "2")
                    .orElseGet(() -> instrumentRepo.save(new Instrument("Trumpet", "2")));

            Instrument clarinet1 = instrumentRepo
                    .findByInstrumentNameAndVoice("Clarinet", "1")
                    .orElseGet(() -> instrumentRepo.save(new Instrument("Clarinet", "1")));

            // Crear usuario de prueba
            if (!userRepo.existsByEmail("test@bandas.com")) {
                User testUser = new User();
                testUser.setFirstName("UsuarioTest");
                testUser.setLastName("ApellidoTest");
                testUser.setSecondLastName("Apellido2Test");
                testUser.setEmail("test@bandas.com");
                testUser.setPasswordHash(passwordEncoder.encode("123456"));
                testUser.setBirthDate(LocalDate.of(1990, 1, 1));
                testUser.setBandJoinDate(LocalDate.of(2010, 9, 1));
                testUser.setSystemSignupDate(LocalDate.now());
                testUser.setActive(true);
                testUser.setPhone("600123456");
                testUser.setNotes("Usuario de prueba inicial");
                testUser.setProfilePictureUrl(null);
                testUser.setRoles(Set.of(musicianRole));
                testUser.setInstruments(Set.of(clarinet1));

                userRepo.save(testUser);
            }

            // Crear más usuarios de prueba si es necesario
            if (!userRepo.existsByEmail("ismael@bandas.com")) {
                User ismaelUser = new User();
                ismaelUser.setFirstName("Ismael");
                ismaelUser.setLastName("Blazquez");
                ismaelUser.setSecondLastName("Carabias");
                ismaelUser.setEmail("ismael@bandas.com");
                ismaelUser.setPasswordHash(passwordEncoder.encode("123456"));
                ismaelUser.setBirthDate(LocalDate.of(1996, 5, 4));
                ismaelUser.setBandJoinDate(LocalDate.of(2008, 11, 1));
                ismaelUser.setSystemSignupDate(LocalDate.now().minusDays(1));
                ismaelUser.setActive(true);
                ismaelUser.setPhone("6665544321");
//                ismaelUser.setNotes("Usuario de prueba inicial");
                ismaelUser.setProfilePictureUrl(null);
                ismaelUser.setRoles(Set.of(adminRole, musicianRole));
                ismaelUser.setInstruments(Set.of(trumpet1, trumpet2));

                userRepo.save(ismaelUser);
            }
        };
    }

}
