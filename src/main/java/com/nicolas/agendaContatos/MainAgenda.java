package com.nicolas.agendaContatos;

import java.util.ArrayList;
import java.util.Scanner;

public class MainAgenda {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Agenda AgendaNicolas = new Agenda();
        System.out.println("Qual o nome dessa Agenda: ");
        AgendaNicolas.setNomeDono(scan.next());
        ArrayList<Contatos> contatos = new ArrayList<>();
        boolean PainelAddContato = true;
        String Continue = "";

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

        AgendaNicolas.setContatos(contatos);
        boolean painel = true;
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
