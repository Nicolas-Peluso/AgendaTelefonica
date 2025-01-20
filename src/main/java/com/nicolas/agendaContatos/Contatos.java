package com.nicolas.agendaContatos;

public class Contatos {

    private String nome;
    private Endereco endereço;
    private Telefone Telefone;

    public Endereco getEndereço() {
        return endereço;
    }

    public void setEndereço(Endereco endereço) {
        this.endereço = endereço;
    }

    public Telefone getTelefone() {
        return Telefone;
    }

    public void setTelefone(Telefone Telefone) {
        this.Telefone = Telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
