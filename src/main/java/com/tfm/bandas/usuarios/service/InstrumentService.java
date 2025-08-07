package com.tfm.bandas.usuarios.service;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;

import java.util.List;

public interface InstrumentService {
    List<InstrumentDTO> getAllInstruments();
    InstrumentDTO getInstrumentById(Long id);
    List<InstrumentDTO> getInstrumentsByInstrumentName(String instrumentName);
    InstrumentDTO createInstrument(InstrumentDTO dto);
    void deleteInstrument(Long id);
}
