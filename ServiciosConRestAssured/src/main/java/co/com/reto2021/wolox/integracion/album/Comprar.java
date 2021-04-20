package co.com.reto2021.wolox.integracion.album;

import co.com.reto2021.wolox.model.Error;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static co.com.reto2021.wolox.util.Constantes.ALBUMES;

public class Comprar implements Task {
    private int idAlbum;
    public Comprar (int idAlbum){
        this.idAlbum=idAlbum;
    }

    public static Comprar album(int idAlbum){
        return Tasks.instrumented(Comprar.class,idAlbum);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(ALBUMES+"/"+idAlbum)
                .with(request->request
                    .contentType("application/json")
                    .header("Authorization",actor.recall("token"))
        ));
        if(SerenityRest.lastResponse().asString().contains("errors")){
            Error error = SerenityRest.lastResponse().jsonPath().getList("errors", Error.class).get(0);
            actor.remember("mensajeRespuesta",error.getCode());
            actor.remember("mensajeError",error.getMessage());
        }
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
    }
}
