package com.jongs.protocolCarService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProtocolInterfaceCarService extends Remote{
    public String createCar(String car) throws RemoteException;
    public String readCar(String id) throws RemoteException;
    public String updateCar(String id, String car) throws RemoteException;
    public String removeCarStock(String id) throws RemoteException;
    public String addCarStock(String car) throws RemoteException;
    public List<String> listAllCars() throws RemoteException;
    public List<String> listAllCarsModelQuantity() throws RemoteException;
    public List<String> listCarsByName(String name) throws RemoteException;
    public String searchCarByRenavam(String renavam) throws RemoteException;
    public boolean ping() throws RemoteException;
}
