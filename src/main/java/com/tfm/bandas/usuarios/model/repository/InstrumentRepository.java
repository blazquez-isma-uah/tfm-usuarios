package com.tfm.bandas.usuarios.model.repository;

import com.tfm.bandas.usuarios.model.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    Optional<Instrument> findByInstrumentNameAndVoice(String instrumentName, String voice);
}
