package com.jongs.protocolUserService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.jongs.entitys.dto.LoginRequest;
import com.jongs.protocolUserDb.ProtocolInterfaceUserBd;

public interface ProtocolInterfaceUserService extends Remote{
    public String createUser(String request) throws RemoteException;
    public String searchUserById(Integer id) throws RemoteException;
    public String authenticate(String user) throws RemoteException;
    public void setServerBd(String serverDb) throws RemoteException;
    public boolean ping() throws RemoteException;
}
