package com.jongs.protocolDb;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.jongs.entitys.dto.UserRequest;

public interface ProtocolInterfaceBd extends Remote {
    public String create(String request) throws RemoteException;
    public String read(Integer id) throws RemoteException;
    public String update(Integer id) throws RemoteException;
    public String delete(Integer id) throws RemoteException;
    public String searchByLoginAndPassword(String login) throws RemoteException;
}

