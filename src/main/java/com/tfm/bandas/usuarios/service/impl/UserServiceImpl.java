package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.dto.UserUpdateDTO;
import com.tfm.bandas.usuarios.dto.mapper.UserMapper;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.entity.Role;
import com.tfm.bandas.usuarios.model.entity.User;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.repository.RoleRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import com.tfm.bandas.usuarios.service.UserService;
import com.tfm.bandas.usuarios.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final InstrumentRepository instrumentRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo,
                           InstrumentRepository instrumentRepo, BCryptPasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.instrumentRepo = instrumentRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(userMapper::toDTO);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepo.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public UserDTO createUser(UserCreateDTO dto) {
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setSecondLastName(dto.secondLastName());
        user.setEmail(dto.email());
        user.setPasswordHash(passwordEncoder.encode(dto.password()));
        Set<Role> roles = new HashSet<>(roleRepo.findAllById(dto.roleIds()));
        Set<Instrument> instruments = null;
        if (dto.instrumentIds() != null && !dto.instrumentIds().isEmpty()) {
            instruments = new HashSet<>(instrumentRepo.findAllById(dto.instrumentIds()));
        } else {
            instruments = new HashSet<>();
        }
        user.setRoles(roles);
        user.setInstruments(instruments);
        user.setActive(true);
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        if (dto.firstName() != null && !dto.firstName().isEmpty()) {
            user.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null && !dto.lastName().isEmpty()) {
            user.setLastName(dto.lastName());
        }
        user.setSecondLastName(dto.secondLastName());

        if(userRepo.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already registered: " + dto.email());
        }
        if (dto.email() != null && !dto.email().isEmpty()) {
            user.setEmail(dto.email());
        }
        if (dto.password() != null && !dto.password().isEmpty() && !Constants.NOT_ALLOWED_PASSWORDS.contains(dto.password().trim())) {
            user.setPasswordHash(passwordEncoder.encode(dto.password()));
        }
        if (dto.roleIds() != null && !dto.roleIds().isEmpty()) {
            Set<Role> roles = new HashSet<>(roleRepo.findAllById(dto.roleIds()));
            user.setRoles(roles);
        }
        Set<Instrument> instruments;
        if (dto.instrumentIds() != null) {
            instruments = new HashSet<>(instrumentRepo.findAllById(dto.instrumentIds()));
        } else {
            instruments = new HashSet<>();
        }
        user.setInstruments(instruments);
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepo.delete(user);
    }

    @Override
    public void disableUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setActive(false);
        userRepo.save(user);
    }

    @Override
    public void enableUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setActive(true);
        userRepo.save(user);
    }

    @Override
    public UserDTO assignRoles(Long userId, Set<Long> roleIds){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Set<Role> roles = new HashSet<>(roleRepo.findAllById(roleIds));
        user.setRoles(roles);
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserDTO assignInstruments(Long userId, Set<Long> instrumentIds) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Set<Instrument> instruments = null;
        if (instrumentIds != null && !instrumentIds.isEmpty()) {
            instruments = new HashSet<>(instrumentRepo.findAllById(instrumentIds));
        } else {
            instruments = new HashSet<>();
        }
        user.setInstruments(instruments);
        return userMapper.toDTO(userRepo.save(user));
    }

    private void setUserAttributes(UserCreateDTO dto, User user) {

    }
}
