package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.conection.Conection;

public class SelectDados {
    private AmbienteDados Selecionar = new AmbienteDados();
    
    public void SelecionarTodosOsNomesDosContatos(){
        StringBuilder str = new StringBuilder();
        try{
            Connection c = Conection.Conect();
            Selecionar.setQuery("SELECT nomeContato FROM contato;");
            PreparedStatement stm = c.prepareStatement(Selecionar.getQuery());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                str.append(rs.getString("nomeContato")).append(" | ");
            }
            System.out.println(str.toString());
        } catch(SQLException e){
            System.out.println(e);
        }
    }   

    public boolean SelecionarTelefoneDoContato(String ConNome, boolean ProcurarAgendaid, int agid, String msg){
        int ContId = this.Selecionar.SelecionaContatoIdPorNome(ConNome, ProcurarAgendaid, agid, msg);

        if(ContId == -1){
            System.out.println("Nao foi possivel encontrar o Id");
            return false;
        }
        try{
            Connection c = Conection.Conect();
            this.Selecionar.setQuery("SELECT DDD, TelefoneNumero FROM telefone WHERE idContato = ?;");
            PreparedStatement stm2 = c.prepareStatement(this.Selecionar.getQuery());
            stm2 = c.prepareStatement(this.Selecionar.getQuery());
            stm2.setInt(1, ContId);
            ResultSet rs2 = stm2.executeQuery();
            if(rs2.next()){
                System.out.print("("+rs2.getString("DDD")+") ");
                System.out.println(rs2.getString("TelefoneNumero"));
                return true;
            } else{
                throw new SQLException("Nenhum Contato Com esse nome foi encontrado");
            }
        } catch(SQLException erro){
            System.out.println(erro);
        }
        return false;
    }

    public boolean SelecionarEnderecoDoContato(String contNome, boolean ProcurarAgendaid, int agid, String msg){
        int id = this.Selecionar.SelecionaContatoIdPorNome(contNome, ProcurarAgendaid, agid, msg);
        if(id == -1){
            System.out.println("Nao foi possivel encontrar o Id");
            return false;
        }
        try{
            Connection c = Conection.Conect();
            this.Selecionar.setQuery("SELECT nomeDaRua, cidade, Bairo, numero FROM endereco WHERE idContato = ?");
            PreparedStatement stm = c.prepareStatement(this.Selecionar.getQuery());
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
                if(rs.next()){
                    System.out.println("Cidade: "+rs.getString("cidade"));
                    System.out.println("Bairro: "+rs.getString("Bairo"));
                    System.out.println("Rua: "+rs.getString("nomeDaRua"));
                    System.out.println("Numero Da casa: "+rs.getString("numero"));
                    return true;
                } else{
                    throw new SQLException("Contato nao encontrado verifque o nome, caso nao se lembre busque por todos os seus contatos");
                }
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
