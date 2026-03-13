package com.proyecto.service;

import com.proyecto.domain.Cita;
import com.proyecto.domain.HistorialMedico;
import com.proyecto.repository.HistorialMedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialMedicoService {
    
    private final HistorialMedicoRepository historialRepository;
    private final CitaService citaService;
    
    public HistorialMedicoService(HistorialMedicoRepository historialRepository, CitaService citaService) {
        this.historialRepository = historialRepository;
        this.citaService = citaService;
    }
    
    @Transactional(readOnly = true)
    public List<HistorialMedico> getHistoriales(boolean activos) {
        return activos ? historialRepository.findByActivoTrue() : historialRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<HistorialMedico> getHistorial(Integer idHistorial) {
        return historialRepository.findById(idHistorial);
    }
    
    @Transactional
    public HistorialMedico save(HistorialMedico historial) {
        if (historial.getActivo() == null) {
            historial.setActivo(true);
        }
        
        // Marcar la cita como atendida
        if (historial.getCita() != null && historial.getCita().getIdCita() != null) {
            Cita cita = citaService.getCita(historial.getCita().getIdCita()).orElse(null);
            if (cita != null) {
                cita.setAtendida(true);
                citaService.save(cita);
            }
        }
        
        return historialRepository.save(historial);
    }
    
    @Transactional(readOnly = true)
    public List<HistorialMedico> getHistorialPorPaciente(Integer idPaciente) {
        return historialRepository.findByPacienteIdPaciente(idPaciente);
    }
    
    // ✅ NUEVO MÉTODO AGREGADO
    @Transactional
    public void delete(Integer idHistorial) {
        historialRepository.deleteById(idHistorial);
    }
}