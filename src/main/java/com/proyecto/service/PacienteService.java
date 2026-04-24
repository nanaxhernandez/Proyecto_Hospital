package com.proyecto.service;

import com.proyecto.domain.Paciente;
import com.proyecto.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    
    private final PacienteRepository pacienteRepository;
    
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Paciente> getPacientes(boolean activos) {
        return activos ? pacienteRepository.findByActivoTrue() : pacienteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Paciente> getPaciente(Integer idPaciente) {
        return pacienteRepository.findById(idPaciente);
    }
    
    @Transactional
    public Paciente save(Paciente paciente) {
        if (paciente.getActivo() == null) {
            paciente.setActivo(true);
        }
        return pacienteRepository.save(paciente);
    }
    
    @Transactional
    public void delete(Integer idPaciente) {
        pacienteRepository.deleteById(idPaciente);
    }
    
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorNombre(String nombre) {
        return pacienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorApellido(String apellido) {
        return pacienteRepository.findByApellidoContainingIgnoreCase(apellido);
    }
}