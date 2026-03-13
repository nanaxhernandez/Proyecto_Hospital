/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Usuario;
import com.proyecto.service.UsuarioService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author dereckrodriguez
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios(true));
        return "usuario/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuario/modifica";
    }

    @PostMapping("/guardar")
    public String guardar(Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String modificar(@PathVariable Integer idUsuario, Model model) {
        var usuario = usuarioService.getUsuario(idUsuario).orElse(null);
        model.addAttribute("usuario", usuario);
        return "usuario/modifica";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String eliminar(@PathVariable Integer idUsuario) {
        usuarioService.delete(idUsuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/buscar")
    public String mostrarFormulario() {
        return "usuario/buscar"; // formulario de búsqueda
    }

    @PostMapping("/buscarCorreo")
    public String buscarPorCorreo(@RequestParam("correo") String correo, Model model) {
        Usuario usuario = usuarioService.buscarPorCorreo(correo);
        model.addAttribute("usuario", usuario);
        return "usuario/detalle";
    }


    @PostMapping("/buscarNombre")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        model.addAttribute("usuarios", usuarios);
        return "usuario/listado";
    }


    @PostMapping("/buscarCedula")
    public String buscarPorCedula(@RequestParam("cedula") String cedula, Model model) {
        Usuario usuario = usuarioService.buscarPorCedula(cedula);
        model.addAttribute("usuario", usuario);
        return "usuario/detalle"; // reutilizas la vista detalle
    }

}
