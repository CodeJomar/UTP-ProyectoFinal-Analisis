package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.auth.services.UsuarioService;
import com.udemy.matriculas.registros.services.DocenteService;
import com.udemy.matriculas.registros.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final DocenteService docenteService;
    private final EstudianteService estudianteService;

    @GetMapping("")
    public String paginaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerVistaUnificadaDeUsuarios());
        model.addAttribute("ctdDocente", docenteService.contarDocentes());
        model.addAttribute("ctdEstudiante", estudianteService.contarEstudiantes());
        model.addAttribute("ctdUsuario", usuarioService.contarUsuarios());
        return "usuario";
    }

}