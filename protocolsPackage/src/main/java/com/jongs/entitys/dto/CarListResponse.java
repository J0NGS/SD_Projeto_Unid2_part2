package com.jongs.entitys.dto;

public record CarListResponse(String name, Integer quantity) {
    
    
    @Override
    public String toString() {
        return "{" +
                " name='" + name() + "'" +
                ", quantity='" + quantity() + "'" +
                "}";
    }
}
