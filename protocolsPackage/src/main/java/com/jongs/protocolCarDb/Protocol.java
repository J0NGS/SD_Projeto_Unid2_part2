package com.jongs.protocolCarDb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.jongs.entitys.Cars.Cars;


public class Protocol extends UnicastRemoteObject implements ProtocolInterfaceCarBd, Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Cars> database;
    private Map<String, Integer> databaseStock;
    private static final String FILE_PATH = "car_store_car_db/src/main/resources/DB.bin";
    private static final String FILE_STOCK_PATH = "car_store_car_db/src/main/resources/DBStock.bin";

    public Protocol() throws RemoteException {
        File file = new File(FILE_PATH);
        File fileStock = new File(FILE_STOCK_PATH);
        if (file.exists() && fileStock.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(fileStock));
                this.database = (ConcurrentHashMap<Integer, Cars>) ois.readObject();
                this.databaseStock = (ConcurrentHashMap<String, Integer>) ois2.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.database = new ConcurrentHashMap<>();
            this.databaseStock = new ConcurrentHashMap<>();
            // Carros Econômicos
            database.put(1, new Cars(1, "Volkswagen Gol", Cars.CAR_CATEGORY.ECONOMIC, 2019));
            database.put(2, new Cars(2, "Fiat Palio", Cars.CAR_CATEGORY.ECONOMIC, 2018));
            database.put(3, new Cars(3,"Renault Kwid", Cars.CAR_CATEGORY.ECONOMIC, 2020));
            database.put(11, new Cars(11,"Hyundai Creta", Cars.CAR_CATEGORY.ECONOMIC, 2022));
            
            this.databaseStock.put("Volkswagen Gol", 10);
            this.databaseStock.put("Fiat Palio", 10);
            this.databaseStock.put("Renault Kwid", 10);
            this.databaseStock.put("Hyundai Creta", 10);
            // Carros Intermediários
            database.put(4, new Cars(4,"Ford Focus", Cars.CAR_CATEGORY.INTERMEDIARY, 2018));
            database.put(5, new Cars(5,"Honda City", Cars.CAR_CATEGORY.INTERMEDIARY, 2016));
            database.put(6, new Cars(6,"Toyota Yaris", Cars.CAR_CATEGORY.INTERMEDIARY, 2019));
            database.put(10, new Cars(10,"Chevrolet Prisma", Cars.CAR_CATEGORY.INTERMEDIARY, 2018));

            this.databaseStock.put("Ford Focus", 10);
            this.databaseStock.put("Honda City", 10);
            this.databaseStock.put("Toyota Yaris", 10);
            this.databaseStock.put("Chevrolet Prisma", 10);
            // Carros Executivos
            database.put(7, new Cars(7, "Mercedes-Benz C-Class", Cars.CAR_CATEGORY.EXECUTIVE, 2021));
            database.put(8, new Cars(8, "BMW 5 Series", Cars.CAR_CATEGORY.EXECUTIVE, 2020));
            database.put(9, new Cars(9, "Audi A6", Cars.CAR_CATEGORY.EXECUTIVE, 2019));
            database.put(12, new Cars(12, "Nissan Sentra", Cars.CAR_CATEGORY.EXECUTIVE, 2017));

            this.databaseStock.put("Mercedes-Benz C-Class", 10);
            this.databaseStock.put("BMW 5 Series", 10);
            this.databaseStock.put("Audi A6", 10);
            this.databaseStock.put("Nissan Sentra", 10);

            try {
                saveDatabase();
                saveDatabaseStock();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void saveDatabase() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        oos.writeObject(this.database);
    }

    public void saveDatabaseStock() throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_STOCK_PATH));
        oos.writeObject(this.databaseStock);
    }

    @Override
    public String create(String request) throws RemoteException {
        int id = database.size() + 1;

        Cars car = Cars.fromString(request);
        car.setId(id);

        database.put(car.getId(), car);
        try {
            saveDatabase();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            return "400,Db not found";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return "400,Car not saved";
        }
        return "200,Car saved";
    }

    @Override
    public String read(Integer id) throws RemoteException {
        Optional<Cars> car = Optional.ofNullable(this.database.get(id));
        if (car.isPresent()) {
            Cars response = car.get();
            return response.toString();
        } else {
            return "400,User not found";
        }
    }

    @Override
    public String update(Integer id, String str) throws RemoteException {
        try {
            Cars entityUpdate = Cars.fromString(str);
            entityUpdate.setId(id);
            try {
                database.put(entityUpdate.getId(), entityUpdate);
                saveDatabase();
                return "200,Updated";
            } catch (Exception e) {
                e.printStackTrace();
                return "400,Not updated";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "400,Not convert request";
        }
    }

    @Override
    public String delete(Integer id) throws RemoteException {
        try {
            database.remove(id);
            saveDatabase();
            return "200,Removed";
        } catch (Exception e) {
            e.printStackTrace();
            return"400,Not removed";
        }
    }

    @Override
    public List<String> SearchByName(String id) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchByName'");
    }

}
