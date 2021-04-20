package co.com.reto2021.wolox.integracion.album;

import co.com.reto2021.wolox.model.AlbumComprado;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;

import static co.com.reto2021.wolox.util.Constantes.ALBUMES;
import static co.com.reto2021.wolox.util.Constantes.USUARIOS;

public class ConsultarAlbumes implements Task {

    private int idUsuario;

    public ConsultarAlbumes(int idUsuario){
        this.idUsuario=idUsuario;
    }

    public static ConsultarAlbumes comprados(int idUsuario){
        return Tasks.instrumented(ConsultarAlbumes.class,idUsuario);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(USUARIOS+"/"+idUsuario+ALBUMES)
                .with(request->request
                        .header("Authorization",actor.recall("token")
                        ))
        );
        List<AlbumComprado> albumesComprados= SerenityRest.lastResponse().jsonPath().getList("",AlbumComprado.class);
        actor.remember("albumesComprados",albumesComprados);
    }
}
