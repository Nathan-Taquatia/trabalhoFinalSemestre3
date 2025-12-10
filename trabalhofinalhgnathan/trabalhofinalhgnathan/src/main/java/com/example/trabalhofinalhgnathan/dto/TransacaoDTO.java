package com.example.trabalhofinalhgnathan.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

// para saque e depósito
public class TransacaoDTO {
      
    // Valor da transação (saque ou depósito).
    @NotNull
    @DecimalMin("0.01")
    private Double valor;

    // Getter e Setter
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}