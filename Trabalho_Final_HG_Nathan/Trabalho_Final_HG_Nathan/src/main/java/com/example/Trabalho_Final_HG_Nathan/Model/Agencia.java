package com.example.Trabalho_Final_HG_Nathan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "agencia")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Número não pode ser nulo")
    private Integer numero;

    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    public Agencia() { }

    public Agencia(Integer numero, String nome, Banco banco) {
        this.numero = numero;
        this.nome = nome;
        this.banco = banco;
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
