package com.jongs;

public class CarStoreService {
    public static void main(String[] args) {
        ServerCar server = new ServerCar();
        Thread thread = new Thread(server);

        thread.start();
    }
}
