package com.jongs.protocolCarService;

import java.io.Serializable;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
        try {
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
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public String addCarStock(String car) throws RemoteException {

        try {
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
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }

    }

    @Override
    public String removeCarStock(String id) throws RemoteException {
        try {
            String response = serverBd.removeStock(id);
            String[] parts = response.split(",");
            String code = parts[0];
            String message = parts[1];
            if (code.equals("200")) {
                return "Car removed!";
            } else {
                return "Error!!!!\n" + "Message-> " + message;
            }
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public List<String> listAllCars() throws RemoteException {
        try {
            return serverBd.getAll();
        } catch (ConnectException e) {
            System.out.println(e);
            List<String> response = new ArrayList<>();
            response.add("Error, database is offline");
            return response;
        } catch (RemoteException e) {
            System.out.println(e);
            List<String> response = new ArrayList<>();
            response.add("Error, database is offline");
            return response;
        }
    }

    @Override
    public List<String> listAllCarsModelQuantity() throws RemoteException {
        try {
            return serverBd.getAllWithQuantity();
        } catch (ConnectException e) {
            System.out.println(e);
            List<String> response = new ArrayList<>();
            response.add("Error, database is offline");
            return response;
        } catch (RemoteException e) {
            System.out.println(e);
            List<String> response = new ArrayList<>();
            response.add("Error, database is offline");
            return response;
        }
    }

    @Override
    public List<String> listCarsByName(String name) throws RemoteException {
        try {
            return serverBd.SearchByName(name);
        } catch (ConnectException e) {
            System.out.println(e);
            List<String> response = new ArrayList<>();
            response.add("Error, database is offline");
            return response;
        } catch (RemoteException e) {
            System.out.println(e);
            List<String> response = new ArrayList<>();
            response.add("Error, database is offline");
            return response;
        }
    }

    @Override
    public String updateCar(String id, String car) throws RemoteException {
        try {
            int idInt = Integer.parseInt(id);
            String response = serverBd.update(idInt, car);
            String parts[] = response.split(",");
            if (parts[0].equals("200")) {
                return "Car updated!";
            } else {
                return "Error-> message: " + parts[1];
            }
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public String readCar(String id) throws RemoteException {
        try {
            int idInt = Integer.parseInt(id);
            return serverBd.read(idInt);
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public String searchCarByRenavam(String renavam) throws RemoteException {
        try {
            String response = serverBd.SearchByRenavam(renavam);
            String parts[] = response.split(",");
            if (parts[0].equals("404")) {
                return "Error-> message: " + parts[1];
            } else {
                return response;
            }
        } catch (ConnectException e) {
            System.out.println(e);
            return "Error, database is offline";
        } catch (RemoteException e) {
            System.out.println(e);
            return "Error, database is offline";
        }
    }

    @Override
    public boolean ping() throws RemoteException {
        return true;
    }
}
