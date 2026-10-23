package gt.edu.url.miparaiso.sistema_reportes_mantenimiento.service;

import org.springframework.stereotype.Service;

import gt.edu.url.miparaiso.sistema_reportes_mantenimiento.model.Visitante;

@Service
public class BienvenidaService {

    private static final String INSTITUCION =
            "Colegio Cristiano Mixto Bilingüe Mi Dulce Paraíso";

    public String mensajeGeneral() {
        return "¡Bienvenido(a) al " + INSTITUCION + "!";
    }

    public String mensajePara(String nombre) {
        Visitante visitante = new Visitante(nombre);

        return "Hola, " + visitante.getNombre()
                + ". Gracias por visitar el " + INSTITUCION + ".";
    }
}
