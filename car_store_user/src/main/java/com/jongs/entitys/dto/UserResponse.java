package com.jongs.entitys.dto;

import com.jongs.entitys.User;
import com.jongs.entitys.User.USER_POLICY;

    public record UserResponse(int id, String login, USER_POLICY policy) {
        
        public static UserResponse fromUser(User user){
            return new UserResponse(user.getId(), user.getLogin(), user.getPolicy());
        }


        public static UserResponse fromString(String str) {
            str = str.replace("{", "").replace("}", "").trim();
            String[] parts = str.split(",");
            int id = Integer.parseInt(parts[0].split("=")[1].trim());
            String login = parts[1].split("=")[1].trim();
            USER_POLICY policy = USER_POLICY.valueOf(parts[2].split("=")[1].trim());
            return new UserResponse(id, login, policy);
        }
        

        
        @Override
        public String toString() {
            return "{" +
                " id='" + id() + "'" +
                ", login='" + login() + "'" +
                ", policy='" + policy() + "'" +
                "}";
        }
    }
