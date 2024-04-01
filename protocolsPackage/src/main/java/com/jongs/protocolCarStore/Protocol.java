package com.jongs.protocolCarStore;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jongs.protocolUserService.ProtocolInterfaceUserService;


public class Protocol extends UnicastRemoteObject implements ProtocolInterfaceCarStore, Serializable {
    ProtocolInterfaceUserService userService;

    public Protocol(ProtocolInterfaceUserService userService) throws RemoteException {
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
