
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

}