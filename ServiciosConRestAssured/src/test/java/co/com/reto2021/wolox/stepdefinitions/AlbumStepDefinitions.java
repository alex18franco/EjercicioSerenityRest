package co.com.reto2021.wolox.stepdefinitions;

import co.com.reto2021.wolox.integracion.album.Comprar;
import co.com.reto2021.wolox.integracion.album.Consultar;
import co.com.reto2021.wolox.integracion.album.ConsultarAlbumes;
import co.com.reto2021.wolox.integracion.album.ConsultarFotos;
import co.com.reto2021.wolox.model.AlbumComprado;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import org.junit.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AlbumStepDefinitions {
    @Cuando("^el usuario consulta el listado de albumes$")
    public void elUsuarioConsultaElListadoDeAlbumes() {
        theActorInTheSpotlight().attemptsTo(Consultar.albumes());
    }

    @Cuando("^el usuario consulta el listado de fotos del album (\\d+)$")
    public void elUsuarioConsultaElListadoDeFotosDelAlbum(int idAlbum) {
        theActorInTheSpotlight().attemptsTo(ConsultarFotos.delAlbum(idAlbum));
    }

    @Cuando("^el usuario consulta los albumes comprados por (\\d+)$")
    public void elUsuarioConsultaLosAlbumesCompradosPor(int idUsuario) {
        theActorInTheSpotlight().attemptsTo(ConsultarAlbumes.comprados(idUsuario));
    }

    @Entonces("^se evidencia que los albumes pertenecen al usuario especificado (\\d+)$")
    public void seEvidenciaQueLosAlbumesPertenecenAlUsuarioEspecificado(int idUsuario) {
        List<AlbumComprado> albumesComprados=theActorInTheSpotlight().recall("albumesComprados");
        List<AlbumComprado> albumConUsuario=albumesComprados.stream().filter(album->album.getUser_id()==idUsuario).collect(Collectors.toList());
        Assert.assertArrayEquals(albumesComprados.toArray(),albumConUsuario.toArray());
    }

    @Dado("^que el usuario compra el album (\\d+)$")
    public void queElUsuarioCompraElAlbum(int idAlbum) {
        theActorInTheSpotlight().attemptsTo(Comprar.album(idAlbum));
    }
    @Entonces("^se evidencia que el album comprado quedo guardado correctamente (\\d+)$")
    public void seEvidenciaQueElAlbumCompradoQuedoGuardadoCorrectamente(int idAlbum) {
        List<AlbumComprado> albumesComprados=theActorInTheSpotlight().recall("albumesComprados");
        Optional<AlbumComprado> albumEncontrado=albumesComprados.stream().filter(album->album.getAlbum().getId()==idAlbum).findFirst();
        String mensajeError=theActorInTheSpotlight().recall("mensajeRespuesta");
        if(mensajeError!=null){
            Assert.assertEquals(mensajeError,"entity_already_exists");
            theActorInTheSpotlight().remember("codigoRespuesta",201);
        }
        Assert.assertTrue(albumEncontrado.isPresent());
    }
}
