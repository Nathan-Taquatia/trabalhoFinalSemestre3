package com.example.trabalhofinalhgnathan.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

// para saque e depósito
public class TransacaoDTO {
      
    @NotNull
    @DecimalMin("0.01")
    private Double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}