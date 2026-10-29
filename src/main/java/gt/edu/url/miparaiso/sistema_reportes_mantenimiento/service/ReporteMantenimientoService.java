package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository.ReporteMantenimientoRepository;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository.SeguimientoReporteRepository;

@Service
public class ReporteMantenimientoService {

    private final ReporteMantenimientoRepository repositorio;
    private final SeguimientoReporteRepository seguimientoRepositorio;

    public ReporteMantenimientoService(
            ReporteMantenimientoRepository repositorio,
            SeguimientoReporteRepository seguimientoRepositorio) {

        this.repositorio = repositorio;
        this.seguimientoRepositorio = seguimientoRepositorio;
    }

    // Listar todos los reportes
    public List<ReporteMantenimiento> listarTodos() {
        return repositorio.findAll();
    }

    // Buscar un reporte por su ID
    public ReporteMantenimiento buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un reporte de mantenimiento con id " + id
                        )
                );
    }

    // Crear o actualizar un reporte
    @Transactional
    public ReporteMantenimiento guardar(ReporteMantenimiento reporte) {

        // Si el reporte es nuevo, guardarlo normalmente
        if (reporte.getId() == null) {
            return repositorio.save(reporte);
        }

        // Si ya existe, recuperar el registro original
        ReporteMantenimiento existente =
                buscarPorId(reporte.getId());

        // Actualizar únicamente los datos editables
        existente.setTitulo(reporte.getTitulo());
        existente.setDescripcion(reporte.getDescripcion());
        existente.setUbicacion(reporte.getUbicacion());
        existente.setEstado(reporte.getEstado());

        // Nuevos campos de la Semana 15
        existente.setReportadoPor(reporte.getReportadoPor());
        existente.setPrioridad(reporte.getPrioridad());
        existente.setTipoMantenimiento(reporte.getTipoMantenimiento());
        existente.setObservaciones(reporte.getObservaciones());

        // No modificar fechaRegistro:
        // se conserva la fecha original del reporte.

        // fechaResolucion se actualiza mediante @PreUpdate
        // cuando cambia el estado del reporte.

        return repositorio.save(existente);
    }

    // Eliminar un reporte y sus seguimientos
    @Transactional
    public void eliminar(Long id) {

        ReporteMantenimiento reporte = buscarPorId(id);

        seguimientoRepositorio.deleteAll(
                seguimientoRepositorio
                        .findByReporteIdOrderByFechaSeguimientoDesc(id)
        );

        repositorio.delete(reporte);
    }

    // Contar reportes
    public long contar() {
        return repositorio.count();
    }
}