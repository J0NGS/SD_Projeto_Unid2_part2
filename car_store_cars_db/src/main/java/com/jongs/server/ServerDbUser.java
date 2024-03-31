package com.jongs.server;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocolDb.Protocol;
import com.jongs.protocolDb.ProtocolInterfaceBd;



public class ServerDbUser {
    public static void main(String[] args) throws IOException {
        try {
            //Protocolo implementado
            ProtocolInterfaceBd protocol = new Protocol();
            //Endereço
            String name = "rmi://localhost:8082/carDb";
            
			LocateRegistry.createRegistry(8082);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            
            System.out.println("started cars bd");
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