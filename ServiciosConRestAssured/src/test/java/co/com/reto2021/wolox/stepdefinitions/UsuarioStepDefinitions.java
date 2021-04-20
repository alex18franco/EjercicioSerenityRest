package co.com.reto2021.wolox.stepdefinitions;

import co.com.reto2021.wolox.exceptions.ElMensajeValidado;
import co.com.reto2021.wolox.integracion.usuario.Consultar;
import co.com.reto2021.wolox.integracion.usuario.Iniciar;
import co.com.reto2021.wolox.integracion.usuario.Invalidar;
import co.com.reto2021.wolox.integracion.usuario.Registrar;
import co.com.reto2021.wolox.model.Usuario;
import co.com.reto2021.wolox.questions.ElMensaje;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Assert;

import java.util.List;

import static co.com.reto2021.wolox.util.Constantes.END_POINT;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class UsuarioStepDefinitions {
    private String token;

    @Before
    public void queElUsuarioTieneLaHabilidadDeConsumirElApi() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("Alex").whoCan(CallAnApi.at(END_POINT));
    }

    @Cuando("^se ingresan los datos del usuario regular a registrar$")
    public void seIngresanLosDatosDelUsuarioRegularARegistrar(List<Usuario> usuario) {
        theActorInTheSpotlight().attemptsTo(Registrar.elUsuario(usuario.get(0)));
    }

    @Entonces("^se debe crear el usuario correctamente$")
    public void seDebeCrearElUsuarioCorrectamente() {
        String mensajeRespuesta=theActorInTheSpotlight().recall("mensajeErrorRegistrar");
        String mensajeRespuestaOK=theActorInTheSpotlight().recall("mensajeRespuestaOK");
        if(mensajeRespuesta!=null) {
            theActorInTheSpotlight().should(
                    seeThat(ElMensaje.de(mensajeRespuesta).contiene("create already exists"), equalTo(true))
                            .orComplainWith(ElMensajeValidado.class, "El mensaje de respuesta no contiene el mensaje indicado")
            );
            theActorInTheSpotlight().remember("codigoRespuesta",201);
        }else{
            Assert.assertEquals(mensajeRespuestaOK,"user_id");
        }
    }

    @Entonces("^ve el codigo de respuesta (\\d+)$")
    public void veElCodigoDeRespuesta(int codigoRespuesta) {
        Assert.assertEquals((int)theActorInTheSpotlight().recall("codigoRespuesta"),codigoRespuesta);
    }

    @Entonces("^se debe mostrar los errores relacionados con los campos$")
    public void seDebeMostrarLosErroresRelacionadosConLosCampos(List<String> camposError) {
        String mensaje=theActorCalled("Alex").recall("mensajeErrorRegistrar");
        camposError.forEach(campo->{
            theActorInTheSpotlight().should(
                    seeThat(ElMensaje.de(mensaje).contiene(campo), equalTo(true))
                            .orComplainWith(ElMensajeValidado.class,"el campo: "+campo+" no se encuentra en el mensaje de respuesta")
            );
        });
    }
    @Cuando("^se inicia sesion con el usuario$")
    public void seIniciaSesionConElUsuario(List<Usuario> usuario) {
        theActorInTheSpotlight().attemptsTo(Iniciar.conCredenciales(usuario.get(0)));
    }

    @Cuando("^consulta el listado de usuarios$")
    public void consultaElListadoDeUsuarios() {
        theActorInTheSpotlight().attemptsTo(Consultar.usuarios());
    }

    @Entonces("^que el codigo de autorizacion no esta vacio$")
    public void queElCodigoDeAutorizacionNoEstaVacio() {
        String token=theActorInTheSpotlight().recall("token");
        Assert.assertNotNull(token);
        Assert.assertTrue(!token.isEmpty());
    }

    @Entonces("^el usuario debe ver su id de usuario (\\d+)$")
    public void elUsuarioDebeVerSuIdDeUsuario(int idUsuario) {
        Assert.assertEquals((int)theActorInTheSpotlight().recall("userId"),idUsuario);
    }

    @Entonces("^el usuario debe ver el error (.*)$")
    public void elUsuarioDebeVerElError(String error) {
        Assert.assertEquals(theActorInTheSpotlight().recall("mensajeError"),error);
    }

    @Cuando("^el sistema devolvera los usuarios con rol (.*)$")
    public void elSistemaDevolveraLosUsuariosConRolCualquiera(String rol) {
        List<Usuario> usuarosNoRegular=theActorInTheSpotlight().recall("usuarosNoRegular");
        if("admin".equals(rol)){
            Assert.assertFalse(usuarosNoRegular.isEmpty());
        }else if("regular".equals(rol)){
            Assert.assertTrue(usuarosNoRegular.isEmpty());
        }
    }

    @Cuando("^se invalida las sessiones de los usuarios$")
    public void seInvalidaLasSessionesDeLosUsuarios() {
        theActorInTheSpotlight().attemptsTo(Invalidar.sesiones());
    }
}
