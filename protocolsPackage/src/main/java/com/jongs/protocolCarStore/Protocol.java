package com.jongs.protocolCarStore;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import com.jongs.protocolCarService.ProtocolInterfaceCarService;
import com.jongs.protocolUserService.ProtocolInterfaceUserService;


public class Protocol extends UnicastRemoteObject implements ProtocolInterfaceCarStore, Serializable {
    ProtocolInterfaceUserService userService;
    ProtocolInterfaceCarService carService;
    String carServiceIp;
    String userServiceIp;

    public Protocol(ProtocolInterfaceUserService userService, ProtocolInterfaceCarService carService, String carServiceIp, String userServiceIp) throws RemoteException {
        this.userService = userService;
        this.carService = carService;
        this.carServiceIp = carServiceIp;
        this.userServiceIp = userServiceIp;
    }

    @Override
    public String login(String login) throws RemoteException {
        try {
            String response = userService.authenticate(login);
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, user service is offline");
            restartServer();
            return "Error, user service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with user service");
            restartServer();
            return "Error with user service";
        }
    }

    @Override
    public String createCar(String car) throws RemoteException {
        try {
            String response = carService.createCar(car);
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            restartServer();
            return "Error, car service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with user service");
            restartServer();
            return "Error with car service";
        }
    }

    @Override
    public List<String> getCars() throws RemoteException {
        try {
            List<String> response = carService.listAllCars();
            if (response.get(0).equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            List<String> error = new ArrayList<>();
            error.add("Error, car service is offline");
            restartServer();
            return error;
        } catch (RemoteException e) {
            System.out.println("Error with user service");
            List<String> error = new ArrayList<>();
            error.add("Error with user service");
            restartServer();
            return error;
        }
    }

    @Override
    public List<String> getModels() throws RemoteException {
        try {
            List<String> response = carService.listAllCarsModelQuantity();
            if (response.get(0).equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            List<String> error = new ArrayList<>();
            error.add("Error, car service is offline");
            restartServer();
            return error;
        } catch (RemoteException e) {
            System.out.println("Error with car service");
            List<String> error = new ArrayList<>();
            error.add("Error with car service");
            restartServer();
            return error;
        }
    }

    @Override
    public String addStock(String car) throws RemoteException {
        try {
            String response = carService.addCarStock(car);
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            return "Error, user service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with car service");
            return "Error with car service";
        }
    }

    @Override
    public String removeStock(String id) throws RemoteException {
        try {
            String response = carService.removeCarStock(id);
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            return "Error, user service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with car service");
            return "Error with car service";
        }
    }

    @Override
    public List<String> searchCarByName(String name) throws RemoteException {
        try {
            List<String> response = carService.listCarsByName(name);
            if (response.get(0).equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            List<String> error = new ArrayList<>();
            error.add("Error, car service is offline");
            return error;
        } catch (RemoteException e) {
            System.out.println("Error with car service");
            List<String> error = new ArrayList<>();
            error.add("Error with car service");
            return error;
        }
    }

    @Override
    public String update(String id, String car) throws RemoteException {
        try {
            String response = carService.updateCar(id, car);
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            return "Error, user service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with car service");
            return "Error with car service";
        }
    }

    @Override
    public String readCar(String id) throws RemoteException {
        try {
            String response = carService.updateCar(id, carService.readCar(id));
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            return "Error, user service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with car service");
            return "Error with car service";
        }
    }

    @Override
    public String searchCarByRenavam(String renavam) throws RemoteException {
        try {
            String response = carService.searchCarByRenavam(renavam);
            if (response.equals("Error, database is offline")) {
                restartServer();
            } 
            return response;
        } catch (ConnectException e) {
            System.out.println("Error, car service is offline");
            return "Error, car service is offline";
        } catch (RemoteException e) {
            System.out.println("Error with user service");
            return "Error with car service";
        }
    }
    
    @Override
    public boolean pingUserService() throws RemoteException {
        return userService.ping();
    }

    @Override
    public boolean pingCarService() throws RemoteException {
        return carService.ping();
    }


    private void restartServer(){
        try {
            System.out.println("Atualizando conexão");
            this.userService = (ProtocolInterfaceUserService) Naming.lookup("rmi://" + userServiceIp + ":8083/carStoreUser");
            this.carService = (ProtocolInterfaceCarService) Naming.lookup("rmi://" + carServiceIp + ":8089/carStoreCar");
            System.out.println("Conexões atualizadas, tente novamente");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println("Não foi possivel se reconectar ao servidor, tente mais tarde");
            e.printStackTrace();
        }
    }

}
