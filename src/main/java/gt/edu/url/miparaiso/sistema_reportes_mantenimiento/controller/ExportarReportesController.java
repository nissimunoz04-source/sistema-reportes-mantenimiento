
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service.ReporteMantenimientoService;

@Controller
public class ExportarReportesController {

    private final ReporteMantenimientoService reporteService;

    public ExportarReportesController(
            ReporteMantenimientoService reporteService) {

        this.reporteService = reporteService;
    }

    @GetMapping("/reportes/exportar")
    public ResponseEntity<byte[]> exportarExcel(
            @RequestParam(defaultValue = "") String buscar,
            @RequestParam(defaultValue = "") String estado) {

        // Obtener los reportes existentes
        List<ReporteMantenimiento> reportes =
                reporteService.listarTodos();

        // Preparar los filtros
        String textoBusqueda =
                buscar.trim().toLowerCase(Locale.ROOT);

        String estadoSeleccionado =
                estado.trim().toLowerCase(Locale.ROOT);

        // Aplicar los mismos filtros de la pantalla
        List<ReporteMantenimiento> filtrados =
                reportes.stream()

                .filter(reporte -> {

                    if (textoBusqueda.isEmpty()) {
                        return true;
                    }

                    String titulo =
                            reporte.getTitulo() == null
                            ? ""
                            : reporte.getTitulo()
                                .toLowerCase(Locale.ROOT);

                    String ubicacion =
                            reporte.getUbicacion() == null
                            ? ""
                            : reporte.getUbicacion()
                                .toLowerCase(Locale.ROOT);

                    return titulo.contains(textoBusqueda)
                            || ubicacion.contains(textoBusqueda);
                })

                .filter(reporte -> {

                    if (estadoSeleccionado.isEmpty()) {
                        return true;
                    }

                    String estadoReporte =
                            reporte.getEstado() == null
                            ? ""
                            : reporte.getEstado()
                                .toString()
                                .trim()
                                .toLowerCase(Locale.ROOT);

                    return estadoReporte.equals(estadoSeleccionado);
                })

                .toList();

        // Crear el contenido del archivo CSV
        StringBuilder csv = new StringBuilder();

        csv.append("ID;Título;Descripción;Ubicación;Estado\r\n");

        for (ReporteMantenimiento reporte : filtrados) {

            csv.append(celda(reporte.getId()));
            csv.append(";");

            csv.append(celda(reporte.getTitulo()));
            csv.append(";");

            csv.append(celda(reporte.getDescripcion()));
            csv.append(";");

            csv.append(celda(reporte.getUbicacion()));
            csv.append(";");

            csv.append(celda(reporte.getEstado()));
            csv.append("\r\n");
        }

        // Agregar BOM para que Excel reconozca las tildes
        String contenido = "\uFEFF" + csv;

        byte[] archivo =
                contenido.getBytes(StandardCharsets.UTF_8);

        HttpHeaders headers = new HttpHeaders();

        headers.add(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"reportes-mantenimiento.csv\""
        );

        headers.setContentType(
            MediaType.parseMediaType("text/csv;charset=UTF-8")
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(archivo);
    }

    // Protege el formato CSV y conserva los textos
    private String celda(Object valor) {

        String texto = valor == null
                ? ""
                : valor.toString();

        // Evitar que Excel interprete datos como fórmulas
        if (texto.matches("(?s)^\\s*[=+@\\-].*")) {
            texto = "'" + texto;
        }

        // Escapar comillas para el formato CSV
        texto = texto.replace("\"", "\"\"");

        return "\"" + texto + "\"";
    }
}