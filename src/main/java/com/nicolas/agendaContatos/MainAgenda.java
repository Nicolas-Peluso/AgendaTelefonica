package com.nicolas.agendaContatos;

import java.util.Scanner;
import com.nicolas.ambienteDados.AmbienteDados;

public class MainAgenda {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean PainelAddContato = false;
        AmbienteDados mani = new AmbienteDados();

        while (true) {
            System.out.println("Digite Uma Opção: ");
            System.out.println("1 - Ver o nome de todos os contatos: ");
            System.out.println("2 - Buscar o telefone de um contato esspecifico: ");
            System.out.println("3 - Adicionar um contato: ");
            System.out.println("4 - Alterar um contato: ");
            System.out.println("5 - Deletar um contato: ");
            System.out.println("6 - Criar uma nova agenda: ");
            System.out.println("7 - Buscar endereco de um contato: ");

            int opt = scan.nextInt();

            switch (opt) {
                case 1:
                    mani.SelecionarTodosOsNomesDosContatos();
                    break;
                case 2:
                    System.out.println("Qual o nome do Contato");
                    String nome = scan.next();
                    mani.SelecionarTelefoneDoContato(nome);
                    scan.close();
                    break;
                case 3:
                    PainelAddContato = true;
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Qual o nome do Contato");
                    String nomeC = scan.next();
                    mani.SelecionarEnderecoDoContato(nomeC);
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
            tel.setTipo(scan.next());
            scan.nextLine();
            cont1.setTelefone(tel);
            tel = null;
            end = null;
            mani.AdicionarUmContato(cont1); //Adicionando contato no banco
            cont1 = null;
            PainelAddContato = false;
        }

        scan.close();
    }
}
