
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.Usuario;
import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.repository.UsuarioRepository;

@Component
public class CargadorUsuarios implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final String passwordAdmin;
    private final String passwordPersonal;

    public CargadorUsuarios(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.password}") String passwordAdmin,
            @Value("${app.personal.password}") String passwordPersonal) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordAdmin = passwordAdmin;
        this.passwordPersonal = passwordPersonal;
    }

    @Override
    public void run(String... args) {

        crearSiNoExiste("admin", passwordAdmin, "ADMIN");
        crearSiNoExiste("personal", passwordPersonal, "PERSONAL");

    }

    private void crearSiNoExiste(
            String username,
            String passwordPlano,
            String rol) {

        if (usuarioRepository.findByUsername(username).isEmpty()) {

            String hash = passwordEncoder.encode(passwordPlano);

            usuarioRepository.save(
                    new Usuario(username, hash, rol)
            );
        }
    }
}