package com.tfm.bandas.usuarios.auth;

import com.tfm.bandas.usuarios.model.entity.Role;
import com.tfm.bandas.usuarios.model.entity.User;
import com.tfm.bandas.usuarios.model.repository.RoleRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepo, RoleRepository roleRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        if (userRepo.existsByEmail(request.email)) {
            System.out.println("Email already registered: " + request.email);
            return "Email already registered";
        }

        System.out.println("Registering user: " + request.firstName + " " + request.lastName);

        Role musician = roleRepo.findByName("MUSICIAN")
                .orElseThrow(() -> new RuntimeException("MUSICIAN role not found"));

        User user = new User();
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setSecondLastName(request.secondLastName);
        user.setEmail(request.email);
        user.setPasswordHash(passwordEncoder.encode(request.password));
        user.setActive(true);
        user.setBandJoinDate(LocalDate.now());
        user.setSystemSignupDate(LocalDate.now());
        user.setRoles(Set.of(musician));

        userRepo.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        System.out.println("Login attempt for email: " + request.email);
        User user = userRepo.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

}
