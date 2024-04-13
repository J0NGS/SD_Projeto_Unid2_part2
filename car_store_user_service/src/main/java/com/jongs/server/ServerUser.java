package com.jongs.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocolUserDb.ProtocolInterfaceUserBd;
import com.jongs.protocolUserService.Protocol;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;


public class ServerUser implements Runnable{
    @Override    
    public void run() {
            try {
                String serverDbName = "rmi://localhost:8080/userDb";
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
                System.out.println("trying connect in server db...");
                ProtocolInterfaceUserBd serverDb = (ProtocolInterfaceUserBd) Naming.lookup(serverDbName);
                System.out.println("connected in server db.");
                System.out.println("--------------------------------------------");
                
            
                //Protocolo implementado
                ProtocolInterfaceUserService protocol = new Protocol(serverDb);
                //Endereço
                String name = "rmi://localhost:8083/carStoreUser";
                
                LocateRegistry.createRegistry(8083);
                //Registrando e associando o protocolo
                Naming.rebind(name, protocol);
                System.out.println("--------------------------------------------");
                System.out.println("started user service");
                System.out.println("Waiting for requests...");
                System.out.println("--------------------------------------------");
                while (true) {
                   Thread.sleep(100); // Aguarda 100ms antes de verificar novamente
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
			
            // Criando um arquivo para redirecionar a saída
            //File logFile = new File("car_store_user_bd/src/main/resources/DB_Log.txt");
            //PrintStream printStream = new PrintStream(new FileOutputStream(logFile));
            
            // Redirecionando a saída padrão
            //System.setOut(printStream); 
            
            //System.out.println("started...");
            //System.out.println("Waiting for requests...");
    }
}