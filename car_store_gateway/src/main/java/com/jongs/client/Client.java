package com.jongs.client;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;


public class Client {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException, IOException, NotBoundException {
        //definindo endereço do servidor
        String name = "rmi://localhost:8085/carStore";
        //Procurando servidor no endereço
        ProtocolInterfaceCarStore server = (ProtocolInterfaceCarStore) Naming.lookup(name);
        
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