package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.BienvenidaService;

@RestController
public class BienvenidaController {

    private final BienvenidaService bienvenidaService;

    public BienvenidaController(BienvenidaService bienvenidaService) {
        this.bienvenidaService = bienvenidaService;
    }

    @GetMapping("/api/saludo")
    public String saludo() {
        return bienvenidaService.mensajeGeneral();
    }

    @GetMapping("/api/bienvenida")
    public String bienvenida(@RequestParam(defaultValue = "visitante") String nombre) {
        return bienvenidaService.mensajePara(nombre);
    }
}