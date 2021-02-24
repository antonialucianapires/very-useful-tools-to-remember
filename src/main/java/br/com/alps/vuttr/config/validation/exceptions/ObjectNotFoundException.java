package br.com.alps.vuttr.config.validation.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() {}

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
