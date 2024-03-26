package com.jongs.protocolUserService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProtocolInterfaceUserService extends Remote{
    public String createUser(String request) throws RemoteException;
    public String searchUserById(Integer id) throws RemoteException;
    public String authenticate(String request) throws RemoteException;
}
