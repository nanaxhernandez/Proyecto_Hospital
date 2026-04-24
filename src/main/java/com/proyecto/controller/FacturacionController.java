/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Facturacion;
import com.proyecto.service.FacturacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/facturacion")
public class FacturacionController {
    
    private final FacturacionService facturacionService;
    
    public FacturacionController(FacturacionService facturacionService) {
        this.facturacionService = facturacionService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("facturaciones", facturacionService.getFacturaciones());
        return "facturacion/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("facturacion", new Facturacion());
        return "facturacion/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Facturacion facturacion) {
        facturacionService.save(facturacion);
        return "redirect:/facturacion/listado";
    }
    
    @GetMapping("/modificar/{idFactura}")
    public String modificar(@PathVariable Long idFactura, Model model) {
        var facturacion = facturacionService.getFacturacion(idFactura).orElse(null);
        model.addAttribute("facturacion", facturacion);
        return "facturacion/modifica";
    }
    
    @GetMapping("/eliminar/{idFactura}")
    public String eliminar(@PathVariable Long idFactura) {
        facturacionService.delete(idFactura);
        return "redirect:/facturacion/listado";
    }
}