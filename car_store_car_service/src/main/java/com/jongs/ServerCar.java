package com.jongs;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import com.jongs.protocolCarDb.ProtocolInterfaceCarBd;
import com.jongs.protocolCarService.Protocol;
import com.jongs.protocolCarService.ProtocolInterfaceCarService;
import com.jongs.protocolUserDb.ProtocolInterfaceUserBd;

public class ServerCar implements Runnable {
    private String serverDbName;
    private String name = "rmi://localhost:8089/carStoreCar";
    private boolean connected = false;
    private Scanner snc = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            try {

                System.out.println("\r\n" + //
                        "  ______     ___      .______              _______. _______ .______     ____    ____  __    ______  _______ \r\n"
                        + //
                        " /      |   /   \\     |   _  \\            /       ||   ____||   _  \\    \\   \\  /   / |  |  /      ||   ____|\r\n"
                        + //
                        "|  ,----'  /  ^  \\    |  |_)  |          |   (----`|  |__   |  |_)  |    \\   \\/   /  |  | |  ,----'|  |__   \r\n"
                        + //
                        "|  |      /  /_\\  \\   |      /            \\   \\    |   __|  |      /      \\      /   |  | |  |     |   __|  \r\n"
                        + //
                        "|  `----./  _____  \\  |  |\\  \\----.   .----)   |   |  |____ |  |\\  \\----.  \\    /    |  | |  `----.|  |____ \r\n"
                        + //
                        " \\______/__/     \\__\\ | _| `._____|   |_______/    |_______|| _| `._____|   \\__/     |__|  \\______||_______|\r\n"
                        + //
                        "                                                                                                            \r\n"
                        + //
                        "");
                System.out.println("--------------------------------------------");
                System.out.println("trying connect in server car db...");

                ProtocolInterfaceCarBd serverDb = null;
                while (!connected) {
                    try {
                        System.out.println("Qual o ip do server db?");
                        serverDbName = snc.nextLine();
                        serverDb = (ProtocolInterfaceCarBd) Naming.lookup("rmi://" + serverDbName +":8082/carDb");
                        System.out.println("connected in server car db.");
                        connected = true;
                    } catch (Exception e) {
                        System.out.println("Failed to connect to the server db. Retrying in 1 second...");
                        Thread.sleep(1000); // Aguarda 5 segundos antes de tentar novamente
                    }

                }
                System.out.println("--------------------------------------------");

                // Protocolo implementado
                ProtocolInterfaceCarService protocol = new Protocol(serverDb);
                // Endereço
                LocateRegistry.createRegistry(8089);
                // Registrando e associando o protocolo
                Naming.rebind(name, protocol);
                System.out.println("--------------------------------------------");
                System.out.println("started car service");
                System.out.println("Waiting for requests...");
                System.out.println("--------------------------------------------");
                while (true) {
                    try {
                        serverDb.ping();
                    } catch (Exception e) {
                        System.out.println("Failed to connect to the server db. Retrying in 1 seconds...");
                        connected = false;
                        while (!connected) {
                            try {
                                serverDb = (ProtocolInterfaceCarBd) Naming.lookup("rmi://" + serverDbName +":8082/carDb");
                                protocol = new Protocol(serverDb);
                                connected = true;
                                Naming.rebind(name, protocol);
                                System.out.println("Connected to the server db.");
                            } catch (Exception e2) {
                                System.out.println("Failed to connect to the server db. Retrying in 1 second...");
                                Thread.sleep(1000); // Aguarda 5 segundos antes de tentar novamente
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