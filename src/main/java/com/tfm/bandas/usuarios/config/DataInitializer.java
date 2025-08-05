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

import java.time.LocalDate;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepo,
                                      RoleRepository roleRepo,
                                      InstrumentRepository instrumentRepo) {
        return args -> {

            // Crear roles si no existen
            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ADMIN", "System administrator")));
            Role musicianRole = roleRepo.findByName("MUSICIAN")
                    .orElseGet(() -> roleRepo.save(new Role("MUSICIAN", "Standard musician")));

            // Crear instrumentos
            Instrument trumpet1 = instrumentRepo
                    .findByNameAndVoice("Trumpet", "1")
                    .orElseGet(() -> instrumentRepo.save(new Instrument("Trumpet", "1")));

            Instrument trumpet2 = instrumentRepo
                    .findByNameAndVoice("Trumpet", "2")
                    .orElseGet(() -> instrumentRepo.save(new Instrument("Trumpet", "2")));

            Instrument clarinet1 = instrumentRepo
                    .findByNameAndVoice("Clarinet", "1")
                    .orElseGet(() -> instrumentRepo.save(new Instrument("Clarinet", "1")));

            // Crear usuario de prueba
            if (!userRepo.existsByEmail("test@bandas.com")) {
                User testUser = new User();
                testUser.setFirstName("Ismael");
                testUser.setLastName("Blázquez");
                testUser.setSecondLastName("Carabias");
                testUser.setEmail("test@bandas.com");
                testUser.setPasswordHash("$2a$10$e.jvEjUaeR4Jc9Q2M8OJo.RkWfmr02v4v7C9AAGz7okd0KmVaTI.C"); // "123456" codificada con BCrypt
                testUser.setBirthDate(LocalDate.of(1990, 1, 1));
                testUser.setBandJoinDate(LocalDate.of(2010, 9, 1));
                testUser.setSystemSignupDate(LocalDate.now());
                testUser.setActive(true);
                testUser.setPhone("600123456");
                testUser.setNotes("Usuario de prueba inicial");
                testUser.setProfilePictureUrl(null);
                testUser.setRoles(Set.of(adminRole, musicianRole));
                testUser.setInstruments(Set.of(trumpet1, clarinet1));

                userRepo.save(testUser);
            }
        };
    }
}
