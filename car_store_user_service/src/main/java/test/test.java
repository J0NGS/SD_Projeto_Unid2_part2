package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jongs.entitys.dto.LoginRequest;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;

public class test {
    public static void main(String[] args) {
        String serverUserName = "rmi://localhost:8083/carStoreUser";
        System.out.println("try connect in user service...");
        ProtocolInterfaceUserService serverUser = null;
        try {
            serverUser = (ProtocolInterfaceUserService) Naming.lookup(serverUserName);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("connected in user service.");
        System.out.println("-----------------------------------");

        LoginRequest user = new LoginRequest("admin", "admin");
        try {
            System.out.println(serverUser.authenticate(user.toString()));
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
