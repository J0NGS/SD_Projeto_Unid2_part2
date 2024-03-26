package com.jongs.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocolDb.ProtocolInterfaceBd;
import com.jongs.protocolUserService.Protocol;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;


public class ServerUser implements Runnable{
    @Override    
    public void run() {
            try {
                String serverDbName = "rmi://localhost:1099/userDb";
                //Procurando servidor do db
                System.out.println("trying connect in server db...");
                ProtocolInterfaceBd serverDb = (ProtocolInterfaceBd) Naming.lookup(serverDbName);
                System.out.println("connected in server db.");
                
            
                //Protocolo implementado
                ProtocolInterfaceUserService protocol = new Protocol(serverDb);
                //Endereço
                String name = "rmi://localhost:8080/carStoreUser";
                
                LocateRegistry.createRegistry(8080);
                //Registrando e associando o protocolo
                Naming.rebind(name, protocol);
                
                System.out.println("started user service");
                System.out.println("Waiting for requests...");
                while (true) {
                    Thread.sleep(1000); // Aguarda 1 segundo antes de verificar novamente
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