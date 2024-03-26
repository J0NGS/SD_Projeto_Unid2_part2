package com.jongs.protocolUserService;

import java.rmi.RemoteException;

import com.jongs.entitys.dto.LoginRequest;
import com.jongs.entitys.dto.UserRequest;
import com.jongs.protocolDb.ProtocolInterfaceBd;

public class Protocol implements ProtocolInterfaceUserService{
    private ProtocolInterfaceBd serverDb;


    public Protocol(ProtocolInterfaceBd serverDb) {
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
        if (response.equals("User not found")) {
            return "Error, usuário não existe.";
        } else {
            return response;
        }
    }


}
