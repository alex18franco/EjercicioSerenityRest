package co.com.reto2021.wolox.integracion.usuario;

import co.com.reto2021.wolox.model.Error;
import co.com.reto2021.wolox.model.Usuario;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static co.com.reto2021.wolox.util.Constantes.USUARIOS;

public class Registrar implements Task {

    private Usuario usuario;

    public Registrar(Usuario usuario){
        this.usuario=usuario;
    }

    public static Registrar elUsuario(Usuario usuario){
        return Tasks.instrumented(Registrar.class,usuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(USUARIOS)
                .with(request->request
                .contentType("application/json")
                .body(usuario)
                )
        );
        if(SerenityRest.lastResponse().asString().contains("errors")){
            Error error = SerenityRest.lastResponse().jsonPath().getList("errors", Error.class).get(0);
            actor.remember("mensajeRespuestaRegistrar",error.getCode());
            actor.remember("mensajeErrorRegistrar",error.getMessage());
        }else if(SerenityRest.lastResponse().asString().contains("user_id")){
            String message="user_id";
            int userId=SerenityRest.lastResponse().jsonPath().get(message);
            actor.remember("mensajeRespuestaOK",message);
            actor.remember("userId",userId);
        }
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
    }
}
