package com.jongs.entitys.Cars;
import java.io.Serializable;

public class EconomicCar extends Cars implements Serializable{
    private static final long serialVersionUID = 1L;
    public EconomicCar() {
    }

    public EconomicCar(int id, String name,String renavam, int yearOfManufacture) {
        super(id, name, CAR_CATEGORY.ECONOMIC, yearOfManufacture, renavam);
    }
}
