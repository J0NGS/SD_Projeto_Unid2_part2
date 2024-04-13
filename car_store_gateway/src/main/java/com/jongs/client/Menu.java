package com.jongs.client;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.text.html.Option;

import com.jongs.entitys.User;
import com.jongs.entitys.Cars.Cars;
import com.jongs.entitys.Cars.EconomicCar;
import com.jongs.entitys.Cars.ExecutiveCar;
import com.jongs.entitys.Cars.IntermediaryCar;
import com.jongs.entitys.User.USER_POLICY;
import com.jongs.entitys.dto.CarListResponse;
import com.jongs.entitys.dto.LoginRequest;
import com.jongs.entitys.dto.UserResponse;
import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;

public class Menu {
    private ProtocolInterfaceCarStore server;
    private Optional<UserResponse> user;
    private int optionMenuAdmin;
    private int optionMenuUser;

    // Inicializa o servidor de interação
    public Menu(ProtocolInterfaceCarStore server) {
        this.server = server;
        this.user = Optional.empty();
        this.optionMenuAdmin = 50;
        this.optionMenuUser = 50;
    }

    // Função para limpar a tela
    public static void limpatela() {
        System.out.print(
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ");
    }

    // Metodo para exibir as opções do menu
    public void optionsMenuAdmin() {
        System.out.println("---------------------------------------");
        System.out.println("1. Inserir novo modelo de veicúlo");
        System.out.println("2. Inserir novo veicúlo de modelo existente");
        System.out.println("3. Atualizar veículo existente");
        System.out.println("4. Buscar veículo por RENAVAM");
        System.out.println("5. Buscar veículo por nome");
        System.out.println("6. Remover veículo");
        System.out.println("7. Listar todos os veículo");
        System.out.println("8. Quantidade de veículos salvos");
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
                String parts[] = response.split(",");
                if (parts[0].equals("404")) {
                    System.out.println(parts[1]);
                } else {
                    setUser(Optional.of(UserResponse.fromString(response)));
                    System.out.println("User logado!");
                    System.out.println("---------------------------------------");

                    /*USUÁRIO EMPLYEE LOGADO */
                    if (user.get().policy().equals(USER_POLICY.EMPLOYEE)) {
                        optionsMenuAdmin();
                        optionMenuAdmin = Integer.parseInt(scn.nextLine());
                        while (optionMenuAdmin != 0) {
                            switch (optionMenuAdmin) {
                                case 1:
                                    System.out.println("---------------------------------------");
                                    System.out.println("Qual o modelo do carro? (Marca Nome, Exemplo: Hyundai HB20)");
                                    String carName = scn.nextLine();
                                    System.out.println("Qual o ano de fabricação do carro?");
                                    int carYear = Integer.parseInt(scn.nextLine());
                                    System.out.println("Qual o renavam do carro?");
                                    String carRenavam = scn.nextLine();
                                    System.out.println("Qual a categoria ?");
                                    System.out.println("1. Econômico");
                                    System.out.println("2. Intermediario");
                                    System.out.println("Qualquer outro inteiro. Executivo");
                                    int carCategory = Integer.parseInt(scn.nextLine());
                                    if (carCategory == 1) {
                                        EconomicCar economicCar = new EconomicCar(0, carName, carRenavam, carYear);
                                        System.out.println(server.createCar(economicCar.toString()));
                                    } else if (carCategory == 2) {
                                        IntermediaryCar intermediaryCar = new IntermediaryCar(0, carName, carRenavam,
                                                carYear);
                                        System.out.println(server.createCar(intermediaryCar.toString()));
                                    } else {
                                        ExecutiveCar executiveCar = new ExecutiveCar(0, carName, carRenavam, carYear);
                                        System.out.println(server.createCar(executiveCar.toString()));

                                    }
                                    break;

                                case 2:
                                    System.out.println("---------------------------------------");
                                    System.out.println("Qual o modelo do carro? (Marca Nome, Exemplo: Hyundai HB20)");
                                    String carAddName = scn.nextLine();
                                    System.out.println("Qual o ano de fabricação do carro?");
                                    int carAddYear = Integer.parseInt(scn.nextLine());
                                    System.out.println("Qual o renavam do carro?");
                                    String carAddRenavam = scn.nextLine();
                                    System.out.println("Qual a categoria ?");
                                    System.out.println("1. Econômico");
                                    System.out.println("2. Intermediario");
                                    System.out.println("Qualquer outro inteiro. Executivo");
                                    int carAddCategory = Integer.parseInt(scn.nextLine());
                                    if (carAddCategory == 1) {
                                        EconomicCar economicCar = new EconomicCar(0, carAddName, carAddRenavam,
                                                carAddYear);
                                        System.out.println(server.addStock(economicCar.toString()));
                                    } else if (carAddCategory == 2) {
                                        IntermediaryCar intermediaryCar = new IntermediaryCar(0, carAddName,
                                                carAddRenavam,
                                                carAddYear);
                                        System.out.println(server.addStock(intermediaryCar.toString()));
                                    } else {
                                        ExecutiveCar executiveCar = new ExecutiveCar(0, carAddName, carAddRenavam,
                                                carAddYear);
                                        System.out.println(server.addStock(executiveCar.toString()));

                                    }
                                    break;
                                case 3:
                                    System.out.println("---------------------------------------");
                                    System.out.println("Qual o id do carro que você deseja editar?");
                                    String carIdUpdate = scn.nextLine();
                                    System.out.println("---------------------------------------");
                                    if (server.readCar(carIdUpdate).equals("404,car not found")) {
                                        System.out.println("Carro com id não encontrado");
                                        break;
                                    } else {
                                        System.out.println(server.readCar(carIdUpdate));
                                        System.out.println("==========ATUALIZAÇÃO=========");
                                        System.out
                                                .println("Qual o modelo do carro? (Marca Nome, Exemplo: Hyundai HB20)");
                                        String carUpdateName = scn.nextLine();
                                        System.out.println("Qual o ano de fabricação do carro?");
                                        int carUpdateYear = Integer.parseInt(scn.nextLine());
                                        System.out.println("Qual o renavam do carro?");
                                        String carUpdateRenavam = scn.nextLine();
                                        System.out.println("Qual a categoria ?");
                                        System.out.println("1. Econômico");
                                        System.out.println("2. Intermediario");
                                        System.out.println("Qualquer outro inteiro. Executivo");
                                        int carUpdateCategory = Integer.parseInt(scn.nextLine());
                                        if (carUpdateCategory == 1) {
                                            EconomicCar economicCar = new EconomicCar(0, carUpdateName,
                                                    carUpdateRenavam,
                                                    carUpdateYear);
                                            System.out.println(server.update(carIdUpdate,economicCar.toString()));
                                        } else if (carUpdateCategory == 2) {
                                            IntermediaryCar intermediaryCar = new IntermediaryCar(0, carUpdateName,
                                                    carUpdateRenavam,
                                                    carUpdateYear);
                                            System.out.println(server.update(carIdUpdate,intermediaryCar.toString()));
                                        } else {
                                            ExecutiveCar executiveCar = new ExecutiveCar(0, carUpdateName,
                                                    carUpdateRenavam,
                                                    carUpdateYear);
                                            System.out.println(server.update(carIdUpdate,executiveCar.toString()));

                                        }
                                    }
                                    break;
                                case 4:
                                    System.out.println("---------------------------------------");
                                    System.out.println("Qual o renavam do carro que você deseja buscar?");
                                    String carRenavamSearch = scn.nextLine();
                                    System.out.println("---------------------------------------");
                                    System.out.println(server.searchCarByRenavam(carRenavamSearch));
                                    System.out.println("---------------------------------------");
                                    break;
                                case 5:
                                    System.out.println("---------------------------------------");
                                    System.out.println("Qual o nome do carro que você deseja buscar?");
                                    String carNameSearch = scn.nextLine();
                                    System.out.println("---------------------------------------");
                                    System.out.println("RESULTADOS...");
                                    System.out.println("---------------------------------------");
                                    for (String resultsOffNameSearch : server.searchCarByName(carNameSearch)) {
                                        System.out.println();
                                        System.out.println(resultsOffNameSearch);
                                        System.out.println();
                                    }
                                    System.out.println("---------------FIM---------------------");
                                    break;
                                case 6:
                                    System.out.println("---------------------------------------");
                                    System.out.println("Qual o id do carro que você deseja remover?");
                                    System.out.println();
                                    System.out.println(server.removeStock(scn.nextLine()));
                                    System.out.println("---------------------------------------");
                                    break;
                                case 7:
                                    System.out.println("---------------------------------------");
                                    List<String> allCars = server.getCars();
                                    for (String string : allCars) {
                                        System.out.println(string);
                                    }
                                    System.out.println("---------------------------------------");
                                    break;

                                case 8:
                                    System.out.println("---------------------------------------");
                                    for (String responseModels : server.getModels()) {
                                        System.out.println(responseModels);
                                    }
                                    System.out.println();
                                    System.out.println("---------------------------------------");
                                    break;
                                default:
                                    System.out.println("Opção inválida!!!!!!!!!!!");
                                    break;
                            }
                            optionsMenuAdmin();
                            optionMenuAdmin = Integer.parseInt(scn.nextLine());
                        }
                        System.out.println("Até mais!");
                    } else {
                    }
                }
                break;
            }
            // Update
            case 2: {
                try {
                    limpatela();
                    user = Optional.ofNullable(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            // Search
            case 3: {
                limpatela();
                if (user.isPresent()) {
                    System.out.println("Usário logado-> " + user.get().login());
                } else {
                    System.out.println("Nenhum usuário logado");
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
