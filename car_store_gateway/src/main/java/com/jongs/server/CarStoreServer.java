package com.jongs.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import com.jongs.protocolCarService.ProtocolInterfaceCarService;
import com.jongs.protocolCarStore.Protocol;
import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;

public class CarStoreServer {
    public static void main(String[] args) throws IOException {
        String serverUserName = "rmi://localhost:8083/carStoreUser";
        String serverCarName = "rmi://localhost:8089/carStoreCar";
        ProtocolInterfaceUserService serverUser;
        ProtocolInterfaceCarService serverCar;
        boolean connectUserService = false;
        boolean connectCarService = false;
        Scanner snc = new Scanner(System.in);

        try {
            serverUser = null;
            serverCar = null;

            // Procurando servidor do serviço de usuário
            System.out.println("-----------------------------------");
            while (!connectUserService) {
                try {
                    System.out.println("Qual o ip do server do serviceUser?");
                    serverUserName = snc.nextLine();
                    System.out.println("try connect in user service...");
                    serverUser = (ProtocolInterfaceUserService) Naming.lookup("rmi://" + serverUserName +":8083/carStoreUser");
                    connectUserService = true;
                    System.out.println("connected in user service.");
                    System.out.println("-----------------------------------");
                } catch (ConnectException e) {
                    System.out.println("connected in user service fail!!!!");
                    connectUserService = false;
                } catch (RemoteException e) {
                    System.out.println("connected in user service fail!!!!");
                    connectUserService = false;
                }
                Thread.sleep(1000);
            }
            // Procurando servidor do serviço de carros
            System.out.println();
            System.out.println("-----------------------------------");

            while (!connectCarService) {
                try {
                    System.out.println("Qual o ip do server do serviceCar?");
                    serverCarName = snc.nextLine();
                    System.out.println("try connect in car service...");
                    serverCar = (ProtocolInterfaceCarService) Naming.lookup("rmi://" + serverCarName + ":8089/carStoreCar");
                    connectCarService = true;
                    System.out.println("connected in car service.");
                    System.out.println("-----------------------------------");
                } catch (ConnectException e) {
                    System.out.println("connected in car service fail!!!!");
                    connectCarService = false;
                } catch (RemoteException e) {
                    System.out.println("connected in user service fail!!!!");
                    connectCarService = false;
                }
                Thread.sleep(1000);
            }
            System.out.println();

            // Protocolo implementado
            ProtocolInterfaceCarStore protocol = new Protocol(serverUser, serverCar);
            // Endereço
            String name = "rmi://localhost:8085/carStore";

            LocateRegistry.createRegistry(8085);
            // Registrando e associando o protocolo
            Naming.rebind(name, protocol);

            System.out.println("started carStore server...");
            System.out.println("Waiting for requests...");

            while (true) {
                try {
                    serverCar = (ProtocolInterfaceCarService) Naming.lookup(serverCarName);
                } catch (Exception e) {
                    System.out.println("Failed to connect to the server service. Retrying in 1 seconds...");
                    connectCarService = false;
                    while (!connectCarService) {
                        try {
                            System.out.println("try connect in car service...");
                            serverCar = (ProtocolInterfaceCarService) Naming.lookup(serverCarName);
                            protocol = new Protocol(serverUser, serverCar);
                            connectCarService = true;
                            Naming.rebind(name, protocol);
                        } catch (Exception e2) {
                            System.out.println("Failed to connect to the server service. Retrying in 1 second...");
                            Thread.sleep(1000);
                        }
                    }
                }
                try {
                    serverUser = (ProtocolInterfaceUserService) Naming.lookup(serverUserName);
                } catch (Exception e) {
                    System.out.println("Failed to connect to the server service. Retrying in 1 seconds...");
                    connectUserService = false;
                    while (!connectUserService) {
                        try {
                            System.out.println("try connect in user service...");
                            serverUser = (ProtocolInterfaceUserService) Naming.lookup(serverUserName);
                            protocol = new Protocol(serverUser, serverCar);
                            connectUserService = true;
                            Naming.rebind(name, protocol);
                        } catch (Exception e2) {
                            System.out.println("Failed to connect to the server service. Retrying in 1 second...");
                            Thread.sleep(1000);
                        }
                    }
                }
                Thread.sleep(1000);
            }

            // Criando um arquivo para redirecionar a saída
            // File logFile = new
            // File("E:/Documentos/GitHub/joao-goncalo-pratica-off-1-main/src/Server/serverLog.txt");
            // PrintStream printStream = new PrintStream(new FileOutputStream(logFile));

            // Redirecionando a saída padrão
            // System.setOut(printStream);

            // System.out.println("started...");
            // System.out.println("Waiting for requests...");

            // Inicio do protocolo do servidor adicionando 50 carros
            // protocol.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}