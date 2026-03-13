package com.proyecto.repository;

import com.proyecto.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByActivoTrue();
    List<Cita> findByPacienteIdPaciente(Integer idPaciente);
    List<Cita> findByDoctorIdDoctor(Integer idDoctor);
    List<Cita> findByFecha(LocalDate fecha);
    List<Cita> findByAtendidaFalse();
}