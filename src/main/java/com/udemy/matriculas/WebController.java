package com.udemy.matriculas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    
    @GetMapping("/usuario")
    public String pagina1() {
        return "usuario";
    }
    
    @GetMapping("/registro")
    public String pagina2() {
        return "registro";
    }
    
    @GetMapping("/login")
    public String pagina3() {
        return "login";
    }
    
    @GetMapping("/index")
    public String pagina4() {
        return "index";
    }
    
    @GetMapping("/eventos")
    public String pagina5() {
        return "eventos";
    }
    
    @GetMapping("/docentes")
    public String pagina6() {
        return "docentes";
    }
    
    @GetMapping("/cursos")
    public String pagina7() {
        return "cursos";
    }
    
    @GetMapping("/alumnos")
    public String pagina8() {
        return "alumnos";
    }
    
}
