package com.udemy.matriculas.auth.controllers;

import com.udemy.matriculas.auth.models.dtos.RegisUsuarioDTO;
import com.udemy.matriculas.auth.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        if (!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new RegisUsuarioDTO());
        }
        return "registro"; // registro.html
    }

    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("usuario") RegisUsuarioDTO dto,
                            BindingResult result, Model model, RedirectAttributes redirect) {

        if (result.hasErrors()) {
            model.addAttribute("mostrarError", true);
            return "registro";
        }

        try {
            usuarioService.registrarUsuario(dto);
            redirect.addFlashAttribute("mensaje", "¡Usuario registrado exitosamente!");
            return "redirect:/?registroExitoso";
        } catch (IllegalArgumentException ex) {
            result.rejectValue("username", null, ex.getMessage());
            model.addAttribute("mostrarError", true);
            model.addAttribute("usuario", dto);
            return "registro";
        }
    }
}

