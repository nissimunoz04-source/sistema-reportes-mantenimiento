package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository.ReporteMantenimientoRepository;

@Service
public class ReporteMantenimientoService {

    private final ReporteMantenimientoRepository repositorio;

    public ReporteMantenimientoService(ReporteMantenimientoRepository repositorio) {
        this.repositorio = repositorio;
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

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    public long contar() {
        return repositorio.count();
    }
}