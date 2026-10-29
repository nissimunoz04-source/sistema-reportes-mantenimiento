package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ReporteMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. Título del reporte
    @NotBlank(message = "El título del reporte es obligatorio.")
    @Size(min = 5, max = 60,
          message = "El título debe tener entre 5 y 60 caracteres.")
    @Column(nullable = false, length = 60)
    private String titulo;

    // 2. Descripción del problema
    @NotBlank(message = "La descripción del problema es obligatoria.")
    @Size(min = 10, max = 250,
          message = "La descripción debe tener entre 10 y 250 caracteres.")
    @Column(nullable = false, length = 250)
    private String descripcion;

    // 3. Ubicación del problema
    @NotBlank(message = "La ubicación del problema es obligatoria.")
    @Size(min = 3, max = 80,
          message = "La ubicación debe tener entre 3 y 80 caracteres.")
    @Column(nullable = false, length = 80)
    private String ubicacion;

    // 4. Estado del reporte
    @NotBlank(message = "El estado del reporte es obligatorio.")
    @Pattern(
        regexp = "Pendiente|En revisión|Resuelto",
        message = "El estado debe ser Pendiente, En revisión o Resuelto."
    )
    @Column(nullable = false, length = 20)
    private String estado;

    // 5. Fecha de registro
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // 6. Fecha de resolución
    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    // 7. Persona que reporta el problema
    @NotBlank(message = "El nombre de quien reporta es obligatorio.")
    @Size(max = 80,
          message = "El nombre no puede superar los 80 caracteres.")
    @Column(nullable = false, length = 80)
    private String reportadoPor;

    // 8. Prioridad del reporte
    @NotBlank(message = "La prioridad del reporte es obligatoria.")
    @Pattern(
        regexp = "Baja|Media|Alta",
        message = "La prioridad debe ser Baja, Media o Alta."
    )
    @Column(nullable = false, length = 10)
    private String prioridad;

    // 9. Tipo de mantenimiento
    @NotBlank(message = "El tipo de mantenimiento es obligatorio.")
    @Pattern(
        regexp = "Preventivo|Correctivo",
        message = "El tipo debe ser Preventivo o Correctivo."
    )
    @Column(nullable = false, length = 20)
    private String tipoMantenimiento;

    // 10. Observaciones adicionales (opcional)
    @Size(max = 250,
          message = "Las observaciones no pueden superar los 250 caracteres.")
    @Column(length = 250)
    private String observaciones;

    // Constructor vacío
    public ReporteMantenimiento() {
    }

    // Constructor original para conservar compatibilidad
    public ReporteMantenimiento(
            String titulo,
            String descripcion,
            String ubicacion,
            String estado) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    // Constructor con los nuevos campos
    public ReporteMantenimiento(
            String titulo,
            String descripcion,
            String ubicacion,
            String estado,
            String reportadoPor,
            String prioridad,
            String tipoMantenimiento,
            String observaciones) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.reportadoPor = reportadoPor;
        this.prioridad = prioridad;
        this.tipoMantenimiento = tipoMantenimiento;
        this.observaciones = observaciones;
    }

    // Se ejecuta antes de guardar un reporte nuevo
    @PrePersist
    public void antesDeCrear() {

        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }

        if ("Resuelto".equals(estado)
                && fechaResolucion == null) {

            fechaResolucion = LocalDateTime.now();
        }
    }

    // Se ejecuta antes de actualizar un reporte
    @PreUpdate
    public void antesDeActualizar() {

        if ("Resuelto".equals(estado)
                && fechaResolucion == null) {

            fechaResolucion = LocalDateTime.now();
        }

        if (!"Resuelto".equals(estado)) {
            fechaResolucion = null;
        }
    }

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(String reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}