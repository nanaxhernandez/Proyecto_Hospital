/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Reporte;
import com.proyecto.repository.ReporteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {
    
    private final ReporteRepository reporteRepository;
    
    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Reporte> getReportes() {
        return reporteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Reporte> getReporte(Long idReporte) {
        return reporteRepository.findById(idReporte);
    }
    
    @Transactional
    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }
    
    @Transactional
    public void delete(Long idReporte) {
        reporteRepository.deleteById(idReporte);
    }
}