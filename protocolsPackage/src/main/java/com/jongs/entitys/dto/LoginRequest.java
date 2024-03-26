package com.jongs.entitys.dto;

public record LoginRequest(String login, String password) {

    public static LoginRequest fromString(String str) {
        str = str.replace("{", "").replace("}", "").replace("'", "").trim();
        String[] parts = str.split(",");
        String login = parts[0].split("=")[1].trim();
        String password = parts[1].split("=")[1].trim();
        return new LoginRequest(login, password);
    }

    @Override
    public String toString() {
        return "{" +
                " login='" + login() + "'" +
                ", password='" + password() + "'" +
                "}";
    }
}
