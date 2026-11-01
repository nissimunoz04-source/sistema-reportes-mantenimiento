package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreditosController {

    @GetMapping("/creditos")
    public String mostrarCreditos() {
        return "creditos";
    }
}