package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;

@Service
public class ReporteMantenimientoService {

    private final List<ReporteMantenimiento> reportes = new ArrayList<>();

    public void guardar(ReporteMantenimiento reporte) {
        reportes.add(reporte);
    }

    public List<ReporteMantenimiento> listarTodos() {
        return reportes;
    }

    public int contar() {
        return reportes.size();
    }
}