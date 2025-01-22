package com.nicolas.agendaContatos;

import java.util.ArrayList;
import java.util.Scanner;
import com.nicolas.ambienteDados.AmbienteDados;

public class MainAgenda {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Agenda AgendaNicolas = new Agenda();
        System.out.println("Qual o nome dessa Agenda: ");
        AgendaNicolas.setNomeDono(scan.next());
        ArrayList<Contatos> contatos = new ArrayList<>();
        boolean PainelAddContato = false;
        String Continue = "";
        AmbienteDados mani = new AmbienteDados();

        while (true) {
            System.out.println("Digite Uma Opção: ");
            System.out.println("1 - Ver o nome de todos os contatos: ");
            System.out.println("2 - Buscar o telefone de um contato esspecifico: "); //essa opção vai virar "buscar um contato esspecifico" retornando todas as colunas referente ao contato
            System.out.println("3 - Adicionar um contato: ");
            System.out.println("4 - Alterar um contato: ");
            System.out.println("5 - Deletar um contato: ");
            System.out.println("6 - Criar uma nova agenda: ");


            int opt = scan.nextInt();

            switch (opt) {
                case 1:
                    mani.SelecionarTodosOsNomesDosContatos();
                    break;
                case 2:
                    System.out.println("Qual o nome do Contato");
                    String nome = scan.next();
                    mani.SelecionarTelefoneDoContato(nome);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("opção invalida");
                    break;
            }
            break;
        }

        while (PainelAddContato) {
            Contatos cont1 = new Contatos();
            Telefone tel = new Telefone();
            Endereco end = new Endereco();
            System.out.println("Digite o Nome Do Contato: ");
            cont1.setNome(scan.next().toLowerCase());
            scan.nextLine();

            System.out.println("Digite o Cidade Do " + cont1.getNome());
            end.setCidade(scan.next());
            scan.nextLine();
            System.out.println("Digite o Bairro Do " + cont1.getNome());
            end.setBairro(scan.next());
            scan.nextLine();
            System.out.println("Digite a Rua Do " + cont1.getNome());
            end.setRua(scan.next());
            scan.nextLine();
            System.out.println("Digite o  numero da casa Do " + cont1.getNome());
            end.setNumeroCasa(scan.next());
            scan.nextLine();
            cont1.setEndereço(end);

            System.out.println("Digite o DDD do telefone do " + cont1.getNome());
            tel.setDdd(scan.next());
            scan.nextLine();
            System.out.println("Digite o numero do telefone do " + cont1.getNome());
            tel.setNumero(scan.next());
            scan.nextLine();
            System.out.println("Digite o tipo de Aparelho do " + cont1.getNome());
            tel.setNumero(scan.next());
            scan.nextLine();
            cont1.setTelefone(tel);
            tel = null;
            end = null;
            contatos.add(cont1);
            cont1 = null;

            //INSERIR CONTATO CRIADO NA AGENDA Esspecificada.

            while (true) {
                System.out.println("Deseja Adicionar Mais algum contato? sim/nao ");
                Continue = scan.next().toLowerCase();
                if (Continue.equals("sim")) {
                    PainelAddContato = true;
                    break;
                } else if (Continue.equals("nao")) {
                    PainelAddContato = false;
                    break;
                } else {
                    System.err.println("Invalido responda com sim ou nao");
                }
            }
        }

        //AgendaNicolas.setContatos(contatos);
        boolean painel = false;
        while (painel) {
            System.out.println("Digite o nome do contado que voce quer o numero: ");
            String nome = scan.next().toLowerCase();
            String NumeroContato = AgendaNicolas.getNuemroDoContato(nome);
            String EnderecoContato = AgendaNicolas.getEnderecoContato(nome);

            System.out.println(EnderecoContato);
            System.out.println(NumeroContato);
            
            boolean e;
            do {
                System.out.println("Sair? ");
                e = !scan.nextBoolean();
            } while (e != false && e != true);
            painel = e;
        }
        scan.close();
    }
}
