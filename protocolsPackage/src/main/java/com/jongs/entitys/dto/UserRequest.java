package com.jongs.entitys.dto;

import com.jongs.entitys.User.USER_POLICY;

public record UserRequest(String login, String password, USER_POLICY policy) {

    public static UserRequest fromString(String str) {
        str = str.replace("{", "").replace("}", "").replace("'", "").trim();
        String[] parts = str.split(",");
        String login = parts[0].split("=")[1].trim();
        String password = parts[1].split("=")[1].trim();
        USER_POLICY policy = USER_POLICY.valueOf(parts[2].split("=")[1].trim());
        return new UserRequest(login, password, policy);
    }
    

    @Override
    public String toString() {
        return "{" +
                " login='" + login() + "'" +
                ", password='" + password() + "'" +
                ", policy='" + policy() + "'" +
                "}";
    }
}
