/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Facturacion;
import com.proyecto.repository.FacturacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FacturacionService {
    
    private final FacturacionRepository facturacionRepository;
    
    public FacturacionService(FacturacionRepository facturacionRepository) {
        this.facturacionRepository = facturacionRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Facturacion> getFacturaciones() {
        return facturacionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Facturacion> getFacturacion(Long idFactura) {
        return facturacionRepository.findById(idFactura);
    }
    
    @Transactional
    public Facturacion save(Facturacion facturacion) {
        return facturacionRepository.save(facturacion);
    }
    
    @Transactional
    public void delete(Long idFactura) {
        facturacionRepository.deleteById(idFactura);
    }
}