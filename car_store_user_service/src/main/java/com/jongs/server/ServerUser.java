package com.jongs.server;

import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import com.jongs.protocolUserDb.ProtocolInterfaceUserBd;
import com.jongs.protocolUserService.Protocol;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;


public class ServerUser implements Runnable {
    private String serverDbName;
    private String name = "rmi://localhost:8083/carStoreUser";
    private boolean connected = false;
    private ProtocolInterfaceUserBd serverDb;
    private Scanner snc = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            try {
                //Procurando servidor do db
                System.out.println("\r\n" + //
                        " __    __       _______. _______ .______          _______.        _______. _______ .______     ____    ____  __    ______  _______ \r\n" + //
                        "|  |  |  |     /       ||   ____||   _  \\        /       |       /       ||   ____||   _  \\    \\   \\  /   / |  |  /      ||   ____|\r\n" + //
                        "|  |  |  |    |   (----`|  |__   |  |_)  |      |   (----`      |   (----`|  |__   |  |_)  |    \\   \\/   /  |  | |  ,----'|  |__   \r\n" + //
                        "|  |  |  |     \\   \\    |   __|  |      /        \\   \\           \\   \\    |   __|  |      /      \\      /   |  | |  |     |   __|  \r\n" + //
                        "|  `--'  | .----)   |   |  |____ |  |\\  \\----.----)   |      .----)   |   |  |____ |  |\\  \\----.  \\    /    |  | |  `----.|  |____ \r\n" + //
                        " \\______/  |_______/    |_______|| _| `._____|_______/       |_______/    |_______|| _| `._____|   \\__/     |__|  \\______||_______|\r\n" + //
                        "                                                                                                                                   \r\n" + //
                        "");
                System.out.println("--------------------------------------------");
                System.out.println("Trying to connect to the server db...");
                while (!connected) {
                    try {
                        System.out.println("Qual o ip do server db?");
                        serverDbName = snc.nextLine();
                        serverDb = (ProtocolInterfaceUserBd) Naming.lookup("rmi://" + serverDbName +":8080/userDb");
                        System.out.println("Connected to the server db.");
                        connected = true;
                    } catch (Exception e) {
                        System.out.println("Failed to connect to the server db. Retrying in 1 second...");
                        Thread.sleep(1000); // Aguarda 5 segundos antes de tentar novamente
                    }
                }

                System.out.println("--------------------------------------------");

                //Protocolo implementado
                ProtocolInterfaceUserService protocol = new Protocol(serverDb);
                //Endereço

                LocateRegistry.createRegistry(8083);
                //Registrando e associando o protocolo
                Naming.rebind(name, protocol);
                System.out.println("--------------------------------------------");
                System.out.println("Started user service");
                System.out.println("Waiting for requests...");
                System.out.println("--------------------------------------------");

                // Loop infinito para manter o serviço em execução
                while (true) {
                    try {
                        protocol.ping();
                    } catch (Exception e) {
                        System.out.println("Failed to connect to the server db. Retrying in 1 seconds...");
                        connected = false;
                        while (!connected) {
                            try {
                                serverDb = (ProtocolInterfaceUserBd) Naming.lookup("rmi://" + serverDbName +":8080/userDb");
                                protocol = new Protocol(serverDb);
                                Naming.rebind(name, protocol);
                                connected = true;
                                System.out.println("Connected to the server db.");
                            } catch (Exception e2) {
                                System.out.println("Failed to connect to the server db. Retrying in 1 second...");
                                Thread.sleep(1000); // Aguarda 1 segundo antes de tentar novamente
                            }
                        }
                    }
                    Thread.sleep(100); // Aguarda 100ms antes de verificar novamente
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

