package com.nicolas.agendaContatos;
import java.util.ArrayList;

public class Agenda {

    private String nomeDono;
    private ArrayList<Contatos> contatos;
    private Contatos CacheContatos;
    private ArrayList<String> Aux = new ArrayList<>();

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public ArrayList<Contatos> getContatos() {
        return contatos;
    }

    public void setContatos(ArrayList<Contatos> contatos) {
        this.contatos = contatos;
        this.setCacheContatos(contatos.getFirst());
    }

    public Contatos getCacheContatos() {
        return CacheContatos;
    }

    public void setCacheContatos(Contatos CacheContatos) {
        this.CacheContatos = CacheContatos;
    }

    public String getEnderecoContato(String nome) {
        if (this.TemEmCache(nome)) {
            this.Aux.add("Cidade:");
            this.Aux.add(this.getCacheContatos().getEndereço().getCidade());
            this.Aux.add("Bairro:");
            this.Aux.add(this.getCacheContatos().getEndereço().getBairro());
            this.Aux.add("rua:");
            this.Aux.add(this.getCacheContatos().getEndereço().getRua());
            this.Aux.add("Numero Da Casa:");
            this.Aux.add(this.getCacheContatos().getEndereço().getNumeroCasa());

            return this.formataString(this.Aux);
        }

        for (Contatos c : contatos) {
            if (c.getNome().equals(nome) && c.getEndereço() != null) {
                this.setCacheContatos(c);
                this.Aux.add("Cidade:");
                this.Aux.add(c.getEndereço().getCidade());
                this.Aux.add("Bairro:");
                this.Aux.add(c.getEndereço().getBairro());
                this.Aux.add("rua:");
                this.Aux.add(c.getEndereço().getRua());
                this.Aux.add("Numero Da Casa:");
                this.Aux.add(c.getEndereço().getNumeroCasa());
                
                return this.formataString(this.Aux);
            }
        }
        return "Contato nao Enconrado verifique o nome digitado";
    }

    public boolean TemEmCache(String Nome) {
        if (getCacheContatos() != null && getCacheContatos().getNome().equals(Nome)) {
            return true;
        }
        return false;
    }

    private String formataString(ArrayList<String> args) {
        StringBuilder str = new StringBuilder();

        for (String s : args) {
            str.append(s);
        }
        this.Aux.clear();
        return str.toString();
    }

    public String getNuemroDoContato(String Nome) {
        if (this.TemEmCache(Nome)) {
            this.Aux.add("Telefone:");
            this.Aux.add(getCacheContatos().getTelefone().getDdd());
            this.Aux.add(getCacheContatos().getTelefone().getNumero());

            return this.formataString(this.Aux);
        }

        for (Contatos c : contatos) {
            if (c.getNome().equals(Nome) && c.getTelefone() != null) {
                this.setCacheContatos(c);
                this.Aux.add("Telefone:");
                this.Aux.add(c.getTelefone().getDdd());
                this.Aux.add(c.getTelefone().getNumero());

                return this.formataString(this.Aux);
            }
        }
        return "Contato nao Enconrado verifique o nome digitado";
    }

}
