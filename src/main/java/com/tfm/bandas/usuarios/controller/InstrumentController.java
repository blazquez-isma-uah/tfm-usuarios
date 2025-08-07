package com.tfm.bandas.usuarios.controller;

import com.tfm.bandas.usuarios.dto.InstrumentDTO;
import com.tfm.bandas.usuarios.service.InstrumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instruments")
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentService instrumentService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping
    public List<InstrumentDTO> getAll() {
        return instrumentService.getAllInstruments();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping("/{id}")
    public InstrumentDTO getById(@PathVariable Long id) {
        return instrumentService.getInstrumentById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICIAN')")
    @GetMapping("/search")
    public List<InstrumentDTO> getByInstumentName(@RequestParam String instrumentName) {
        return instrumentService.getInstrumentsByInstrumentName(instrumentName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public InstrumentDTO create(@RequestBody @Valid InstrumentDTO dto) {
        return instrumentService.createInstrument(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        instrumentService.deleteInstrument(id);
    }
}
