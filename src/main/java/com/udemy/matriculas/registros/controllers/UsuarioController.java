package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.auth.services.UsuarioService;
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
    
    @GetMapping("")
    public String paginaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listadoUsuarios());
        return "usuarios";
    }
    
}
