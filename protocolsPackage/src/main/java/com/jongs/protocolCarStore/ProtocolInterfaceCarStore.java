package com.jongs.protocolCarStore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProtocolInterfaceCarStore extends Remote {
    public String login(String login) throws RemoteException;
    public String createCar(String car) throws RemoteException;
    public String addStock(String car) throws RemoteException;
    public String removeStock(String id) throws RemoteException;
    public List<String> getCars() throws RemoteException;
    public String getModels() throws RemoteException;
}
