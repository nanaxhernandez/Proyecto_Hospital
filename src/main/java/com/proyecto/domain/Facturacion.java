/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "facturacion")
public class Facturacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Long idFactura;

    @Column(name = "numero_factura")
    private String numeroFactura;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha_factura")
    private String fechaFactura;

    @Column(name = "estado")
    private String estado;
}