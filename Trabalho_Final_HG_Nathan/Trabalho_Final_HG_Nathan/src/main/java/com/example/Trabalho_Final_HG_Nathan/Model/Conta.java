package com.example.Trabalho_Final_HG_Nathan.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@Entity
@Table( name = "conta")
public class Conta {

    @Id
    private int id;
    @Column
    @NotBlank( message = "Numero não pode ser nulo")
    private int numero;
    @Column
    private double saldo;

    private Cliente cliente;

    private Agencia agencia;
    public Conta() {
    }

    public Conta(int id, int numero, double saldo) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + numero;
        long temp;
        temp = Double.doubleToLongBits(saldo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        if (id != other.id)
            return false;
        if (numero != other.numero)
            return false;
        if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Conta [id=" + id + ", numero=" + numero + ", saldo=" + saldo + "]";
    }
}
