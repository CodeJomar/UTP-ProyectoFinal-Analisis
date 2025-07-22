package com.udemy.matriculas.auth.controllers;

import com.udemy.matriculas.auth.models.dtos.RegisUsuarioDTO;
import com.udemy.matriculas.auth.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
/*
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    
    @PostMapping("/registro")
    public String registro(@Valid @ModelAttribute("usuario") RegisUsuarioDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "";
        }
        usuarioService.registrarUsuario(dto);
        return "";
    }
}
*/