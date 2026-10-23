
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.SeguimientoReporte;

public interface SeguimientoReporteRepository
        extends JpaRepository<SeguimientoReporte, Long> {

    // Buscar todos los seguimientos de un reporte,
    // ordenados del más reciente al más antiguo.
    List<SeguimientoReporte>
        findByReporteIdOrderByFechaSeguimientoDesc(Long reporteId);
}