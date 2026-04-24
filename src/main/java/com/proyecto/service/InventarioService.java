/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Inventario;
import com.proyecto.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    
    private final InventarioRepository inventarioRepository;
    
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Inventario> getInventarios() {
        return inventarioRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Inventario> getInventario(Long idInventario) {
        return inventarioRepository.findById(idInventario);
    }
    
    @Transactional
    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
    
    @Transactional
    public void delete(Long idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
}