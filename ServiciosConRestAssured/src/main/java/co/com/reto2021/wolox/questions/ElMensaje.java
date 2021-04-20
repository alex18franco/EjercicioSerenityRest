package co.com.reto2021.wolox.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ElMensaje implements Question<Boolean> {

    private String mensaje;
    private  String mensajeContenido;
    public ElMensaje (String mensaje){
        this.mensaje=mensaje;
    }
    public static ElMensaje de(String mensaje){
        return new ElMensaje(mensaje);
    }
    public ElMensaje contiene(String mensajeContenido){
        this.mensajeContenido=mensajeContenido;
        return this;
    }
    @Override
    public Boolean answeredBy(Actor actor) {
        return mensaje.contains(mensajeContenido);
    }
}
