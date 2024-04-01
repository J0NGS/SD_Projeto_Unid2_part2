package com.jongs.protocolUserService;

import java.io.Serializable;
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
        return serverDb.create(userRequest.toString());
    }

    @Override
    public String searchUserById(Integer id) throws RemoteException {
        return serverDb.read(id);
    }

    @Override
    public String authenticate(String request) throws RemoteException {
        LoginRequest loginRequest = LoginRequest.fromString(request);
        String response = serverDb.searchByLoginAndPassword(loginRequest.toString());
        if (response.equals("404,User not found")) {
            return "Error, user not found";
        } else {
            return response;
        }
    }


}
