package com.proyecto.repository;

import com.proyecto.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    List<Paciente> findByActivoTrue();
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
    List<Paciente> findByApellidoContainingIgnoreCase(String apellido);
}