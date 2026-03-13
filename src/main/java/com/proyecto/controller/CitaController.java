package com.proyecto.controller;

import com.proyecto.domain.Cita;
import com.proyecto.service.CitaService;
import com.proyecto.service.PacienteService;
import com.proyecto.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cita")
public class CitaController {
    
    private final CitaService citaService;
    private final PacienteService pacienteService;
    private final DoctorService doctorService;
    
    public CitaController(CitaService citaService, PacienteService pacienteService, DoctorService doctorService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
        this.doctorService = doctorService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("citas", citaService.getCitas(true));
        return "cita/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("pacientes", pacienteService.getPacientes(true));
        model.addAttribute("doctores", doctorService.getDoctores(true));
        return "cita/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cita cita) {
        citaService.save(cita);
        return "redirect:/cita/listado";
    }
    
    @GetMapping("/modificar/{idCita}")
    public String modificar(@PathVariable Integer idCita, Model model) {
        var cita = citaService.getCita(idCita).orElse(null);
        model.addAttribute("cita", cita);
        model.addAttribute("pacientes", pacienteService.getPacientes(true));
        model.addAttribute("doctores", doctorService.getDoctores(true));
        return "cita/modifica";
    }
    
    @GetMapping("/eliminar/{idCita}")
    public String eliminar(@PathVariable Integer idCita) {
        citaService.delete(idCita);
        return "redirect:/cita/listado";
    }
    
    // ✅ NUEVO MÉTODO AGREGADO
    @GetMapping("/atender/{idCita}")
    public String atender(@PathVariable Integer idCita) {
        return "redirect:/historialmedico/nuevo?citaId=" + idCita;
    }
}