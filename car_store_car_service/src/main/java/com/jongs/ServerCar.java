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
                String serverDbName = "rmi://localhost:8082/carDb";
                //Procurando servidor do db
                System.out.println("trying connect in server car db...");
                ProtocolInterfaceCarBd serverDb = (ProtocolInterfaceCarBd) Naming.lookup(serverDbName);
                System.out.println("connected in server car db.");
                
            
                //Protocolo implementado
                ProtocolInterfaceCarService protocol = new Protocol(serverDb);
                //Endereço
                String name = "rmi://localhost:8089/carStoreCar";
                
                LocateRegistry.createRegistry(8089);
                //Registrando e associando o protocolo
                Naming.rebind(name, protocol);
                
                System.out.println("started car service");
                System.out.println("Waiting for requests...");
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