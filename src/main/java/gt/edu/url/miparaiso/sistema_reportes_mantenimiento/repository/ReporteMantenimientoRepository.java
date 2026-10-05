package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.ReporteMantenimiento;

public interface ReporteMantenimientoRepository
        extends JpaRepository<ReporteMantenimiento, Long> {
}