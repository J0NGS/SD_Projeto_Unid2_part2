package com.jongs.client;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.UnknownHostException;
import java.util.Scanner;

import com.jongs.protocol.ProtocolInterface;

public class Client {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException, IOException, NotBoundException {
        //definindo endereço do servidor
        String name = "rmi://localhost:1099/carStore";
        //Procurando servidor no endereço
        ProtocolInterface server = (ProtocolInterface) Naming.lookup(name);
        
        int option;
        try {
            //Inicializando a classe Menu
            Menu menu = new Menu(server);
            do {
                //Exibindo menu de opções
                menu.optionsLogin();
                option = scn.nextInt();
                //Executando a opção
                menu.run(option);
            } while (option != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}