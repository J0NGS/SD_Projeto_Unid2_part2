package com.jongs.entitys.Cars;
import java.io.Serializable;

public class ExecutiveCar extends Cars implements Serializable{
    private static final long serialVersionUID = 1L;
    public ExecutiveCar() {
    }

    public ExecutiveCar(int id, String renavam, int yearOfManufacture) {
        super(id, renavam, CAR_CATEGORY.EXECUTIVE, yearOfManufacture, renavam);
    }
}
