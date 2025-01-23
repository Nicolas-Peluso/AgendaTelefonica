package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.nicolas.agendaContatos.Contatos;
import com.nicolas.agendaContatos.Endereco;
import com.nicolas.agendaContatos.Telefone;
import com.nicolas.conection.Conection;

public class AmbienteDados {
    private String Query = "";
    private ArrayList<String> TempContactsName = new ArrayList<>();
    private Map<String, Integer> TempoAgendaNomes = new HashMap<String, Integer>();

    public ArrayList<String> SelecionarTodosOsNomesDosContatos(){
        if(!getTempContactsName().isEmpty()){
            return TempContactsName; //Quando um novo contato for add Zerar o valor.
        }

        try{
            Connection c = Conection.Conect();
            setQuery("SELECT * FROM contato;");
            PreparedStatement stm = c.prepareStatement(getQuery());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                setTempContactsName(getTempContactsName(), rs.getString("nomeContato"));
            }
            rs.close();
            c.close();
            return getTempContactsName();
        }catch(SQLException e){
            System.out.println(e);
        }
        return getTempContactsName(); //só retorna vazio se tiver dado erro
    }

    private int SelecionaContatoIdPorNome(String nome){
        try{
            Connection c = Conection.Conect();
            setQuery("SELECT idContato FROM contato WHERE nomeContato = ?;");
            PreparedStatement stm = c.prepareStatement(getQuery());
            stm.setString(1, nome);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("idContato");
            }
        }catch(SQLException e){
            System.out.println(e);
        }

        return -1;
    }

    public boolean SelecionarTelefoneDoContato(String ConNome){
        int ContId = this.SelecionaContatoIdPorNome(ConNome);
        if(ContId == -1){
            System.out.println("Nao foi possivel encontrar o Id");
            return false;
        }
        try{
            Connection c = Conection.Conect();
            setQuery("SELECT DDD, TelefoneNumero FROM telefone WHERE idContato = ?;");
            PreparedStatement stm2 = c.prepareStatement(getQuery());
            stm2 = c.prepareStatement(getQuery());
            stm2.setInt(1, ContId);
            ResultSet rs2 = stm2.executeQuery();
            if(rs2.next()){
                System.out.print(rs2.getString("DDD"));
                System.out.println(rs2.getString("TelefoneNumero"));
            } else{
                throw new SQLException("Nenhum Contato Com esse nome foi encontrado");
            }
        } catch(SQLException erro){
            System.out.println(erro);
        }
        return true;
    }

    public boolean SelecionarEnderecoDoContato(String contNome){
        int id = SelecionaContatoIdPorNome(contNome);
        if(id == -1){
            System.out.println("Nao foi possivel encontrar o Id");
            return false;
        }
        try{
            Connection c = Conection.Conect();
            setQuery("SELECT nomeDaRua, cidade, Bairo, numero FROM endereco WHERE idContato = ?");
            PreparedStatement stm = c.prepareStatement(getQuery());
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
                if(rs.next()){
                    System.out.println("Cidade: "+rs.getString("cidade"));
                    System.out.println("Bairro: "+rs.getString("Bairo"));
                    System.out.println("Rua: "+rs.getString("nomeDaRua"));
                    System.out.println("Numero Da casa: "+rs.getString("numero"));
                } else{
                    throw new SQLException("Contato nao encontrado verifque o nome, caso nao se lembre busque por todos os seus contatos");
                }
        }catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }

    private int SelecionarAgendaId(){
        try{
            Connection c = Conection.Conect();
            setQuery("SELECT nome, agendaId FROM agenda");
            PreparedStatement stm = c.prepareStatement(getQuery());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                TempoAgendaNomes.put(rs.getString("nome"), rs.getInt("agendaId")); //adicionar a resposta do banco em um dicionario, para referencia futura sem ter chamar novamente
            }
            int idA = this.PainelAgendas();
            return idA;
        } catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }

    private int PainelAgendas(){
        Scanner scan = new Scanner(System.in);
        String nome;
        StringBuilder strAgendaNomes = new StringBuilder();

        for (String chave : TempoAgendaNomes.keySet()) {
            strAgendaNomes.append(chave)
            .append(" | ");
        }
        
        while(true){
            System.out.println("Digite o nome da agenda que voce quer adicionar esse novo contato: ");
            System.out.println(strAgendaNomes.toString());
            nome = scan.next();
            if(TempoAgendaNomes.get(nome) != null){
                scan.close();
                return TempoAgendaNomes.get(nome);
            }
            System.out.println("Nome deve ser exato ao exibido a baixo: ");
        }
}

    public boolean AdicionarUmContato(Contatos contInterno){
        int AgendaId = SelecionarAgendaId();
        if(AgendaId == -1){
            System.out.println("Algo deu errado com a busca da agenda tente novamente! ");
            return false;
        }   
            try{
                Connection c = Conection.Conect();
                setQuery("INSERT INTO contato(nomeContato, agendaId) VALUES(?, ?)");
                PreparedStatement stm = c.prepareStatement(getQuery());
                stm.setString(1, contInterno.getNome());
                stm.setInt(2, AgendaId);
                stm.executeUpdate();                
                try{
                    int idContato = SelecionaContatoIdPorNome(contInterno.getNome());
                    AdicionarUmTelefoneAUmContato(idContato, contInterno.getTelefone());               
                    AdicionarUmEnderecoAUmContato(idContato, contInterno.getEndereço());          
                } catch(Exception e){
                    System.out.println(e);
                }
            } catch(SQLException e){
                System.out.println(e);
            }
            return true;
        }

    private void AdicionarUmTelefoneAUmContato(int contId, Telefone tel){
        try{
            Connection c = Conection.Conect();
            setQuery("INSERT INTO telefone(TelefoneNumero, idContato, DDD, tipoAparelho) VALUES(?, ?, ?, ?)");
            PreparedStatement stm = c.prepareStatement(getQuery());
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
            setQuery("INSERT INTO endereco(idContato, nomeDaRua, cidade, Bairo, numero) VALUES(?, ?, ?, ?, ?)");
            PreparedStatement stm = c.prepareStatement(getQuery());
            stm.setInt(1, contId);
            stm.setString(2, end.getRua());
            stm.setString(3, end.getCidade());
            stm.setString(4, end.getBairro());
            stm.setString(5, end.getNumeroCasa());
            stm.executeUpdate();
            System.out.println("Contato Adicionado Com Sucesso!");
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    private String getQuery() {
        return Query;
    }

    private void setQuery(String query) {
        this.Query = "";
        this.Query = query;
    }

    public ArrayList<String> getTempContactsName() {
        return TempContactsName;
    }

    public void setTempContactsName(ArrayList<String> tempContactsName, String elemento) {
        tempContactsName.add(elemento);
    }
    
}   
