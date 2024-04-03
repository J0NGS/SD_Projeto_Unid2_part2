package com.jongs;

public class App {
    public static void main(String[] args) {
        ServerCar server = new ServerCar();
        Thread thread = new Thread(server);

        thread.start();
    }
}
