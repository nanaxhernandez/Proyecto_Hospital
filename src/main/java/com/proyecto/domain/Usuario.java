/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author dereckrodriguez
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "rol", length = 20)
    private String rol;

    @Column(name = "cedula", length = 20, unique = true)
    private String cedula;

    @Column(name = "activo")
    private Boolean activo;
}
