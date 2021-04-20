package co.com.reto2021.wolox.exceptions;

public class ElMensajeValidado extends AssertionError{

    public ElMensajeValidado(String message, Throwable cause) {
        super(message, cause);
    }
}
