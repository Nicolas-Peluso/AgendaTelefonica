package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.nicolas.conection.Conection;

public class AmbienteDados {
    private String Query = "";
    private Map<String, Integer> TempoAgendaNomes = new HashMap<String, Integer>();

    public int SelecionaContatoIdPorNome(String nome, boolean GetAdengaId, int idA, String msg){
        int AgendaId = idA;
        if(GetAdengaId){
            AgendaId = this.SelecionarAgendaId(msg);
        }
        try{
            Connection c = Conection.Conect();
            setQuery("SELECT idContato FROM contato WHERE agendaId = ? AND nomeContato = ?;");
            PreparedStatement stm = c.prepareStatement(getQuery());
            stm.setInt(1, AgendaId);
            stm.setString(2, nome);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("idContato");
            }
        }catch(SQLException e){
            System.out.println(e);
        }

        return -1;
    }

    public int SelecionarAgendaId(String msg){
        try{
            Connection c = Conection.Conect();
            setQuery("SELECT nome, agendaId FROM agenda");
            PreparedStatement stm = c.prepareStatement(getQuery());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TempoAgendaNomes.put(rs.getString("nome"), rs.getInt("agendaId")); //adicionar a resposta do banco em um dicionario, para referencia futura, sem ter chamar novamente
            }
            int idA = this.PainelAgendas(msg);
            return idA;
        } catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }

    private int PainelAgendas(String MensagemDinamica){
        Scanner scan = new Scanner(System.in);
        String nome;
        StringBuilder strAgendaNomes = new StringBuilder();

        for (String chave : TempoAgendaNomes.keySet()) {
            strAgendaNomes.append(chave)
            .append(" | ");
        }
        
        while(true){
            if(MensagemDinamica.isEmpty()){
                System.out.println("Digite o nome da agenda onde o contato esta: ");
            }else{
                System.out.println(MensagemDinamica);
            }
            System.out.println(strAgendaNomes.toString());
            nome = scan.nextLine();
            if(TempoAgendaNomes.get(nome) != null){
                return TempoAgendaNomes.get(nome);
            }
            System.out.println("Nome deve ser exato ao exibido a baixo: ");
        }
}
    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        this.Query = "";
        this.Query = query;
    }    
}   
