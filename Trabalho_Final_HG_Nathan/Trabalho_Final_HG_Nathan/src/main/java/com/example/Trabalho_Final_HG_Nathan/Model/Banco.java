package com.example.Trabalho_Final_HG_Nathan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table( name = "banco")
public class Banco {
    @Id
    private int id;
    @Column
    @NotBlank( message = "Nome não pode ser nulo")
    private String nome;
    @Column
    @NotBlank( message = "Código não pode ser nulo")
    private int codigo;
    @Column
    @NotBlank( message = "CNPJ não pode ser nulo")
    private String cnpj;

    public Banco() {
    }

    public Banco(int id, String nome, int codigo, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.cnpj = cnpj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + codigo;
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
        Banco other = (Banco) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (codigo != other.codigo)
            return false;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Banco [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", cnpj=" + cnpj + "]";
    }
}
