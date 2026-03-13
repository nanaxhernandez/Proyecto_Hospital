package com.proyecto.repository;

import com.proyecto.domain.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {
    List<HistorialMedico> findByActivoTrue();
    List<HistorialMedico> findByPacienteIdPaciente(Integer idPaciente);
    List<HistorialMedico> findByDoctorIdDoctor(Integer idDoctor);
}