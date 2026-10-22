
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.SeguimientoReporte;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository.SeguimientoReporteRepository;

@Service
public class SeguimientoReporteService {

    private final SeguimientoReporteRepository seguimientoRepository;

    private final ReporteMantenimientoService reporteService;

    public SeguimientoReporteService(
            SeguimientoReporteRepository seguimientoRepository,
            ReporteMantenimientoService reporteService) {

        this.seguimientoRepository = seguimientoRepository;
        this.reporteService = reporteService;
    }

    // Consultar el historial de un reporte
    @Transactional(readOnly = true)
    public List<SeguimientoReporte> listarPorReporte(Long reporteId) {

        // Verificamos que el reporte exista
        reporteService.buscarPorId(reporteId);

        return seguimientoRepository
                .findByReporteIdOrderByFechaSeguimientoDesc(reporteId);
    }

    // Registrar un nuevo seguimiento
    @Transactional
    public SeguimientoReporte guardar(
            Long reporteId,
            String descripcion) {

        // Buscamos el reporte al que pertenece
        ReporteMantenimiento reporte =
                reporteService.buscarPorId(reporteId);

        // Validamos la descripción
        if (descripcion == null
                || descripcion.isBlank()) {

            throw new IllegalArgumentException(
                    "La descripción del seguimiento es obligatoria."
            );
        }

        String descripcionLimpia = descripcion.trim();

        if (descripcionLimpia.length() < 5
                || descripcionLimpia.length() > 500) {

            throw new IllegalArgumentException(
                    "La descripción debe tener entre 5 y 500 caracteres."
            );
        }

        // Creamos el seguimiento
        SeguimientoReporte seguimiento =
                new SeguimientoReporte();

        seguimiento.setReporte(reporte);
        seguimiento.setDescripcion(descripcionLimpia);

        // Guardamos en la base de datos
        return seguimientoRepository.save(seguimiento);
    }
}