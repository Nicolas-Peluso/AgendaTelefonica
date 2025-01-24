package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.nicolas.conection.Conection;
import com.nicolas.agendaContatos.Agenda;

public class CriarAgenda {
    
    public void Criar(String nomeAgenda){
        AmbienteDados principal = new AmbienteDados();
        Agenda.setNomeDono(nomeAgenda);

        try{
            Connection c = Conection.Conect();
            principal.setQuery("INSERT INTO agenda(nome) VALUES(?)");
            PreparedStatement stm = c.prepareStatement(principal.getQuery());
            stm.setString(1, nomeAgenda);
            stm.executeUpdate();
            System.out.println("Agenda Criada com Sucesso");
        }catch(SQLException e){
            System.out.println(e);
        }
    }

}
