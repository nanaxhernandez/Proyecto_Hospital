package com.proyecto.service;

import com.proyecto.domain.Cita;
import com.proyecto.repository.CitaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    
    private final CitaRepository citaRepository;
    
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Cita> getCitas(boolean activas) {
        return activas ? citaRepository.findByActivoTrue() : citaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Cita> getCita(Integer idCita) {
        return citaRepository.findById(idCita);
    }
    
    @Transactional
    public Cita save(Cita cita) {
        if (cita.getActivo() == null) {
            cita.setActivo(true);
        }
        if (cita.getAtendida() == null) {
            cita.setAtendida(false);
        }
        return citaRepository.save(cita);
    }
    
    @Transactional
    public void delete(Integer idCita) {
        citaRepository.deleteById(idCita);
    }
    
    @Transactional(readOnly = true)
    public List<Cita> getCitasPendientes() {
        return citaRepository.findByAtendidaFalse();
    }
}