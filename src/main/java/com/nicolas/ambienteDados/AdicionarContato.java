package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.agendaContatos.Contatos;
import com.nicolas.agendaContatos.Endereco;
import com.nicolas.agendaContatos.Telefone;
import com.nicolas.conection.Conection;

public class AdicionarContato {
    private AmbienteDados Adicionando = new AmbienteDados();

    public boolean AdicionarUmContato(Contatos contInterno, String msg){
        int AgendaId = this.Adicionando.SelecionarAgendaId(msg);
        if(AgendaId == -1){
            System.out.println("Algo deu errado com a busca da agenda tente novamente! ");
            return false;
        }   
            try{
                Connection c = Conection.Conect();
                this.Adicionando.setQuery("INSERT INTO contato(nomeContato, agendaId) VALUES(?, ?)");
                PreparedStatement stm = c.prepareStatement(this.Adicionando.getQuery());
                stm.setString(1, contInterno.getNome());
                stm.setInt(2, AgendaId);
                stm.executeUpdate();
                int idContato = this.Adicionando.SelecionaContatoIdPorNome(contInterno.getNome(), false, AgendaId, "");
                AdicionarUmTelefoneAUmContato(idContato, contInterno.getTelefone());               
                AdicionarUmEnderecoAUmContato(idContato, contInterno.getEndereço());                
                System.out.println("Contato adicionado com sucesso!");   
            } catch(SQLException e){
                System.out.println(e);
            }
            return true;
        }

    private void AdicionarUmTelefoneAUmContato(int contId, Telefone tel){
        try{
            Connection c = Conection.Conect();
            this.Adicionando.setQuery("INSERT INTO telefone(TelefoneNumero, idContato, DDD, tipoAparelho) VALUES(?, ?, ?, ?)");
            PreparedStatement stm = c.prepareStatement(this.Adicionando.getQuery());
            stm.setString(1, tel.getNumero()); 
            stm.setInt(2, contId); 
            stm.setString(3, tel.getDdd()); 
            stm.setString(4, tel.getTipo()); 
            stm.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    private void AdicionarUmEnderecoAUmContato(int contId, Endereco end){
        try{
            Connection c = Conection.Conect();
            this.Adicionando.setQuery("INSERT INTO endereco(idContato, nomeDaRua, cidade, Bairo, numero) VALUES(?, ?, ?, ?, ?)");
            PreparedStatement stm = c.prepareStatement(this.Adicionando.getQuery());
            stm.setInt(1, contId);
            stm.setString(2, end.getRua());
            stm.setString(3, end.getCidade());
            stm.setString(4, end.getBairro());
            stm.setString(5, end.getNumeroCasa());
            stm.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
