package com.jongs;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocolCarDb.ProtocolInterfaceCarBd;
import com.jongs.protocolCarService.Protocol;
import com.jongs.protocolCarService.ProtocolInterfaceCarService;


public class ServerCar implements Runnable{
    @Override    
    public void run() {
            try {

                System.out.println("\r\n" + //
                                        "  ______     ___      .______              _______. _______ .______     ____    ____  __    ______  _______ \r\n" + //
                                        " /      |   /   \\     |   _  \\            /       ||   ____||   _  \\    \\   \\  /   / |  |  /      ||   ____|\r\n" + //
                                        "|  ,----'  /  ^  \\    |  |_)  |          |   (----`|  |__   |  |_)  |    \\   \\/   /  |  | |  ,----'|  |__   \r\n" + //
                                        "|  |      /  /_\\  \\   |      /            \\   \\    |   __|  |      /      \\      /   |  | |  |     |   __|  \r\n" + //
                                        "|  `----./  _____  \\  |  |\\  \\----.   .----)   |   |  |____ |  |\\  \\----.  \\    /    |  | |  `----.|  |____ \r\n" + //
                                        " \\______/__/     \\__\\ | _| `._____|   |_______/    |_______|| _| `._____|   \\__/     |__|  \\______||_______|\r\n" + //
                                        "                                                                                                            \r\n" + //
                                        "");
                System.out.println("--------------------------------------------");
                String serverDbName = "rmi://localhost:8082/carDb";
                //Procurando servidor do db
                System.out.println("trying connect in server car db...");
                ProtocolInterfaceCarBd serverDb = (ProtocolInterfaceCarBd) Naming.lookup(serverDbName);
                System.out.println("connected in server car db.");
                System.out.println("--------------------------------------------");
                
            
                //Protocolo implementado
                ProtocolInterfaceCarService protocol = new Protocol(serverDb);
                //Endereço
                String name = "rmi://localhost:8089/carStoreCar";
                
                LocateRegistry.createRegistry(8089);
                //Registrando e associando o protocolo
                Naming.rebind(name, protocol);
                System.out.println("--------------------------------------------");
                System.out.println("started car service");
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