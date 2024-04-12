package com.jongs.protocolCarService;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import com.jongs.entitys.Cars.Cars;
import com.jongs.protocolCarDb.ProtocolInterfaceCarBd;

public class Protocol implements ProtocolInterfaceCarService, Serializable {
    private ProtocolInterfaceCarBd serverBd;

    public Protocol(ProtocolInterfaceCarBd serverBd) {
        this.serverBd = serverBd;
    }

    @Override
    public String createCar(String car) throws RemoteException {
        Cars carEntity = Cars.fromString(car);
        String response = serverBd.create(carEntity.toString());
        String[] parts = response.split(",");
        String code = parts[0];
        String message = parts[1];

        if (code.equals("200")) {
            return "Car created!";
        } else {
            return "Error!!!!\n" + "Message-> " + message;
        }
    }

    @Override
    public String addCarStock(String car) throws RemoteException {
        Cars carEntity = Cars.fromString(car);
        String response = serverBd.addStock(carEntity.toString());
        String[] parts = response.split(",");
        String code = parts[0];
        String message = parts[1];
        if (code.equals("200")) {
            return "Car add in stock!";
        } else {
            return "Error!!!!\n" + "Message-> " + message;
        }

    }

    @Override
    public String removeCarStock(String id) throws RemoteException {
        String response = serverBd.removeStock(id);
        String[] parts = response.split(",");
        String code = parts[0];
        String message = parts[1];
        if (code.equals("200")) {
            return "Car removed!";
        } else {
            return "Error!!!!\n" + "Message-> " + message;
        }
    }

    @Override
    public List<String> listAllCars() throws RemoteException {
        return serverBd.getAll();
    }

    @Override
    public String listAllCarsModelQuantity() throws RemoteException {
       return serverBd.getAllQuantity();
    }

    
    
}
