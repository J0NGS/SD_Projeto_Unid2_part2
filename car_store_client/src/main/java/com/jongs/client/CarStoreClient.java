package com.jongs.client;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;


public class CarStoreClient {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException, IOException, NotBoundException {
        //definindo endereço do servidor
        ProtocolInterfaceCarStore server = null;
        boolean connection = false;
        //Procurando servidor no endereço
                do {
            System.out.println("Digite o ip do servidor. Exemplo: 10.0.0.157");
            String ipServer = scn.nextLine();
            try {
                String name = "rmi://"+ ipServer +":8085/carStore";
                server = (ProtocolInterfaceCarStore) Naming.lookup(name);
                connection = true;
            } catch (Exception e) {
                System.out.println("Erro ao conectar com o servidor, tente novamente");
                connection = false;
            }
        } while (!connection);
        
        int option = 50;
        try {
            //Inicioializando a classe Menu
            Menu menu = new Menu(server);
            do {
                //Exibindo menu de opções
                menu.optionsLogin();
                try {
                    option = Integer.parseInt(scn.nextLine());
                    menu.run(option);

                } catch (NumberFormatException e) {
                    System.out.println("Formato de entrada inválido!!!!!");
                }
            } while (option != 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}