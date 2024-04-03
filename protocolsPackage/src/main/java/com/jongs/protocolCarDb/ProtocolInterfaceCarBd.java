package com.jongs.protocolCarDb;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ProtocolInterfaceCarBd extends Remote {
    public String create(String request) throws RemoteException;
    public String read(Integer id) throws RemoteException;
    public List<String> SearchByName(String name) throws RemoteException;
    public String SearchByRenavam(String renavam) throws RemoteException;
    public List<String> getAll() throws RemoteException;
    public String getAllQuantity() throws RemoteException;
    public String update(Integer id, String str) throws RemoteException;
    public String delete(Integer id) throws RemoteException;
    public String addStock(String name, int year, Integer quantity) throws RemoteException;
    public String removeStock(String name, int year, Integer quantity) throws RemoteException;
}

