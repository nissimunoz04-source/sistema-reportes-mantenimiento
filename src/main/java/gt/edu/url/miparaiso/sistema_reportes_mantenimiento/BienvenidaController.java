package gt.edu.url.miparaiso.sistema_reportes_mantenimiento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BienvenidaController {

    @GetMapping("/api/saludo")
    public String saludo() {
        return "¡Bienvenido(a) al Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso!";
    }

    @GetMapping("/api/bienvenida")
    public String bienvenida(@RequestParam(defaultValue = "visitante") String nombre) {
        return "Hola, " + nombre + ". Gracias por visitar el Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso.";
    }
}