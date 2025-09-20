package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;
import com.tfm.bandas.usuarios.dto.mapper.InstrumentMapper;
import com.tfm.bandas.usuarios.exception.NotFoundException;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.specifications.InstrumentSpecifications;
import com.tfm.bandas.usuarios.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepo;
    private final InstrumentMapper instrumentMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<InstrumentDTO> getAllInstruments(Pageable pageable) {
        return instrumentRepo.findAll(pageable)
                .map(instrumentMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public InstrumentDTO getInstrumentById(Long id) {
        return instrumentRepo.findById(id)
                .map(instrumentMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Instrument not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstrumentDTO> getInstrumentsByInstrumentName(String instrumentName) {
        return instrumentRepo.findByInstrumentNameContainingIgnoreCase(instrumentName)
                .stream()
                .map(instrumentMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public InstrumentDTO createInstrument(InstrumentDTO dto) {
        Instrument instrument = new Instrument();
        instrument.setInstrumentName(dto.instrumentName());
        instrument.setVoice(dto.voice());
        return instrumentMapper.toDTO(instrumentRepo.save(instrument));
    }

    @Override
    @Transactional
    public void deleteInstrument(Long id) {
        instrumentRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InstrumentDTO> searchInstruments(String name, String voice, Pageable pageable) {
        Specification<Instrument> spec = Specification.allOf(
                InstrumentSpecifications.nameContains(name),
                InstrumentSpecifications.voiceContains(voice));

        return instrumentRepo.findAll(spec, pageable).map(instrumentMapper::toDTO);
    }

}
