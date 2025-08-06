package com.tfm.bandas.usuarios.service;

import com.tfm.bandas.usuarios.dto.RoleDTO;
import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Long id);
    RoleDTO createRole(RoleDTO dto);
    void deleteRole(Long id);
}
