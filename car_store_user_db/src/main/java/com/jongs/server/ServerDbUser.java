package com.jongs.server;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import com.jongs.protocolUserDb.ProtocolInterfaceUserBd;
import com.jongs.protocolUserDb.Protocol;




public class ServerDbUser {
    public static void main(String[] args) throws IOException {
        try {
            //Protocolo implementado
            ProtocolInterfaceUserBd protocol = new Protocol();
            //Endereço
            String name = "rmi://localhost:8080/userDb";
            System.out.println("\r\n" + //
                                " __    __       _______. _______ .______          _______.   .______    _______  \r\n" + //
                                "|  |  |  |     /       ||   ____||   _  \\        /       |   |   _  \\  |       \\ \r\n" + //
                                "|  |  |  |    |   (----`|  |__   |  |_)  |      |   (----`   |  |_)  | |  .--.  |\r\n" + //
                                "|  |  |  |     \\   \\    |   __|  |      /        \\   \\       |   _  <  |  |  |  |\r\n" + //
                                "|  `--'  | .----)   |   |  |____ |  |\\  \\----.----)   |      |  |_)  | |  '--'  |\r\n" + //
                                " \\______/  |_______/    |_______|| _| `._____|_______/       |______/  |_______/ \r\n" + //
                                "                                                                                 \r\n" + //
                                "");
			LocateRegistry.createRegistry(8080);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            System.out.println("--------------------------------------------");
            System.out.println("started user bd");
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