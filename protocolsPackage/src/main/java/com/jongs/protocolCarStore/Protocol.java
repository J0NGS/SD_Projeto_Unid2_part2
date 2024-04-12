package com.jongs.protocolCarStore;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.jongs.protocolCarService.ProtocolInterfaceCarService;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;


public class Protocol extends UnicastRemoteObject implements ProtocolInterfaceCarStore, Serializable {
    ProtocolInterfaceUserService userService;
    ProtocolInterfaceCarService carService;

    public Protocol(ProtocolInterfaceUserService userService,ProtocolInterfaceCarService carService) throws RemoteException {
        this.userService = userService;
        this.carService = carService;
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

    @Override
    public String createCar(String car) throws RemoteException {
        return carService.createCar(car);
    }

    @Override
    public List<String> getCars() throws RemoteException {
        return carService.listAllCars();
    }

    @Override
    public String getModels() throws RemoteException {
        return carService.listAllCarsModelQuantity();
    }

    @Override
    public String addStock(String car) throws RemoteException {
        return carService.addCarStock(car);
    }

    @Override
    public String removeStock(String id) throws RemoteException {
        return carService.removeCarStock(id);
    }

}
