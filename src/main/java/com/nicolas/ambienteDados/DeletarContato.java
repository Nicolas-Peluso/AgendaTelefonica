package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.nicolas.conection.Conection;

public class DeletarContato {

    public boolean DeletarUmContato(String nome){
        if(nome.isEmpty()){
            return false;
        }
        String nomeContato = nome;
        AmbienteDados principal = new AmbienteDados();
        int idContato = principal.SelecionaContatoIdPorNome(nomeContato, true, -1, "");

        if(idContato == -1){
            System.out.println("Erro na hora de pegar o ID "); //DEV
            return false;
        }

        try{
            Connection c = Conection.Conect();
            principal.setQuery("DELETE FROM contato WHERE idContato = ?");
            PreparedStatement stm = c.prepareStatement(principal.getQuery());
            stm.setInt(1, idContato);
            stm.executeUpdate();
            System.out.println("Contato deletado com sucesso");
        }catch(SQLException e){
            System.out.println(e);
        }

        return true;
    }
    
}
    

