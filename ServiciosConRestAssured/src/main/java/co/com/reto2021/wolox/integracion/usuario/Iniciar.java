package co.com.reto2021.wolox.integracion.usuario;

import co.com.reto2021.wolox.model.Error;
import co.com.reto2021.wolox.model.Usuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static co.com.reto2021.wolox.util.Constantes.LOGIN;

public class Iniciar implements Task {

    private Usuario usuario;

    public Iniciar(Usuario usuario) {
        this.usuario=usuario;
    }

    public static Iniciar conCredenciales(Usuario usuario){
        return Tasks.instrumented(Iniciar.class,usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(LOGIN).with(request->request
                 .contentType("application/json")
                 .body(usuario)
                )
        );
        if(SerenityRest.lastResponse().asString().contains("errors")){
            Error error = SerenityRest.lastResponse().jsonPath().getList("errors", Error.class).get(0);
            actor.remember("mensajeRespuesta",error.getCode());
            actor.remember("mensajeError",error.getMessage());
        }else{
            actor.remember("userId",(int)SerenityRest.lastResponse().jsonPath().get("user_id"));
            actor.remember("token",SerenityRest.lastResponse().getHeader("Authorization"));
        }
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
    }
}
