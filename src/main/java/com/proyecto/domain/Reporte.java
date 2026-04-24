/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "reporte")
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long idReporte;

    @Column(name = "nombre_reporte")
    private String nombreReporte;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_reporte")
    private Date fechaReporte;

    @Column(name = "estado")
    private String estado;

    @Column(name = "activo")
    private Boolean activo;
}