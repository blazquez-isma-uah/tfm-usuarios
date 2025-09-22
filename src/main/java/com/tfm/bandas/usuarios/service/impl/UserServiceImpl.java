package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.UserCreateDTO;
import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.dto.mapper.UserMapper;
import com.tfm.bandas.usuarios.exception.BadRequestException;
import com.tfm.bandas.usuarios.exception.NotFoundException;
import com.tfm.bandas.usuarios.model.entity.InstrumentEntity;
import com.tfm.bandas.usuarios.model.entity.UserProfileEntity;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import com.tfm.bandas.usuarios.model.specification.UserSpecifications;
import com.tfm.bandas.usuarios.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final InstrumentRepository instrumentRepo;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(userMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        return userRepo.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByIamId(String iamId) {
        return userRepo.findByIamId(iamId)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("User not found with IAM id: " + iamId));
    }

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO dto) {
        if(userRepo.findByIamId(dto.iamId()).isPresent()) {
            throw new BadRequestException("User already registered with IAM id: " + dto.iamId());
        }

        UserProfileEntity userProfile = new UserProfileEntity();
        userProfile.setIamId(dto.iamId());
        userProfile.setSystemSignupDate(dto.systemSignupDate() != null ? dto.systemSignupDate() : LocalDate.now());
        userProfile.setActive(true);

        setMainUserInfo(dto, userProfile);

        return userMapper.toDTO(userRepo.save(userProfile));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserCreateDTO dto) {
        UserProfileEntity userProfile = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        // NO se actualiza iamId → es inmutable
        // NO se actualiza systemSignupDate → es inmutable
        // NO se actualiza active → se hace con endpoints específicos
        // Email y nombres: se supone que ya han sido cambiados en el IdP y se sincronizan aquí
        setMainUserInfo(dto, userProfile);

        return userMapper.toDTO(userRepo.save(userProfile));
    }

    private void setMainUserInfo(UserCreateDTO dto, UserProfileEntity userProfile) {
        userProfile.setFirstName(dto.firstName());
        userProfile.setLastName(dto.lastName());
        userProfile.setSecondLastName(dto.secondLastName());
        userProfile.setEmail(dto.email());
        userProfile.setPhone(dto.phone());
        userProfile.setNotes(dto.notes());
        userProfile.setProfilePictureUrl(dto.profilePictureUrl());
        userProfile.setBirthDate(dto.birthDate());
        userProfile.setBandJoinDate(dto.bandJoinDate());

        if (dto.instrumentIds() != null) {
            Set<InstrumentEntity> instruments = new HashSet<>(instrumentRepo.findAllById(dto.instrumentIds()));
            userProfile.setInstruments(instruments);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new NotFoundException("User not found with id " + id);
        }
        userRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void disableUser(Long id) {
        UserProfileEntity user = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        user.setActive(false);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void enableUser(Long id) {
        UserProfileEntity user = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        user.setActive(true);
        userRepo.save(user);
    }

    @Override
    @Transactional
    public UserDTO updateUserInstruments(Long userId, Set<Long> instrumentIds) {
        UserProfileEntity userProfile = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        if (instrumentIds != null && !instrumentIds.isEmpty()) {
            Set<InstrumentEntity> instruments = new HashSet<>(instrumentRepo.findAllById(instrumentIds));
            userProfile.setInstruments(instruments);
        }
        return userMapper.toDTO(userRepo.save(userProfile));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> searchUsers(String firstName, String lastName, String email, Boolean active, Long instrumentId, Pageable pageable) {
        Specification<UserProfileEntity> spec = Specification.allOf(
                UserSpecifications.firstNameContains(firstName),
                UserSpecifications.lastNameContains(lastName),
                UserSpecifications.emailContains(email),
                UserSpecifications.activeIs(active),
                UserSpecifications.hasInstrument(instrumentId)
        );

        return userRepo.findAll(spec, pageable).map(userMapper::toDTO);
    }

}
