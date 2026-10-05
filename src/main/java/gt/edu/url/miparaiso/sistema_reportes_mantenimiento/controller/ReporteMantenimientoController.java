package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.ReporteMantenimientoService;

import jakarta.validation.Valid;

@Controller
public class ReporteMantenimientoController {

    private final ReporteMantenimientoService reporteService;

    public ReporteMantenimientoController(ReporteMantenimientoService reporteService) {
        this.reporteService = reporteService;
    }

    // GET: muestra el formulario para registrar un reporte nuevo
    @GetMapping("/reportes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reporte", new ReporteMantenimiento());
        return "formulario";
    }

    // POST: valida y guarda un reporte nuevo o editado
    @PostMapping("/reportes")
    public String guardarReporte(
            @Valid @ModelAttribute("reporte") ReporteMantenimiento reporte,
            BindingResult resultado) {

        if (resultado.hasErrors()) {
            return "formulario";
        }

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

    // GET: abre el formulario con los datos del reporte que se editará
    @GetMapping("/reportes/{id}/editar")
    public String editarReporte(
            @PathVariable Long id,
            Model model) {

        ReporteMantenimiento reporte = reporteService.buscarPorId(id);
        model.addAttribute("reporte", reporte);

        return "formulario";
    }

    // POST: elimina un reporte por su ID
    @PostMapping("/reportes/{id}/eliminar")
    public String eliminarReporte(@PathVariable Long id) {

        reporteService.eliminar(id);

        return "redirect:/reportes";
    }
}