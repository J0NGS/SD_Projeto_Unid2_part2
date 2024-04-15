package com.jongs.server;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocolCarDb.Protocol;
import com.jongs.protocolCarDb.ProtocolInterfaceCarBd;




public class ServerDbCar {
    public static void main(String[] args) throws IOException {
        try {
            //Protocolo implementado
            ProtocolInterfaceCarBd protocol = new Protocol();
            //Endereço
            String name = "rmi://localhost:8082/carDb";

            System.out.println("\r\n" + //
                                "  ______     ___      .______          _______.   .______    _______  \r\n" + //
                                " /      |   /   \\     |   _  \\        /       |   |   _  \\  |       \\ \r\n" + //
                                "|  ,----'  /  ^  \\    |  |_)  |      |   (----`   |  |_)  | |  .--.  |\r\n" + //
                                "|  |      /  /_\\  \\   |      /        \\   \\       |   _  <  |  |  |  |\r\n" + //
                                "|  `----./  _____  \\  |  |\\  \\----.----)   |      |  |_)  | |  '--'  |\r\n" + //
                                " \\______/__/     \\__\\ | _| `._____|_______/       |______/  |_______/ \r\n" + //
                                "                                                                      \r\n" + //
                                "");
            
			LocateRegistry.createRegistry(8082);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            System.out.println("--------------------------------------------");
            System.out.println("started cars bd");
            System.out.println("Waiting for requests...");
            System.out.println("--------------------------------------------");
			
            // Criando um arquivo para redirecionar a saída
            //File logFile = new File("car_store_user_bd/src/main/resources/DB_Log.txt");
            //PrintStream printStream = new PrintStream(new FileOutputStream(logFile));
            
            // Redirecionando a saída padrão
            //System.setOut(printStream); 
            
            //System.out.println("started...");
            //System.out.println("Waiting for requests...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}