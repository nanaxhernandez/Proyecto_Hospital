/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Reporte;
import com.proyecto.service.ReporteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("reportes", reporteService.getReportes());
        return "reporte/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("reporte", new Reporte());
        return "reporte/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reporte reporte) {
        reporteService.save(reporte);
        return "redirect:/reporte/listado";
    }

    @GetMapping("/modificar/{idReporte}")
    public String modificar(@PathVariable Long idReporte, Model model) {
        var reporte = reporteService.getReporte(idReporte).orElse(null);
        model.addAttribute("reporte", reporte);
        return "reporte/modifica";
    }

    @GetMapping("/eliminar/{idReporte}")
    public String eliminar(@PathVariable Long idReporte) {
        reporteService.delete(idReporte);
        return "redirect:/reporte/listado";
    }
}
