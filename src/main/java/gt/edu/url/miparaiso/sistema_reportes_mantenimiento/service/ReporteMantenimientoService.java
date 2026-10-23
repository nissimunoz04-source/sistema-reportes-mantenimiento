
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

    public List<ReporteMantenimiento> listarTodos() {
        return repositorio.findAll();
    }

    public ReporteMantenimiento buscarPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un reporte de mantenimiento con id " + id));
    }

    public ReporteMantenimiento guardar(ReporteMantenimiento reporte) {
        return repositorio.save(reporte);
    }

    @Transactional
    public void eliminar(Long id) {

        // Verificar que el reporte exista
        ReporteMantenimiento reporte = buscarPorId(id);

        // Eliminar primero los seguimientos asociados
        seguimientoRepositorio.deleteAll(
                seguimientoRepositorio.findByReporteIdOrderByFechaSeguimientoDesc(id)
        );

        // Eliminar el reporte después de sus seguimientos
        repositorio.delete(reporte);
    }

    public long contar() {
        return repositorio.count();
    }
}