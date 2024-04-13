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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.jongs.entitys.Cars.Cars;
import com.jongs.entitys.dto.CarListResponse;


public class Protocol extends UnicastRemoteObject implements ProtocolInterfaceCarBd, Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Cars> database;
    private Map<String, Integer> databaseStock;
    private static final String FILE_PATH = "car_store_cars_db/src/main/resources/DB.bin";
    private static final String FILE_STOCK_PATH = "car_store_cars_db/src/main/resources/DBStock.bin";

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
            database.put(1, new Cars(1, "Volkswagen Gol", Cars.CAR_CATEGORY.ECONOMIC, 2019, 44000.00F,"62945104578"));
            database.put(2, new Cars(2, "Fiat Palio", Cars.CAR_CATEGORY.ECONOMIC, 2018, 38000.00F,"78023658914"));
            database.put(3, new Cars(3,"Renault Kwid", Cars.CAR_CATEGORY.ECONOMIC,2020, 48000.00F,"32015478632"));
            database.put(11, new Cars(11,"Hyundai Creta", Cars.CAR_CATEGORY.ECONOMIC, 2022, 630000.00F,"31498478656"));
            
            this.databaseStock.put("Volkswagen Gol-2019", 1);
            this.databaseStock.put("Fiat Palio-2018", 1);
            this.databaseStock.put("Renault Kwid-2020", 1);
            this.databaseStock.put("Hyundai Creta-2022", 1);
            // Carros Intermediários
            database.put(4, new Cars(4,"Ford Focus", Cars.CAR_CATEGORY.INTERMEDIARY, 2018, 69000.00F,"95687420135"));
            database.put(5, new Cars(5,"Honda City", Cars.CAR_CATEGORY.INTERMEDIARY, 2016, 64000.00F,"41032659874"));
            database.put(6, new Cars(6,"Toyota Yaris", Cars.CAR_CATEGORY.INTERMEDIARY, 2019, 68000.00F,"58740123659"));
            database.put(10, new Cars(10,"Chevrolet Prisma", Cars.CAR_CATEGORY.INTERMEDIARY, 2018, 64000.00F,"12036548795"));

            this.databaseStock.put("Ford Focus-2018", 1);
            this.databaseStock.put("Honda City-2016", 1);
            this.databaseStock.put("Toyota Yaris-2019", 1);
            this.databaseStock.put("Chevrolet Prisma-2018", 1);
            // Carros Executivos
            database.put(7, new Cars(7, "Mercedes-Benz C-Class", Cars.CAR_CATEGORY.EXECUTIVE, 2021, 84500.00F,"89456230147"));
            database.put(8, new Cars(8, "BMW 5 Series", Cars.CAR_CATEGORY.EXECUTIVE, 2020, 92800.00F,"96587430215"));
            database.put(9, new Cars(9, "Audi A6", Cars.CAR_CATEGORY.EXECUTIVE, 2019,79000.00F,"20136589470"));
            database.put(12, new Cars(12, "Nissan Sentra", Cars.CAR_CATEGORY.EXECUTIVE, 2017, 73000.00F,"47589630124"));

            this.databaseStock.put("Mercedes-Benz C-Class-2021", 1);
            this.databaseStock.put("BMW 5 Series-2020", 1);
            this.databaseStock.put("Audi A6-2019", 1);
            this.databaseStock.put("Nissan Sentra-2017", 1);

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
        int id = generateUniqueId();
        while (database.containsKey(id)) {
            id = generateUniqueId();
        }
        Cars car = Cars.fromString(request);
        String carModel = car.getName()+"-"+ car.getYearOfManufacture();
        if (databaseStock.containsKey(carModel)) {
            return "409,Model already exists; suggestion: add in stock";
        } else {
            car.setId(id);
    
    
            database.put(car.getId(), car);
            databaseStock.put(carModel, 1);
            try {
                saveDatabase();
                saveDatabaseStock();
            } catch (FileNotFoundException e) {
                return "400,Db not found";
            } catch (IOException e) {
                return "400,Car not saved";
            }
            return "200,Car saved";
        }
    }

    @Override
    public String read(Integer id) throws RemoteException {
        Optional<Cars> car = Optional.ofNullable(this.database.get(id));
        if (car.isPresent()) {
            Cars response = car.get();
            return response.toString();
        } else {
            return "404,car not found";
        }
    }

    @Override
    public String update(Integer id, String str) throws RemoteException {
        try {
            
            Cars entityUpdate = Cars.fromString(str);
            Cars oldCar = database.get(id);
            String entityModel = entityUpdate.getName() + "-" + entityUpdate.getYearOfManufacture();
            String oldCarModel = oldCar.getName() + "-" + oldCar.getYearOfManufacture();
            entityUpdate.setId(id);
            try {
                if (!(oldCarModel.equals(entityModel))) {
                    delete(id);
                    
                    if (databaseStock.containsKey(entityModel)) {
                        int quantityNewModel = databaseStock.get(entityModel);
                        quantityNewModel++;
                        databaseStock.put(entityModel, quantityNewModel);
                    }else{
                        databaseStock.put(entityModel, 1);
                    }
                    database.put(entityUpdate.getId(), entityUpdate);
                    saveDatabaseStock();
                }
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
            Cars entity = database.get(id);
            int quantity = databaseStock.get(entity.getName() + "-" + entity.getYearOfManufacture());
            databaseStock.put(entity.getName() + "-" + entity.getYearOfManufacture(), quantity - 1);
            if (databaseStock.get(entity.getName() + "-" + entity.getYearOfManufacture()) == 0) {
                databaseStock.remove(entity.getName() + "-" + entity.getYearOfManufacture());
            }
            database.remove(id);
            saveDatabase();
            saveDatabaseStock();
            return "200,Removed";
        } catch (Exception e) {
            e.printStackTrace();
            return"400,Not removed";
        }
    }

    @Override
    public List<String> SearchByName(String name) throws RemoteException {
        List<String> foundCars = new ArrayList<>(); 
        // Iterar sobre os valores do mapa de carros
        for (Cars car : database.values()) {
            // Verificar se o nome do carro contém a substring desejada
            if (car.getName().toLowerCase().contains(name.toLowerCase())) {
                foundCars.add(car.toString());
            }
        }
        return foundCars;
    }

    @Override
    public String SearchByRenavam(String renavam) throws RemoteException {
            Optional<Cars> carFound = Optional.empty();
            
            for (Cars car : database.values()) {
                if (car.getRenavam().equals(renavam)) {
                    carFound = Optional.of(car);
                }
            }
            if(carFound.isPresent())
                return carFound.get().toString();
            else
                return "404,car not found";
    }

    @Override
    public String addStock(String request) throws RemoteException {
        int id = generateUniqueId();

        Cars car = Cars.fromString(request);
        String carModel = car.getName()+"-"+ car.getYearOfManufacture();
        if (!(databaseStock.containsKey(carModel))) {
            return "404,Model dont exist";
        } else {
            car.setId(id);
    
            database.put(car.getId(), car);
            databaseStock.put(carModel, (databaseStock.get(carModel) + 1));
            try {
                saveDatabase();
                saveDatabaseStock();
            } catch (FileNotFoundException e) {
                return "400,Db not found";
            } catch (IOException e) {
                return "400,Car not saved";
            }
            return "200,Car saved";
        }
    }

    @Override
    public String removeStock(String id) throws RemoteException {
        int idInt = Integer.parseInt(id);
        if (database.containsKey(idInt)) {        
            String respose = delete(idInt);
            String [] parts = respose.split(",");
            if(parts[0].equals("200")){
                return "200,car removed";
            } else {
                return "400,Error-> Operate delete. Message: " + parts[1];
            }
        } else {
            return "404,car not found";
        }
    }

    @Override
    public List<String> getAll(){
        List<String> response = database.values().stream().map(Cars::toString).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<String> getAllWithQuantity(){
        List<String> response = new ArrayList<>();
        int quantity = 0;
        for (String keys : databaseStock.keySet()) {
            quantity+= databaseStock.get(keys);
            CarListResponse carResponse = new CarListResponse(keys, databaseStock.get(keys));
            response.add(carResponse.toString());
        }
        response.add("TOTAL DE CARROS: " + quantity);
        return response;
    }


    private static int generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        int hash = uuid.hashCode();
        int positiveHash = Math.abs(hash);
        int maxLimit = (int) Math.pow(10, 5);
        int limitedHash = positiveHash % maxLimit;
        return limitedHash;
    }
}
