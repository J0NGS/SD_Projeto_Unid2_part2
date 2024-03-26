package com.jongs.protocol;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jongs.entitys.dto.UserResponse;


public class Protocol extends UnicastRemoteObject implements ProtocolInterface {
    ProtocolInterfaceUser userService;

    public Protocol(ProtocolInterfaceUser userService) throws RemoteException {
        this.userService = userService;
    }

    @Override
    public String login(String login) throws RemoteException {
        String response = userService.authenticate(login);
        if (response.equals("Error, usuário não existe.")) {
            return "Error, erro na autenticação.";
        } else {
            return response;
        }
    }

}
