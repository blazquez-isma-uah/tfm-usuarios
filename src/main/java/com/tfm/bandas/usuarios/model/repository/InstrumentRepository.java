package com.tfm.bandas.usuarios.model.repository;

import com.tfm.bandas.usuarios.model.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, Long>,
        JpaSpecificationExecutor<Instrument> {

    Optional<Instrument> findByInstrumentNameAndVoice(String instrumentName, String voice);

    Optional<Instrument> findByInstrumentNameContainingIgnoreCase(String instrumentName);
}
