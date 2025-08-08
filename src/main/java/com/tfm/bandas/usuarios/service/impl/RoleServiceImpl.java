package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.RoleDTO;
import com.tfm.bandas.usuarios.dto.mapper.RoleMapper;
import com.tfm.bandas.usuarios.model.entity.Role;
import com.tfm.bandas.usuarios.model.repository.RoleRepository;
import com.tfm.bandas.usuarios.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepo, RoleMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepo.findAll()
                .stream()
                .map(roleMapper::toDTO)
                .toList();
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return roleRepo.findById(id)
                .map(roleMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public RoleDTO createRole(RoleDTO dto) {
        Role role = new Role();
        if (dto.name() == null || dto.name().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        if (roleRepo.existsByName(dto.name())) {
            throw new IllegalArgumentException("Role with name '" + dto.name() + "' already exists");
        }
        role.setName(dto.name());
        role.setDescription(dto.description());
        return roleMapper.toDTO(roleRepo.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
