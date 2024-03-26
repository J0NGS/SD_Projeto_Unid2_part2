package com.jongs.client;

import java.rmi.RemoteException;
import java.util.Scanner;

import com.jongs.entitys.dto.LoginRequest;
import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;

public class Menu {
    private ProtocolInterfaceCarStore server;

    // Inicializa o servidor de interação
    public Menu(ProtocolInterfaceCarStore server) {
        this.server = server;
    }

    // Função para limpar a tela
    public static void limpatela() {
        System.out.print(
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ");
    }

    // Metodo para exibir as opções do menu
    public void optionsMenu() {
        System.out.println("---------------------------------------");
        System.out.println("1. Inserir novo veicúlo");
        System.out.println("2. Atualizar veículo existente");
        System.out.println("3. Buscar veículo por RENAVAM");
        System.out.println("4. Buscar veículo por placa");
        System.out.println("5. Remover veículo existente");
        System.out.println("6. Listar todos os veículo");
        System.out.println("7. Quantidade de veículos salvos");
        System.out.println("0. Sair");
        System.out.println("---------------------------------------");
    }

    public void optionsLogin() {
        System.out.println("---------------------------------------");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar Funcionário");
        System.out.println("3. Login");
        System.out.println("0. Sair");
        System.out.println("---------------------------------------");
    }

    // Metodo para executar instrução de acordo com a opção selecionada
    public void run(int option) throws RemoteException {
        Scanner scn = new Scanner(System.in);

        switch (option) {
            // Exit
            case 0: {
                limpatela();
                System.out.println("Fechando...");
                break;
            }
            // Login
            case 1: {
                limpatela();
                break;
            }
            // Update
            case 2: {
                try {
                    limpatela();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            // Search
            case 3: {
                limpatela();
                System.out.println("---------------------------------------");
                System.out.println("Qual o login ?");
                String login = scn.nextLine();
                System.out.println("Qual a senha ?");
                String password = scn.nextLine();
                
                LoginRequest request = new LoginRequest(login, password);

                if (server.login(request.toString()).equals("Error, erro na autenticação.")){
                    System.out.println("Error, erro na autenticação.");
                } else {
                    System.out.println("User autenticado");
                }
                break;
            }
            default: {
                limpatela();
                System.out.println("OPÇÃO INVALIDA!!!!!!!!!!");
                break;
            }
        }
    }
}
