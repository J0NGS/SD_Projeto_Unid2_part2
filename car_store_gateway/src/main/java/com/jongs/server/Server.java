package com.jongs.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.jongs.protocolCarService.ProtocolInterfaceCarService;
import com.jongs.protocolCarStore.Protocol;
import com.jongs.protocolCarStore.ProtocolInterfaceCarStore;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            String serverUserName = "rmi://localhost:8083/carStoreUser";
            String serverCarName = "rmi://localhost:8089/carStoreCar";

            // Procurando servidor do serviço de usuário
            System.out.println("-----------------------------------");

            System.out.println("try connect in user service...");
            ProtocolInterfaceUserService serverUser = (ProtocolInterfaceUserService) Naming.lookup(serverUserName);
            System.out.println("connected in user service.");
            System.out.println("-----------------------------------");
            // Procurando servidor do serviço de carros
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("try connect in car service...");
            ProtocolInterfaceCarService serverCar = (ProtocolInterfaceCarService) Naming.lookup(serverCarName);
            System.out.println("connected in car service.");
            System.out.println("-----------------------------------");
            System.out.println();

            // Protocolo implementado
            ProtocolInterfaceCarStore protocol = new Protocol(serverUser, serverCar);
            // Endereço
            String name = "rmi://localhost:8085/carStore";

            LocateRegistry.createRegistry(8085);
            // Registrando e associando o protocolo
            Naming.rebind(name, protocol);

            System.out.println("started carStore server...");
            System.out.println("Waiting for requests...");

            // Criando um arquivo para redirecionar a saída
            // File logFile = new
            // File("E:/Documentos/GitHub/joao-goncalo-pratica-off-1-main/src/Server/serverLog.txt");
            // PrintStream printStream = new PrintStream(new FileOutputStream(logFile));

            // Redirecionando a saída padrão
            // System.setOut(printStream);

            // System.out.println("started...");
            // System.out.println("Waiting for requests...");

            // Inicio do protocolo do servidor adicionando 50 carros
            // protocol.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}