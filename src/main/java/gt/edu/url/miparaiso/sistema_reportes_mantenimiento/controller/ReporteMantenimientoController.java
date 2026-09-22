package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.ReporteMantenimientoService;

@Controller
public class ReporteMantenimientoController {

    private final ReporteMantenimientoService reporteService;

    public ReporteMantenimientoController(ReporteMantenimientoService reporteService) {
        this.reporteService = reporteService;
    }

    // GET: muestra el formulario vacío
    @GetMapping("/reportes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reporte", new ReporteMantenimiento());
        return "formulario";
    }

    // POST: recibe y guarda el reporte
    @PostMapping("/reportes")
    public String guardarReporte(
            @ModelAttribute("reporte") ReporteMantenimiento reporte) {

        reporteService.guardar(reporte);

        return "redirect:/reportes";
    }

    // GET: muestra todos los reportes registrados
    @GetMapping("/reportes")
    public String listarReportes(Model model) {

        model.addAttribute("reportes", reporteService.listarTodos());
        model.addAttribute("total", reporteService.contar());

        return "lista";
    }
}