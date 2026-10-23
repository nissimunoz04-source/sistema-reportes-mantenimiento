
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.ReporteMantenimientoService;

import jakarta.validation.Valid;

@Controller
public class ReporteMantenimientoController {

    private final ReporteMantenimientoService reporteService;

    public ReporteMantenimientoController(
            ReporteMantenimientoService reporteService) {

        this.reporteService = reporteService;
    }

    // Muestra el formulario para registrar un reporte nuevo
    @GetMapping("/reportes/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
            "reporte",
            new ReporteMantenimiento()
        );

        return "formulario";
    }

    // Valida y guarda un reporte nuevo o editado
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

    // Muestra los reportes y permite buscar y filtrar
    @GetMapping("/reportes")
    public String listarReportes(
            @RequestParam(
                name = "buscar",
                defaultValue = ""
            ) String buscar,

            @RequestParam(
                name = "estado",
                defaultValue = ""
            ) String estado,

            Model model) {

        // Obtener todos los reportes
        List<ReporteMantenimiento> reportes =
                reporteService.listarTodos();

        // Preparar el texto de búsqueda
        String textoBusqueda = buscar
                .trim()
                .toLowerCase(Locale.ROOT);

        // Preparar el filtro de estado
        String estadoSeleccionado = estado
                .trim()
                .toLowerCase(Locale.ROOT);

        // Aplicar búsqueda y filtro
        List<ReporteMantenimiento> reportesFiltrados =
                reportes.stream()

                .filter(reporte -> {

                    // Si no escribió nada, mostrar todos
                    if (textoBusqueda.isEmpty()) {
                        return true;
                    }

                    String titulo = reporte.getTitulo() == null
                            ? ""
                            : reporte.getTitulo()
                                    .toLowerCase(Locale.ROOT);

                    String ubicacion = reporte.getUbicacion() == null
                            ? ""
                            : reporte.getUbicacion()
                                    .toLowerCase(Locale.ROOT);

                    // Buscar por título o ubicación
                    return titulo.contains(textoBusqueda)
                            || ubicacion.contains(textoBusqueda);
                })

                .filter(reporte -> {

                    // Si no seleccionó estado, mostrar todos
                    if (estadoSeleccionado.isEmpty()) {
                        return true;
                    }

                    String estadoReporte = reporte.getEstado() == null
                            ? ""
                            : reporte.getEstado()
                                    .toString()
                                    .trim()
                                    .toLowerCase(Locale.ROOT);

                    return estadoReporte.equals(
                            estadoSeleccionado
                    );
                })

                .toList();

        // Enviar los resultados a la pantalla
        model.addAttribute(
            "reportes",
            reportesFiltrados
        );

        // Total general de reportes
        model.addAttribute(
            "total",
            reporteService.contar()
        );

        // Cantidad de resultados encontrados
        model.addAttribute(
            "totalFiltrados",
            reportesFiltrados.size()
        );

        // Conservar los filtros seleccionados
        model.addAttribute("buscar", buscar);
        model.addAttribute("estadoSeleccionado", estado);

        return "lista";
    }

    // Abre el formulario para editar un reporte
    @GetMapping("/reportes/{id}/editar")
    public String editarReporte(
            @PathVariable Long id,
            Model model) {

        ReporteMantenimiento reporte =
                reporteService.buscarPorId(id);

        model.addAttribute("reporte", reporte);

        return "formulario";
    }

    // Elimina un reporte por su ID
    @PostMapping("/reportes/{id}/eliminar")
    public String eliminarReporte(
            @PathVariable Long id) {

        reporteService.eliminar(id);

        return "redirect:/reportes";
    }
}