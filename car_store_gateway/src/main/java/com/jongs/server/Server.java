package com.jongs.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocol.Protocol;
import com.jongs.protocol.ProtocolInterface;
import com.jongs.protocol.ProtocolInterfaceUser;


public class Server {
    public static void main(String[] args) throws IOException {
        try {
            String serverUserName = "rmi://localhost:1099/carStoreUser";
            String serverCarName = "rmi://localhost:1099/carStoreCar";
            
            //Procurando servidor do db
            System.out.println("try connect in user service...");
            ProtocolInterfaceUser serverUser = (ProtocolInterfaceUser) Naming.lookup(serverUserName);
            System.out.println("connected in user service.");

            //Protocolo implementado
            ProtocolInterface protocol = new Protocol(serverUser);
            //Endereço
            String name = "rmi://localhost/carStore";
            
			LocateRegistry.createRegistry(1099);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            
            System.out.println("started carStore server...");
            System.out.println("Waiting for requests...");
			
            // Criando um arquivo para redirecionar a saída
            //File logFile = new File("E:/Documentos/GitHub/joao-goncalo-pratica-off-1-main/src/Server/serverLog.txt");
            //PrintStream printStream = new PrintStream(new FileOutputStream(logFile));
            
            // Redirecionando a saída padrão
            //System.setOut(printStream); 
            
            //System.out.println("started...");
            //System.out.println("Waiting for requests...");

            //Inicio do protocolo do servidor adicionando 50 carros
            //protocol.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}