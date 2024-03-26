package com.jongs.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;



public class ServerDbUser {
    public static void main(String[] args) throws IOException {
        try {
            //Protocolo implementado
            ProtocolInterface protocol = new Protocol();
            //Endereço
            String name = "rmi://localhost/userDb";
            
			LocateRegistry.createRegistry(1099);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            
            System.out.println("started user bd");
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