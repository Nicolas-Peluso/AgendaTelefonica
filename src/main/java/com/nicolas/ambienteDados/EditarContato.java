package com.nicolas.ambienteDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.nicolas.conection.Conection;

public class EditarContato {

    public String painelEdit(){
        Scanner scanEdit = new Scanner(System.in);
        int opt = 0;
        System.out.println("1 - Editar Nome do contato");
        System.out.println("2 - Editar Numero de telefone");
        System.out.println("3 - Editar DDD do contato");
        System.out.println("4 - Editar Nome da Rua do");
        System.out.println("5 - Editar Nome do Bairro do");
        System.out.println("6 - Editar Nome da cidade");
        System.out.println("7 - Editar Numero da casa");
        opt = scanEdit.nextInt();
        switch(opt){
            case 1:
                return "UPDATE contato SET nomeContato = ? WHERE idContato = ?";
            case 2:
                return "UPDATE telefone SET TelefoneNumero = ? WHERE idContato = ?";
            case 3:
                return "UPDATE telefone SET DDD=? WHERE idContato = ?";
            case 4:
                return "UPDATE endereco SET nomeDaRua=? WHERE idContato = ?";
            case 5:
                return "UPDATE endereco SET Bairo=? WHERE idContato = ?";
            case 6:
                return "UPDATE endereco SET cidade=? WHERE idContato = ?";
            case 7:
                return "UPDATE endereco SET numero=? WHERE idContato = ?";
            default:
                System.out.println("Opção invalida");
                break;
        }
        return "";
    }

    public boolean EditarContatoPainel(String NomeDoContato){
        String resContinue = "";
        String NomeDoContatoV = NomeDoContato;
        AmbienteDados principal = new AmbienteDados();
        SelectDados selecionar = new SelectDados();
        Scanner scanN = new Scanner(System.in);
        int IdAgenda = principal.SelecionarAgendaId("");
        
        while(true){
            if(IdAgenda == -1){
                System.out.println("Erro na hora de Buscar o id da agenda");
                break;
            }
            boolean fone = selecionar.SelecionarEnderecoDoContato(NomeDoContatoV,false, IdAgenda, "");
            boolean tel = selecionar.SelecionarTelefoneDoContato(NomeDoContato, false, IdAgenda, "");
            
            int contatoId = principal.SelecionaContatoIdPorNome(NomeDoContatoV, false, IdAgenda, "");

            if((tel == false || fone == false) && contatoId == -1){
                System.out.println("Contato nao encontrado verifque o nome");
                scanN.close();
                return false;
            }

            String q = this.painelEdit();
                if(q.isEmpty()){
                    System.out.println("Algo deu errado na manipulação das querys"); //Dev
                    break;
                }

            System.out.println("Digite o Novo Valor: ");
            String NovoValor = "";
            NovoValor = scanN.nextLine();

            try{
                Connection c = Conection.Conect();
                principal.setQuery(q);
                PreparedStatement stm = c.prepareStatement(principal.getQuery());
                stm.setString(1, NovoValor);
                stm.setInt(2, contatoId);
                stm.executeUpdate();
                System.out.println("Contato editado com sucesso!");
            }
            catch(SQLException e){
                System.out.println(e);
            }
            System.out.println("Continuar Editando? sim/nao ");
            resContinue = scanN.nextLine().toLowerCase();
            if(!resContinue.equals("sim")){
                break;
            }
        }
        scanN.close();
        return true;
    }
}
