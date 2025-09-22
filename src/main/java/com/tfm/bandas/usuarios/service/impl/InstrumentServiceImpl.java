package com.tfm.bandas.usuarios.service.impl;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;
import com.tfm.bandas.usuarios.dto.mapper.InstrumentMapper;
import com.tfm.bandas.usuarios.exception.NotFoundException;
import com.tfm.bandas.usuarios.model.entity.Instrument;
import com.tfm.bandas.usuarios.model.repository.InstrumentRepository;
import com.tfm.bandas.usuarios.model.repository.UserRepository;
import com.tfm.bandas.usuarios.model.specification.InstrumentSpecifications;
import com.tfm.bandas.usuarios.service.InstrumentService;
import jakarta.persistence.EntityNotFoundException;
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
    private final UserRepository userRepo;

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
    public Page<InstrumentDTO> getInstrumentsByInstrumentName(String instrumentName, Pageable pageable) {
        return instrumentRepo.findByInstrumentNameContainingIgnoreCase(instrumentName, pageable)
                .map(instrumentMapper::toDTO);
    }

    @Override
    @Transactional
    public InstrumentDTO createInstrument(InstrumentDTO dto) {
        Instrument instrument = instrumentMapper.toEntity(dto);
        return instrumentMapper.toDTO(instrumentRepo.save(instrument));
    }

    @Override
    @Transactional
    public void deleteInstrument(Long id) {
        Instrument instrument = instrumentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instrument not found: " + id));
        // Eliminar asignaciones de usuarios antes de borrar
        userRepo.findAll().forEach(user -> user.getInstruments().remove(instrument));
        instrumentRepo.delete(instrument);
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
