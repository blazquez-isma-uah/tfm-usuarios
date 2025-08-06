package com.tfm.bandas.usuarios.dto.mapper;

import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))")
    @Mapping(target = "instruments", expression = "java(user.getInstruments().stream().map(i -> i.getInstrumentName() + \" \" + i.getVoice()).collect(Collectors.toSet()))")
    UserDTO toDTO(User user);
}
