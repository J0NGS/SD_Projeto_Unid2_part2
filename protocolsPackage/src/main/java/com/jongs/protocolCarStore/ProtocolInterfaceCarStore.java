package com.jongs.protocolCarStore;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProtocolInterfaceCarStore extends Remote {
    public String login(String login) throws RemoteException;
    public String createCar(String car) throws RemoteException;
    public String readCar(String id) throws RemoteException;
    public String update(String id, String car) throws RemoteException;
    public String removeStock(String id) throws RemoteException;
    public String addStock(String car) throws RemoteException;
    public List<String> getCars() throws RemoteException;
    public List<String> getModels() throws RemoteException;
    public List<String> searchCarByName(String name) throws RemoteException;
    public String searchCarByRenavam(String renavam) throws RemoteException;
    public boolean pingUserService() throws RemoteException;
    public boolean pingCarService() throws RemoteException;
}
