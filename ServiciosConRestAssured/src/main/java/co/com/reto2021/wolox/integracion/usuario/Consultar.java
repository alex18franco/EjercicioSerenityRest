package co.com.reto2021.wolox.integracion.usuario;

import co.com.reto2021.wolox.model.Usuario;
import co.com.reto2021.wolox.model.UsuarioRegistrado;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.reto2021.wolox.util.Constantes.USUARIOS;

public class Consultar implements Task {

    public static Consultar       usuarios(){
        return Tasks.instrumented(Consultar.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Usuario> usuarios=new ArrayList<>();
        UsuarioRegistrado usuarioRegistrado;

        int paginaActual=1;
        do {
            int finalPaginaActual=paginaActual;
            actor.attemptsTo(Get.resource(USUARIOS)
                    .with(request->request.header("Authorization",actor.recall("token"))
                    .params("page", finalPaginaActual))
            );
            usuarioRegistrado= SerenityRest.lastResponse().jsonPath().getObject("", UsuarioRegistrado.class);
            paginaActual+=1;
            usuarios.addAll(usuarioRegistrado.getPage());
        }while (paginaActual<=usuarioRegistrado.getTotal_pages());
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
        List<Usuario> usuarosNoRegular=usuarios.stream().filter(usuario -> !"regular".equals(usuario.getRol())).collect(Collectors.toList());
        actor.remember("usuarosNoRegular",usuarosNoRegular);
    }
}
