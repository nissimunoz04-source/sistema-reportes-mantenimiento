
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "seguimientos_reportes")
public class SeguimientoReporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reporte al que pertenece el seguimiento
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reporte_id", nullable = false)
    private ReporteMantenimiento reporte;

    // Descripción del trabajo realizado
    @NotBlank(message = "La descripción del seguimiento es obligatoria.")
    @Size(
        min = 5,
        max = 500,
        message = "La descripción debe tener entre 5 y 500 caracteres."
    )
    @Column(nullable = false, length = 500)
    private String descripcion;

    // Fecha y hora del seguimiento
    @Column(name = "fecha_seguimiento", nullable = false)
    private LocalDateTime fechaSeguimiento;

    public SeguimientoReporte() {
    }

    public SeguimientoReporte(
            ReporteMantenimiento reporte,
            String descripcion) {

        this.reporte = reporte;
        this.descripcion = descripcion;
    }

    // Coloca automáticamente la fecha al guardar
    @PrePersist
    public void antesDeCrear() {

        if (fechaSeguimiento == null) {
            fechaSeguimiento = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReporteMantenimiento getReporte() {
        return reporte;
    }

    public void setReporte(ReporteMantenimiento reporte) {
        this.reporte = reporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(LocalDateTime fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }
}