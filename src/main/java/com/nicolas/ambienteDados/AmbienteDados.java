package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.nicolas.conection.Conection;

public class AmbienteDados {
    private String Query = "";
    private ArrayList<String> TempContactsName = new ArrayList<>();

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

    public void SelecionarTelefoneDoContato(String ConNome){
        int ContId = 0;
        try{
                Connection c = Conection.Conect();
                setQuery("SELECT idContato FROM contato WHERE nomeContato = ?;");
                PreparedStatement stm = c.prepareStatement(getQuery());
                stm.setString(1, ConNome);
                    try(ResultSet rs = stm.executeQuery()){
                        if(rs.next()){
                            ContId = rs.getInt("idContato");
                            try{
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
                        }else{
                            throw new SQLException("Nenhum Contato Com esse nome foi encontrado");
                        }
                    }
            }
        catch(SQLException e){
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
