package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model;

public class ReporteMantenimiento {

    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String estado;

    // Constructor vacío
    public ReporteMantenimiento() {
    }

    // Constructor con parámetros
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

    // Getter y Setter de titulo
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter y Setter de descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter de ubicacion
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Getter y Setter de estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}