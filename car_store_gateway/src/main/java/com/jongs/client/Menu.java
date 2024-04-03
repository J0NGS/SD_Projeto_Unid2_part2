package com.jongs.client;

import java.rmi.RemoteException;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.text.html.Option;

import com.jongs.entitys.User;
import com.jongs.entitys.Cars.Cars;
import com.jongs.entitys.Cars.EconomicCar;
import com.jongs.entitys.Cars.ExecutiveCar;
import com.jongs.entitys.Cars.IntermediaryCar;
import com.jongs.entitys.User.USER_POLICY;
import com.jongs.entitys.dto.LoginRequest;
import com.jongs.entitys.dto.UserResponse;
import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;

public class Menu {
    private ProtocolInterfaceCarStore server;
    private Optional<UserResponse> user;

    // Inicializa o servidor de interação
    public Menu(ProtocolInterfaceCarStore server) {
        this.server = server;
        this.user = Optional.empty();
    }

    // Função para limpar a tela
    public static void limpatela() {
        System.out.print(
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ");
    }

    // Metodo para exibir as opções do menu
    public void optionsMenuAdmin() {
        System.out.println("---------------------------------------");
        System.out.println("1. Inserir novo veicúlo");
        System.out.println("2. Atualizar veículo existente");
        System.out.println("3. Buscar veículo por RENAVAM");
        System.out.println("3. Buscar veículo por nome");
        System.out.println("5. Remover veículo existente");
        System.out.println("6. Listar todos os veículo");
        System.out.println("7. Quantidade de veículos salvos");
        System.out.println("0. Voltar para o menu principal");
        System.out.println("---------------------------------------");
    }

    public void optionsMenuUser() {
        System.out.println("---------------------------------------");
        System.out.println("1. Comprar um veículo");
        System.out.println("2. Buscar veículo por RENAVAM");
        System.out.println("3. Buscar veículo por nome");
        System.out.println("4. Listar todos os veículo");
        System.out.println("5. Quantidade de veículos salvos");
        System.out.println("0. Voltar para o menu principal");
        System.out.println("---------------------------------------");
    }

    public void optionsLogin() {
        System.out.println("---------------------------------------");
        System.out.println("1. Login");
        System.out.println("2. Deslogar");
        System.out.println("0. Encerrar");
        System.out.println("---------------------------------------");
    }


    public void switchCaseAdmin(int optionAdmin){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo!");
        optionsMenuUser();
        switch (optionAdmin) {
            case 1:
                System.out.println("---------------------------------------");
                System.out.println("Inserir novo veicúlo");
                System.out.println("---------------------------------------");
                System.out.println("Qual o nome do modelo ?");
                String name = scanner.nextLine();
                System.out.println("Qual o ano de fabricação do modelo ?");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.println("Qual o renavam ?");
                String renavam = scanner.nextLine();
                System.out.println("---------------------------------------");
                System.out.println("Qual a categoria do carro ?");
                System.out.println("1. Economico");
                System.out.println("2. Intermediario");
                System.out.println("Qualquer outro inteiro. Executivo");
                int category = Integer.parseInt(scanner.nextLine());
                
                if (category == 1) {
                    EconomicCar car = new EconomicCar(0, name, renavam, year);
                    try {
                        System.out.println(server.createCar(car.toString()));
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } if (category == 2) {
                    IntermediaryCar car = new IntermediaryCar(0, name, renavam, year);
                    try {
                        System.out.println(server.createCar(car.toString()));
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    ExecutiveCar car = new ExecutiveCar(0, name, renavam, year);
                    try {
                        System.out.println(server.createCar(car.toString()));
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    // Metodo para executar instrução de acordo com a opção selecionada
    public void run(int option) throws RemoteException {
        Scanner scn = new Scanner(System.in);
        switch (option) {
            // Exit
            case 0: {
                limpatela();
                scn.close();
                System.out.println("Fechando...");
                break;
            }
            // Login
            case 1: {
                limpatela();
                System.out.println("---------------------------------------");
                System.out.println("Qual o login ?");
                String login = scn.nextLine();
                System.out.println("Qual a senha ?");
                String password = scn.nextLine();
                
                LoginRequest request = new LoginRequest(login, password);
                String response = server.login(request.toString()); 

                if (response.equals("Error, erro na autenticação.")){
                    System.out.println("Error, erro na autenticação.");
                } else {
                    setUser(Optional.of(UserResponse.fromString(response)));
                    System.out.println("User logado!");
                    System.out.println("---------------------------------------");
                    int optionLogin = 50;
                    do {
                        if (user.get().policy().equals(USER_POLICY.ADMIN)){
                            optionsMenuAdmin();
                            optionLogin = Integer.getInteger(scn.nextLine());
                            switchCaseAdmin(optionLogin);                        
                        }
                    } while (optionLogin != 0);

                }
                break;
            }
            // Update
            case 2: {
                try {
                    limpatela();
                    if(user.isPresent()){
                        System.out.println("Usário logado-> " + user.get().login());
                    }else{
                        System.out.println("Nenhum usuário logado");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            // Search
            case 3: {
                limpatela();

                break;
            }
            default: {
                limpatela();
                System.out.println("OPÇÃO INVALIDA!!!!!!!!!!");
                break;
            }
        }

    }



    public ProtocolInterfaceCarStore getServer() {
        return this.server;
    }

    public void setServer(ProtocolInterfaceCarStore server) {
        this.server = server;
    }

    public Optional<UserResponse> getUser() {
        return this.user;
    }

    public void setUser(Optional<UserResponse> user) {
        this.user = user;
    }

}
