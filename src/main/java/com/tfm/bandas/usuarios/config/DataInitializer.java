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
import java.util.*;

@Configuration
public class DataInitializer {

//    @Bean
//    public CommandLineRunner initData(UserRepository userRepo,
//                                      RoleRepository roleRepo,
//                                      InstrumentRepository instrumentRepo) {
//        // Inyectar el codificador de contraseñas
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        return args -> {
//
//            // Crear roles si no existen
//            Role adminRole = roleRepo.findByName("ADMIN")
//                    .orElseGet(() -> roleRepo.save(new Role("ADMIN", "System administrator")));
//            Role musicianRole = roleRepo.findByName("MUSICIAN")
//                    .orElseGet(() -> roleRepo.save(new Role("MUSICIAN", "Standard musician")));
//
//            // Crear instrumentos
//            Instrument trumpet1 = instrumentRepo
//                    .findByInstrumentNameAndVoice("Trumpet", "1")
//                    .orElseGet(() -> instrumentRepo.save(new Instrument("Trumpet", "1")));
//
//            Instrument trumpet2 = instrumentRepo
//                    .findByInstrumentNameAndVoice("Trumpet", "2")
//                    .orElseGet(() -> instrumentRepo.save(new Instrument("Trumpet", "2")));
//
//            Instrument clarinet1 = instrumentRepo
//                    .findByInstrumentNameAndVoice("Clarinet", "1")
//                    .orElseGet(() -> instrumentRepo.save(new Instrument("Clarinet", "1")));
//
//            // Crear usuario de prueba
//            if (!userRepo.existsByEmail("test@bandas.com")) {
//                User testUser = new User();
//                testUser.setFirstName("UsuarioTest");
//                testUser.setLastName("ApellidoTest");
//                testUser.setSecondLastName("Apellido2Test");
//                testUser.setEmail("test@bandas.com");
//                testUser.setPasswordHash(passwordEncoder.encode("123456"));
//                testUser.setBirthDate(LocalDate.of(1990, 1, 1));
//                testUser.setBandJoinDate(LocalDate.of(2010, 9, 1));
//                testUser.setSystemSignupDate(LocalDate.now());
//                testUser.setActive(true);
//                testUser.setPhone("600123456");
//                testUser.setNotes("Usuario de prueba inicial");
//                testUser.setProfilePictureUrl(null);
//                testUser.setRoles(Set.of(musicianRole));
//                testUser.setInstruments(Set.of(clarinet1));
//
//                userRepo.save(testUser);
//            }
//
//            // Crear más usuarios de prueba si es necesario
//            if (!userRepo.existsByEmail("ismael@bandas.com")) {
//                User ismaelUser = new User();
//                ismaelUser.setFirstName("Ismael");
//                ismaelUser.setLastName("Blazquez");
//                ismaelUser.setSecondLastName("Carabias");
//                ismaelUser.setEmail("ismael@bandas.com");
//                ismaelUser.setPasswordHash(passwordEncoder.encode("123456"));
//                ismaelUser.setBirthDate(LocalDate.of(1996, 5, 4));
//                ismaelUser.setBandJoinDate(LocalDate.of(2008, 11, 1));
//                ismaelUser.setSystemSignupDate(LocalDate.now().minusDays(1));
//                ismaelUser.setActive(true);
//                ismaelUser.setPhone("6665544321");
////                ismaelUser.setNotes("Usuario de prueba inicial");
//                ismaelUser.setProfilePictureUrl(null);
//                ismaelUser.setRoles(Set.of(adminRole, musicianRole));
//                ismaelUser.setInstruments(Set.of(trumpet1, trumpet2));
//
//                userRepo.save(ismaelUser);
//            }
//        };
//    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepo,
                                      RoleRepository roleRepo,
                                      InstrumentRepository instrumentRepo,
                                      BCryptPasswordEncoder passwordEncoder) {
        return args -> {

            // Crear roles
            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ADMIN", "Administrador del sistema")));
            Role musicianRole = roleRepo.findByName("MUSICIAN")
                    .orElseGet(() -> roleRepo.save(new Role("MUSICIAN", "Usuario músico")));

            // Crear instrumentos
            String[][] instrumentData = {
                    {"Trompeta", "1"}, {"Trompeta", "2"}, {"Trompeta", "3"}, {"Clarinete", "1"}, {"Clarinete", "2"},
                    {"Flauta", "1"}, {"Flauta", "Principal"}, {"Trombón", "1"}, {"Trombón", "2"},
                    {"Saxofón", "1"}, {"Saxofón", "2"}, {"Tuba", "1"},
                    {"Oboe", "1"}, {"Trompa", "1"}, {"Trompa", "2"}, {"Fagot", "1"}
            };

            Instrument instrumentDirector = new Instrument("Director", "Principal");
            instrumentRepo.save(instrumentDirector); // Guardar instrumento ficticio de director

            List<Instrument> instruments = new ArrayList<>();
            for (String[] data : instrumentData) {
                instruments.add(instrumentRepo.save(new Instrument(data[0], data[1])));
            }
            
            // Crear usuario administrador inicial
            if (!userRepo.existsByEmail("admin@bandas.com")) {
                User adminUser = new User();
                adminUser.setFirstName("Admin");
                adminUser.setLastName("System");
                adminUser.setSecondLastName("User");
                adminUser.setEmail("admin@bandas.com");
                adminUser.setPasswordHash(passwordEncoder.encode("admin123"));
                adminUser.setActive(true);
                adminUser.setBandJoinDate(LocalDate.now().minusYears(15));
                adminUser.setSystemSignupDate(LocalDate.now());
                adminUser.setPhone("600000000");
                adminUser.setNotes("Usuario administrador inicial");
                adminUser.setProfilePictureUrl(null); // URL de imagen de perfil puede ser null
                adminUser.setBirthDate(LocalDate.of(1980, 1, 1));
                adminUser.setRoles(Set.of(adminRole, musicianRole));
                adminUser.setInstruments(new HashSet<>(List.of(instrumentDirector))); // Asignar un instrumento ficticio de director
                userRepo.save(adminUser);
            }

            // Crear usuarios
            List<String> nombres = List.of("Alejandro", "María", "Lucía", "Javier", "Carmen", "Miguel",
                    "Sofía", "Daniel", "Paula", "David", "Laura", "Manuel", "Sara", "Antonio", "Elena", "Pablo",
                    "Marta", "Ismael", "Andrea", "Hugo");
            List<String> apellidos = List.of("García", "Blázquez", "González", "Rodríguez", "López",
                    "Martínez", "Sánchez", "Pérez", "Gómez", "Martín", "Jiménez", "Ruiz", "Hernández", "Díaz",
                    "Moreno", "Muñoz", "Álvarez", "Romero", "Alonso", "Gutiérrez");
            Random rand = new Random();

            for (int i = 1; i <= 20; i++) {
                String firstName = nombres.get(rand.nextInt(nombres.size()));
                String lastName = apellidos.get(rand.nextInt(apellidos.size()));
                String secondLastName = apellidos.get(rand.nextInt(apellidos.size()));

                String email = String.format("%s.%s.%d@bandas.com", firstName.toLowerCase(), lastName.toLowerCase(), (rand.nextInt(25)));
                // reemplazar letras acentuadas por letras sin acento
                email = email.replace("á", "a").replace("é", "e").replace("í", "i")
                        .replace("ó", "o").replace("ú", "u")
                        .replace("ñ", "n").replace("ü", "u");

                if (userRepo.existsByEmail(email)) continue;

                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setSecondLastName(secondLastName);
                user.setEmail(email);
                user.setPasswordHash(passwordEncoder.encode("123456"));
                user.setActive(true);
                user.setBandJoinDate(LocalDate.now().minusYears(rand.nextInt(10)).minusDays(rand.nextInt(50)));
                user.setSystemSignupDate(LocalDate.now().minusDays(rand.nextInt(50)));
                user.setPhone(String.format("%d%d%d%d%d%d%d%d", rand.nextInt(10), rand.nextInt(10), rand.nextInt(10),
                        rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10)));
                user.setNotes("Usuario de prueba generado automáticamente");
                user.setProfilePictureUrl(null); // URL de imagen de perfil puede ser null
                user.setBirthDate(LocalDate.of(rand.nextInt(30) + 1980, rand.nextInt(12) + 1, rand.nextInt(28) + 1));

                // Roles aleatorios
                Set<Role> userRoles = new HashSet<>();
                if (i % 5 == 0) userRoles.add(adminRole);  // Cada 5º usuario es admin también
                userRoles.add(musicianRole);
                user.setRoles(userRoles);

                // Instrumentos aleatorios
                Set<Instrument> userInstruments = new HashSet<>();
                int howMany = rand.nextInt(3) + 1;
                for (int j = 0; j < howMany; j++) {
                    userInstruments.add(instruments.get(rand.nextInt(instruments.size())));
                }
                user.setInstruments(userInstruments);

                userRepo.save(user);
            }

        };
    }

}
