package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.dto.mapper.UserMapper;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.entity.Role;
import com.tfm.bandas.usuarios.model.entity.User;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.repository.RoleRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import com.tfm.bandas.usuarios.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
    public List<UserDTO> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable)
                .stream()
                .map(userMapper::toDTO)
                .toList();
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
        setUserAttributes(dto, user);
        user.setActive(true);
        return userMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserCreateDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        setUserAttributes(dto, user);
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
        Set<Instrument> instruments = new HashSet<>(instrumentRepo.findAllById(instrumentIds));
        user.setInstruments(instruments);
        return userMapper.toDTO(userRepo.save(user));
    }

    private void setUserAttributes(UserCreateDTO dto, User user) {
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setSecondLastName(dto.secondLastName());
        user.setEmail(dto.email());
        user.setPasswordHash(passwordEncoder.encode(dto.password()));

        Set<Role> roles = new HashSet<>(roleRepo.findAllById(dto.roleIds()));
        Set<Instrument> instruments = new HashSet<>(instrumentRepo.findAllById(dto.instrumentIds()));

        user.setRoles(roles);
        user.setInstruments(instruments);
    }
}
