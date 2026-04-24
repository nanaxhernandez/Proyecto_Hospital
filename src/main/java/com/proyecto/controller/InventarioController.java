/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Inventario;
import com.proyecto.service.InventarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventario")
public class InventarioController {
    
    private final InventarioService inventarioService;
    
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("inventarios", inventarioService.getInventarios());
        return "inventario/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("inventario", new Inventario());
        return "inventario/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Inventario inventario) {
        inventarioService.save(inventario);
        return "redirect:/inventario/listado";
    }
    
    @GetMapping("/modificar/{idInventario}")
    public String modificar(@PathVariable Long idInventario, Model model) {
        var inventario = inventarioService.getInventario(idInventario).orElse(null);
        model.addAttribute("inventario", inventario);
        return "inventario/modifica";
    }
    
    @GetMapping("/eliminar/{idInventario}")
    public String eliminar(@PathVariable Long idInventario) {
        inventarioService.delete(idInventario);
        return "redirect:/inventario/listado";
    }
}