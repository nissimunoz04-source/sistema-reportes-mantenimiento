
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccesoController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}