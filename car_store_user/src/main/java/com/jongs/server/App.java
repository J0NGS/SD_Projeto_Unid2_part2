package com.jongs.server;

public class App {
    public static void main(String[] args) {
        ServerUser server = new ServerUser();
        Thread thread = new Thread(server);

        thread.start();
    }
}
