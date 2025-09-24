package com.tfm.bandas.usuarios.service;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstrumentService {
    Page<InstrumentDTO> getAllInstruments(Pageable pageable);
    InstrumentDTO getInstrumentById(Long id);
    InstrumentDTO createInstrument(InstrumentDTO dto);
    void deleteInstrument(Long id);
    Page<InstrumentDTO> searchInstruments(String instrumentName, String voice, Pageable pageable);
}
