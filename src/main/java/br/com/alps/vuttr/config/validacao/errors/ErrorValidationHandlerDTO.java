package br.com.alps.vuttr.config.validacao.errors;

public class ErrorValidationHandlerDTO {

    private String field;
    private String error;


    public ErrorValidationHandlerDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }


    public String getField() {
        return field;
    }


    public String getError() {
        return error;
    }
}
