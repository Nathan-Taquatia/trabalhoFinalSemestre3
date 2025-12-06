package com.example.Trabalho_Final_HG_Nathan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ParametroInvalidoException extends  RuntimeException {

    public ParametroInvalidoException(String mensagem) {
        super(mensagem);
    }
}