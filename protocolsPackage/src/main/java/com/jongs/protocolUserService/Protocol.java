package com.jongs.protocolUserService;

import java.io.Serializable;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import com.jongs.entitys.dto.LoginRequest;
import com.jongs.entitys.dto.UserRequest;
import com.jongs.protocolUserDb.ProtocolInterfaceUserBd;

public class Protocol implements ProtocolInterfaceUserService, Serializable{
    private static final long serialVersionUID = 1L;
    
    
    private ProtocolInterfaceUserBd serverDb;

    public Protocol(ProtocolInterfaceUserBd serverDb) {
        this.serverDb = serverDb;
    }

    @Override
    public String createUser(String request) throws RemoteException {
        UserRequest userRequest = UserRequest.fromString(request);
        try {
            return this.serverDb.create(userRequest.toString());
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public String searchUserById(Integer id) throws RemoteException {
        try{
            return this.serverDb.read(id);
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public String authenticate(String request) throws RemoteException {
        try {
            return this.serverDb.searchByLoginAndPassword(request);
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public void setServerBd(String serverDbName) throws RemoteException {
        try {
            this.serverDb = (ProtocolInterfaceUserBd) Naming.lookup(serverDbName);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar servidor no serviço de usuário");
            e.printStackTrace();
        }
    }

    @Override
    public boolean ping() throws RemoteException{
        return true;
    }
 
}
