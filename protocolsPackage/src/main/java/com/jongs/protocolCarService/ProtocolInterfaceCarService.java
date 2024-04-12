package com.jongs.protocolCarService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProtocolInterfaceCarService extends Remote{
    public String createCar(String car) throws RemoteException;
    public String addCarStock(String car) throws RemoteException;
    public String removeCarStock(String id) throws RemoteException;
    public List<String> listAllCars() throws RemoteException;
    public String listAllCarsModelQuantity() throws RemoteException;
}
