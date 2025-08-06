package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;
import com.tfm.bandas.usuarios.dto.mapper.InstrumentMapper;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.service.InstrumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepo;
    private final InstrumentMapper instrumentMapper;

    public InstrumentServiceImpl(InstrumentRepository instrumentRepo, InstrumentMapper instrumentMapper) {
        this.instrumentRepo = instrumentRepo;
        this.instrumentMapper = instrumentMapper;
    }

    @Override
    public List<InstrumentDTO> getAllInstruments() {
        return instrumentRepo.findAll()
                .stream()
                .map(instrumentMapper::toDTO)
                .toList();
    }

    @Override
    public InstrumentDTO getInstrumentById(Long id) {
        return instrumentRepo.findById(id)
                .map(instrumentMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Instrument not found"));
    }

    @Override
    public InstrumentDTO createInstrument(InstrumentDTO dto) {
        Instrument instrument = new Instrument();
        instrument.setInstrumentName(dto.instrumentName());
        instrument.setVoice(dto.voice());
        return instrumentMapper.toDTO(instrumentRepo.save(instrument));
    }

    @Override
    public void deleteInstrument(Long id) {
        instrumentRepo.deleteById(id);
    }
}
