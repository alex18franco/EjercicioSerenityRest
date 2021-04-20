package co.com.reto2021.wolox.integracion.album;

import co.com.reto2021.wolox.model.Foto;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.List;

import static co.com.reto2021.wolox.util.Constantes.ALBUMES;
import static co.com.reto2021.wolox.util.Constantes.FOTOS;

public class ConsultarFotos implements Task {
    private int idAlbum;
    public  ConsultarFotos(int idAlbum){
        this.idAlbum=idAlbum;
    }
    public static ConsultarFotos delAlbum(int idAlbum){
        return Tasks.instrumented(ConsultarFotos.class,idAlbum);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(ALBUMES+"/"+idAlbum+FOTOS).with(request->request
                .header("Authorization",actor.recall("token")
                ))
        );
        List<Foto> fotos=SerenityRest.lastResponse().jsonPath().getList("",Foto.class);
        actor.remember("fotosDeAlbum",fotos);
        actor.remember("codigoRespuesta", SerenityRest.lastResponse().getStatusCode());
    }
}
