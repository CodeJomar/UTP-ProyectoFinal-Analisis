package com.udemy.matriculas.registros.controllers;

import com.udemy.matriculas.auth.models.entities.Usuario;
import com.udemy.matriculas.auth.services.UsuarioService;
import com.udemy.matriculas.registros.services.DocenteService;
import com.udemy.matriculas.registros.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
>>>>>>> e056ad775ba99c0d1a67588811461aa531cdf76b

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final DocenteService docenteService;
    private final EstudianteService estudianteService;
<<<<<<< HEAD

    @GetMapping("")
    public String paginaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.obtenerVistaUnificadaDeUsuarios());
=======
    
    @GetMapping("")
    public String paginaUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listadoUsuarios());
>>>>>>> e056ad775ba99c0d1a67588811461aa531cdf76b
        model.addAttribute("ctdDocente", docenteService.contarDocentes());
        model.addAttribute("ctdEstudiante", estudianteService.contarEstudiantes());
        model.addAttribute("ctdUsuario", usuarioService.contarUsuarios());
        return "usuario";
<<<<<<< HEAD
    }

}
=======
    }
    
    @GetMapping("/api/{id}/details")
    @ResponseBody // Indica que el método devuelve directamente el cuerpo de la respuesta HTTP
    public ResponseEntity<Map<String, Object>> getUsuarioDetails(@PathVariable String id) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorIdConDetalles(id);
        
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Usuario usuario = usuarioOptional.get();
        Map<String, Object> details = new HashMap<>();
        details.put("id", usuario.getId());
        details.put("username", usuario.getUsername());
        details.put("rol", usuario.getRol().getNombre());
        
        if (usuario.getDocente() != null) {
            Map<String, String> docenteDetails = new HashMap<>();
            docenteDetails.put("nombre", usuario.getDocente().getNombre());
            docenteDetails.put("apellido", usuario.getDocente().getApellido());
            docenteDetails.put("dni", usuario.getDocente().getDni());
            docenteDetails.put("especialidad", usuario.getDocente().getEspecialidad());
            details.put("tipo", "Docente");
            details.put("datosExtra", docenteDetails);
        } else if (usuario.getEstudiante() != null) {
            Map<String, String> estudianteDetails = new HashMap<>();
            estudianteDetails.put("nombre", usuario.getEstudiante().getNombre());
            estudianteDetails.put("apellido", usuario.getEstudiante().getApellido());
            estudianteDetails.put("dni", usuario.getEstudiante().getDni());
            details.put("tipo", "Estudiante");
            details.put("datosExtra", estudianteDetails);
        } else {
            details.put("tipo", "Registro");
            details.put("datosExtra", "No tiene datos adicionales de Docente/Estudiante.");
        }
        
        return ResponseEntity.ok(details);
    }
    
}
>>>>>>> e056ad775ba99c0d1a67588811461aa531cdf76b
