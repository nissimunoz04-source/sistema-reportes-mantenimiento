
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.ReporteMantenimientoService;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.SeguimientoReporteService;

@Controller
public class SeguimientoReporteController {

    private final SeguimientoReporteService seguimientoService;
    private final ReporteMantenimientoService reporteService;

    public SeguimientoReporteController(
            SeguimientoReporteService seguimientoService,
            ReporteMantenimientoService reporteService) {

        this.seguimientoService = seguimientoService;
        this.reporteService = reporteService;
    }

    // Mostrar el historial de un reporte
    @GetMapping("/reportes/{id}/seguimientos")
    public String mostrarSeguimientos(
            @PathVariable Long id,
            Model model) {

        ReporteMantenimiento reporte =
                reporteService.buscarPorId(id);

        model.addAttribute("reporte", reporte);

        model.addAttribute(
                "seguimientos",
                seguimientoService.listarPorReporte(id)
        );

        return "seguimientos";
    }

    // Guardar un seguimiento nuevo
    // Solamente el administrador puede agregarlo
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reportes/{id}/seguimientos")
    public String guardarSeguimiento(
            @PathVariable Long id,
            @RequestParam("descripcion") String descripcion,
            RedirectAttributes redirectAttributes) {

        try {

            seguimientoService.guardar(id, descripcion);

            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "¡Seguimiento registrado correctamente!"
            );

        } catch (IllegalArgumentException error) {

            redirectAttributes.addFlashAttribute(
                    "mensajeError",
                    error.getMessage()
            );
        }

        return "redirect:/reportes/" + id + "/seguimientos";
    }
}