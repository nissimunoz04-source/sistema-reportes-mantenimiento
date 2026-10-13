
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // Aquí se guarda el hash de la contraseña.
    @Column(nullable = false)
    private String password;

    // Los roles serán ADMIN y PERSONAL.
    @Column(nullable = false)
    private String rol;

    public Usuario() {
    }

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    // Permite actualizar el hash de la contraseña.
    public void setPassword(String password) {
        this.password = password;
    }

    // Permite actualizar el rol del usuario.
    public void setRol(String rol) {
        this.rol = rol;
    }
}