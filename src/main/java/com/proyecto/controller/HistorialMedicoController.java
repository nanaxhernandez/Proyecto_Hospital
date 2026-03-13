package com.proyecto.controller;

import com.proyecto.domain.HistorialMedico;
import com.proyecto.service.HistorialMedicoService;
import com.proyecto.service.PacienteService;
import com.proyecto.service.DoctorService;
import com.proyecto.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historialmedico")
public class HistorialMedicoController {
    
    private final HistorialMedicoService historialMedicoService;
    private final PacienteService pacienteService;
    private final DoctorService doctorService;
    private final CitaService citaService;
    
    public HistorialMedicoController(HistorialMedicoService historialMedicoService, 
                                     PacienteService pacienteService,
                                     DoctorService doctorService,
                                     CitaService citaService) {
        this.historialMedicoService = historialMedicoService;
        this.pacienteService = pacienteService;
        this.doctorService = doctorService;
        this.citaService = citaService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("historiales", historialMedicoService.getHistoriales(true));
        return "historialmedico/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(@RequestParam(required = false) Integer citaId, Model model) {
        HistorialMedico historial = new HistorialMedico();
        
        if (citaId != null) {
            var cita = citaService.getCita(citaId).orElse(null);
            if (cita != null) {
                historial.setCita(cita);
                historial.setPaciente(cita.getPaciente());
                historial.setDoctor(cita.getDoctor());
                historial.setFecha(cita.getFecha());
            }
        }
        
        model.addAttribute("historial", historial);
        model.addAttribute("pacientes", pacienteService.getPacientes(true));
        model.addAttribute("doctores", doctorService.getDoctores(true));
        model.addAttribute("citasPendientes", citaService.getCitasPendientes());
        return "historialmedico/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute HistorialMedico historial) {
        historialMedicoService.save(historial);
        return "redirect:/historialmedico/listado";
    }
    
    @GetMapping("/modificar/{idHistorial}")
    public String modificar(@PathVariable Integer idHistorial, Model model) {
        var historial = historialMedicoService.getHistorial(idHistorial).orElse(null);
        model.addAttribute("historial", historial);
        model.addAttribute("pacientes", pacienteService.getPacientes(true));
        model.addAttribute("doctores", doctorService.getDoctores(true));
        return "historialmedico/modifica";
    }
    
    @GetMapping("/eliminar/{idHistorial}")
    public String eliminar(@PathVariable Integer idHistorial) {
        historialMedicoService.delete(idHistorial);
        return "redirect:/historialmedico/listado";
    }
    
    @GetMapping("/paciente/{idPaciente}")
    public String historialPorPaciente(@PathVariable Integer idPaciente, Model model) {
        model.addAttribute("historiales", historialMedicoService.getHistorialPorPaciente(idPaciente));
        model.addAttribute("paciente", pacienteService.getPaciente(idPaciente).orElse(null));
        return "historialmedico/listado";
    }
}