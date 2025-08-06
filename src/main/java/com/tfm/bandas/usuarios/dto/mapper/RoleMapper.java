package com.tfm.bandas.usuarios.dto.mapper;

import com.tfm.bandas.usuarios.dto.RoleDTO;
import com.tfm.bandas.usuarios.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
}
