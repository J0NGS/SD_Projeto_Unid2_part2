package com.jongs.entitys.Cars;
import java.io.Serializable;

public class ExecutiveCar extends Cars implements Serializable{
    private static final long serialVersionUID = 1L;
    public ExecutiveCar() {
    }

    public ExecutiveCar(int id, String name,String renavam, float price, int yearOfManufacture) {
        super(id, name, CAR_CATEGORY.EXECUTIVE, yearOfManufacture, price, renavam);
    }
}
