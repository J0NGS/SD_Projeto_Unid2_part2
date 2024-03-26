package com.jongs.server;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class ServerUser {
    public static void main(String[] args) throws IOException {
        try {
            String serverDbName = "rmi://localhost:1099/userDb";
            //Procurando servidor do db
            System.out.println("trying connect in server db...");
            ProtocolInterfaceDb serverDb = (ProtocolInterfaceDb) Naming.lookup(serverDbName);
            System.out.println("connected in server db.");
            

            //Protocolo implementado
            ProtocolInterface protocol = new Protocol(serverDb);
            //Endereço
            String name = "rmi://localhost/carStoreUser";
            
			LocateRegistry.createRegistry(1099);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            
            System.out.println("started user service");
            System.out.println("Waiting for requests...");
			
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