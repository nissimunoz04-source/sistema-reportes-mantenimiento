
package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filtros(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(reglas -> reglas

                // Pantallas y archivos públicos
                .requestMatchers(
                    "/login",
                    "/estilos.css",
                    "/error"
                ).permitAll()

                // Solo ADMIN puede abrir formularios
                .requestMatchers(
                    "/reportes/nuevo",
                    "/reportes/*/editar"
                ).hasRole("ADMIN")

                // Solo ADMIN puede guardar y eliminar
                .requestMatchers(
                    HttpMethod.POST,
                    "/reportes/**"
                ).hasRole("ADMIN")

                // Las demás páginas requieren iniciar sesión
                .anyRequest().authenticated()
            )

            // Inicio de sesión personalizado
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/reportes", true)
            )

            // Cerrar sesión
            .logout(logout -> logout
                .logoutSuccessUrl("/login?salio")
            )

            // Pantalla cuando no tiene permisos
            .exceptionHandling(errores -> errores
                .accessDeniedPage("/acceso-denegado")
            );

        return http.build();
    }
}