package com.example.trabalhofinalhgnathan.excption.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParametroInvalidoException extends  RuntimeException {

    public ParametroInvalidoException(String mensagem) {
        super(mensagem);
    }
}