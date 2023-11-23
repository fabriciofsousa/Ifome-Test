package br.com.ada.ifome.pessoa;

import lombok.Data;

public class Pessoa {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    private String nome;
    private String cep;

    public boolean validaCpf() {
        return this.cpf != null && !this.cpf.isBlank();
    }

    public boolean validaCpf(String cpf) {
        return cpf.length() == 11;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
