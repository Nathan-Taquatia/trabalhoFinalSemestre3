package com.example.Trabalho_Final_HG_Nathan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table( name = "agencia")
public class Agencia{

    @Id
    private int id;
    @Column
    @NotBlank( message = "Nome não pode ser nulo")
    private int numero;
    @Column
    @NotBlank( message = "Nome não pode ser nulo")
    private String nome;

    private Banco banco;



    public Agencia() {
    }

    public Agencia(int id, int numero, String nome) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + numero;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Agencia other = (Agencia) obj;
        if (id != other.id)
            return false;
        if (numero != other.numero)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Agencia [id=" + id + ", numero=" + numero + ", nome=" + nome + "]";
    }

}
