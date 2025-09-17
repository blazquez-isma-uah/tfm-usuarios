package com.tfm.bandas.usuarios.dto.mapper;

import com.tfm.bandas.usuarios.dto.UserDTO;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "instruments", source = "instruments")
    UserDTO toDTO(UserProfile userProfile);

    // Métodos auxiliares que MapStruct utilizará automáticamente
    default Set<String> mapInstruments(Set<Instrument> instruments) {
        return instruments.stream()
                .map(i -> i.getInstrumentName() + " " + i.getVoice())
                .collect(Collectors.toSet());
    }
}
