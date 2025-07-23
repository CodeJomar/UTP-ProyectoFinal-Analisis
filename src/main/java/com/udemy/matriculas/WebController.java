package com.udemy.matriculas;

import com.udemy.matriculas.auth.services.DashboardService;
import com.udemy.matriculas.auth.services.UsuarioService;
import com.udemy.matriculas.cursos.models.dtos.CursoDTO;
import com.udemy.matriculas.cursos.services.CursoService;
import com.udemy.matriculas.eventos.models.dtos.EventoDTO;
import com.udemy.matriculas.eventos.services.EventoService;
import com.udemy.matriculas.registros.services.DocenteService;
import com.udemy.matriculas.registros.services.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {

<<<<<<< HEAD
    private final DocenteService docenteService;
    private final DashboardService dashboardService;
    private final EstudianteService estudianteService;
    private final UsuarioService usuarioService;
    private final CursoService cursoService;
    private final EventoService eventoService;

=======
    
//    @GetMapping("/usuarios")
//    public String pagina1() {
//        return "usuario";
//    }
    
//    @GetMapping("/registro")
//    public String pagina2() {
//        return "registro";
//    }
    
//    @GetMapping("/")
//    public String pagina3() {
//        return "login";
//    }
    
>>>>>>> e056ad775ba99c0d1a67588811461aa531cdf76b
    @GetMapping("/index")
    public String indexPage(Model model) {
        if (!model.containsAttribute("curso")) {
            model.addAttribute("curso", new CursoDTO());
        }
        if (!model.containsAttribute("evento")) {
            model.addAttribute("evento", new EventoDTO());
        }
        model.addAttribute("docentesDisponibles", docenteService.listarDocentes());
        model.addAttribute("actividades", dashboardService.getActividadesRecientes());
        model.addAttribute("ctdCursos", cursoService.contarCursos());
        model.addAttribute("ctdEventos", eventoService.contarEventos());
        model.addAttribute("ctdUsuarios", usuarioService.contarUsuarios());
        model.addAttribute("ctdEstudiantes", estudianteService.contarEstudiantes());
        model.addAttribute("ctdDocentes", docenteService.contarDocentes());

        return "index";
    }
}