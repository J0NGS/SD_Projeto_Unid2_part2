package com.jongs.protocolDb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.jongs.entitys.User;
import com.jongs.entitys.User.USER_POLICY;
import com.jongs.entitys.dto.LoginRequest;
import com.jongs.entitys.dto.UserRequest;
import com.jongs.entitys.dto.UserResponse;

public class Protocol extends UnicastRemoteObject implements ProtocolInterfaceBd {
    private Map<Integer, User> database;
    private static final String FILE_PATH = "car_store_user_db/src/main/resources/DB.bin";

    public Protocol() throws RemoteException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                this.database = (Map<Integer, User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            this.database = new ConcurrentHashMap<>();
            User admin = new User(1, "admin", "admin", USER_POLICY.EMPLOYEE);
            User customer = new User(1, "customer", "customer", USER_POLICY.CUSTOMER);
            this.database.put(admin.getId(), admin);
            this.database.put(customer.getId(), customer);
            try {
                saveDatabase();
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

    @Override
    public String create(String request) throws RemoteException {
        UserRequest userRequest = UserRequest.fromString(request);

        Integer id = this.database.size() + 1;
        User userEntity = new User(id, userRequest.login(), userRequest.password(), userRequest.policy());
        if (searchByLogin(userRequest.login()).equals("User not found")) {
            this.database.put(userEntity.getId(), userEntity);
            try {
                saveDatabase();
                return "Create!";
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        } else {
            return "Error, usuário já existente com o mesmo login";
        }
            

    }

    @Override
    public String read(Integer id) throws RemoteException {
        Optional<User> user = Optional.ofNullable(this.database.get(id));
        if (user.isPresent()) {
            try {
                UserResponse response = UserResponse.fromUser(user.get());
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        } else {
            return "User not found";
        }
    }

    @Override
    public String update(Integer id) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public String delete(Integer id) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public String searchByLoginAndPassword(String login) throws RemoteException {
        LoginRequest request = LoginRequest.fromString(login);
        Optional<User> user = this.database.values().stream()
                .filter(u -> (u.getLogin().equals(request.login()) && u.getPassword().equals(request.password())))
                .findFirst();
        if (user.isPresent()) {
            UserResponse response = UserResponse.fromUser(user.get());
            return response.toString();
        } else {
            return "User not found";
        }
    }

    private String searchByLogin(String login) throws RemoteException {
        LoginRequest request = LoginRequest.fromString(login);
        Optional<User> user = this.database.values().stream()
                .filter(u -> u.getLogin().equals(request.login()))
                .findFirst();
        if (user.isPresent()) {
            UserResponse response = UserResponse.fromUser(user.get());
            return response.toString();
        } else {
            return "User not found";
        }
    }
}
