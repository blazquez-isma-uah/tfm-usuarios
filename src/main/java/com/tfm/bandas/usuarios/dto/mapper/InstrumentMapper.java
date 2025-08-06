package com.tfm.bandas.usuarios.dto.mapper;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstrumentMapper {
    InstrumentDTO toDTO(Instrument instrument);
}
