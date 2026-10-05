package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ReporteMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título del reporte es obligatorio.")
    @Size(min = 5, max = 60, message = "El título debe tener entre 5 y 60 caracteres.")
    @Column(nullable = false, length = 60)
    private String titulo;

    @NotBlank(message = "La descripción del problema es obligatoria.")
    @Size(min = 10, max = 250, message = "La descripción debe tener entre 10 y 250 caracteres.")
    @Column(nullable = false, length = 250)
    private String descripcion;

    @NotBlank(message = "La ubicación del problema es obligatoria.")
    @Size(min = 3, max = 80, message = "La ubicación debe tener entre 3 y 80 caracteres.")
    @Column(nullable = false, length = 80)
    private String ubicacion;

    @NotBlank(message = "El estado del reporte es obligatorio.")
    @Pattern(
        regexp = "Pendiente|En revisión|Resuelto",
        message = "El estado debe ser Pendiente, En revisión o Resuelto."
    )
    @Column(nullable = false, length = 20)
    private String estado;

    public ReporteMantenimiento() {
    }

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
}