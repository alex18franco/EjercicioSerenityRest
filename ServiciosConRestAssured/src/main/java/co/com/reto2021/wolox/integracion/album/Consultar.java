package co.com.reto2021.wolox.integracion.album;

import co.com.reto2021.wolox.model.Album;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;

import static co.com.reto2021.wolox.util.Constantes.ALBUMES;

public class Consultar implements Task {

    public static Consultar albumes(){
        return Tasks.instrumented(Consultar.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(ALBUMES).with(request->request
                .header("Authorization",actor.recall("token")
               ))
        );
        List<Album> albumes=SerenityRest.lastResponse().jsonPath().getList("",Album.class);
        actor.remember("albumes",albumes);
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
    }
}
