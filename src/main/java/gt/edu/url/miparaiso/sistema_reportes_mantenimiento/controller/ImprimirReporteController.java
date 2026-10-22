
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.ReporteMantenimientoService;

@Controller
public class ImprimirReporteController {

    private final ReporteMantenimientoService reporteService;

    public ImprimirReporteController(
            ReporteMantenimientoService reporteService) {

        this.reporteService = reporteService;
    }

    @GetMapping("/reportes/{id}/imprimir")
    public String imprimirReporte(
            @PathVariable Long id,
            Model model) {

        // Buscar el reporte seleccionado
        ReporteMantenimiento reporte =
                reporteService.buscarPorId(id);

        // Enviar sus datos a la página imprimible
        model.addAttribute("reporte", reporte);

        // Mostrar la plantilla de impresión
        return "imprimir-reporte";
    }
}