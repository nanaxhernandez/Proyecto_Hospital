package com.proyecto.controller;

import com.proyecto.domain.Doctor;
import com.proyecto.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    
    private final DoctorService doctorService;
    
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("doctores", doctorService.getDoctores(true));
        return "doctor/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor/modifica";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctor/listado";
    }
    
    @GetMapping("/modificar/{idDoctor}")
    public String modificar(@PathVariable Integer idDoctor, Model model) {
        var doctor = doctorService.getDoctor(idDoctor).orElse(null);
        model.addAttribute("doctor", doctor);
        return "doctor/modifica";
    }
    
    @GetMapping("/eliminar/{idDoctor}")
    public String eliminar(@PathVariable Integer idDoctor) {
        doctorService.delete(idDoctor);
        return "redirect:/doctor/listado";
    }
}