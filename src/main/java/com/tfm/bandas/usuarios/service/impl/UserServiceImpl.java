package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.dto.mapper.UserMapper;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.entity.UserProfile;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import com.tfm.bandas.usuarios.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final InstrumentRepository instrumentRepo;
    private final UserMapper userMapper;

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
    public UserDTO getUserByIamId(String iamId) {
        return userRepo.findByIamId(iamId)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with IAM id: " + iamId));
    }

    @Override
    public UserDTO createUser(UserCreateDTO dto) {
        if(userRepo.findByIamId(dto.iamId()).isPresent()) {
            throw new RuntimeException("User already registered with IAM id: " + dto.iamId());
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setIamId(dto.iamId());
        userProfile.setSystemSignupDate(dto.systemSignupDate());
        userProfile.setActive(true);

        setMainUserInfo(dto, userProfile);

        Set<Instrument> instruments;
        if (dto.instrumentIds() != null && !dto.instrumentIds().isEmpty()) {
            instruments = new HashSet<>(instrumentRepo.findAllById(dto.instrumentIds()));
        } else {
            instruments = new HashSet<>();
        }
        userProfile.setInstruments(instruments);

        return userMapper.toDTO(userRepo.save(userProfile));
    }

    @Override
    public UserDTO updateUser(Long id, UserCreateDTO dto) {
        UserProfile userProfile = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // NO se actualiza iamId → es inmutable
        // Email y nombres: se supone que ya han sido cambiados en el IdP y se sincronizan aquí
        setMainUserInfo(dto, userProfile);

        Set<Instrument> instruments;
        if (dto.instrumentIds() != null) {
            instruments = new HashSet<>(instrumentRepo.findAllById(dto.instrumentIds()));
        } else {
            instruments = new HashSet<>();
        }
        userProfile.setInstruments(instruments);
        return userMapper.toDTO(userRepo.save(userProfile));
    }

    private static void setMainUserInfo(UserCreateDTO dto, UserProfile userProfile) {
        userProfile.setFirstName(dto.firstName());
        userProfile.setLastName(dto.lastName());
        userProfile.setSecondLastName(dto.secondLastName());
        userProfile.setEmail(dto.email());
        userProfile.setPhone(dto.phone());
        userProfile.setNotes(dto.notes());
        userProfile.setProfilePictureUrl(dto.profilePictureUrl());
        userProfile.setBirthDate(dto.birthDate());
        userProfile.setBandJoinDate(dto.bandJoinDate());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public void disableUser(Long id) {
        UserProfile user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        user.setActive(false);
        userRepo.save(user);
    }

    @Override
    public void enableUser(Long id) {
        UserProfile user = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        user.setActive(true);
        userRepo.save(user);
    }

    @Override
    public UserDTO assignInstruments(Long userId, Set<Long> instrumentIds) {
        UserProfile userProfile = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Set<Instrument> instruments = null;
        if (instrumentIds != null && !instrumentIds.isEmpty()) {
            instruments = new HashSet<>(instrumentRepo.findAllById(instrumentIds));
        } else {
            instruments = new HashSet<>();
        }
        userProfile.setInstruments(instruments);
        return userMapper.toDTO(userRepo.save(userProfile));
    }

}
