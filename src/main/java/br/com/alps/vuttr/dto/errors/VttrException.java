package br.com.alps.vuttr.dto.errors;

public class VttrException extends RuntimeException {

    public VttrException() {}

    public VttrException(String message) {
        super(message);
    }
}
