package co.com.reto2021.wolox.integracion.usuario;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static co.com.reto2021.wolox.util.Constantes.INVALIDAR_SESIONES;

public class Invalidar implements Task {

    public static Invalidar sesiones(){

        return Tasks.instrumented(Invalidar.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(INVALIDAR_SESIONES)
                .with(request->request
                        .contentType("application/json")
                        .header("Authorization",actor.recall("token"))
                )
        );
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
    }
}
