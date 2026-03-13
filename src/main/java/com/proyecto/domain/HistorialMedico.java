package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "historial_medico")
public class HistorialMedico implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    
    @OneToOne
    @JoinColumn(name = "id_cita")
    private Cita cita;
    
    private LocalDate fecha;
    private String descripcion;
    private String tratamiento;
    private Boolean activo;
}