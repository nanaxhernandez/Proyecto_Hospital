package com.proyecto.controller;

import com.proyecto.domain.Paciente;
import com.proyecto.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
    
    private final PacienteService pacienteService;
    
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("pacientes", pacienteService.getPacientes(true));
        return "paciente/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "paciente/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(Paciente paciente) {
        pacienteService.save(paciente);
        return "redirect:/paciente/listado";
    }
    
    @GetMapping("/modificar/{idPaciente}")
    public String modificar(@PathVariable Integer idPaciente, Model model) {
        var paciente = pacienteService.getPaciente(idPaciente).orElse(null);
        model.addAttribute("paciente", paciente);
        return "paciente/modifica";
    }
    
    @GetMapping("/eliminar/{idPaciente}")
    public String eliminar(@PathVariable Integer idPaciente) {
        pacienteService.delete(idPaciente);
        return "redirect:/paciente/listado";
    }
}