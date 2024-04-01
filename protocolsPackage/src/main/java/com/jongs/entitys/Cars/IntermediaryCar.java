package com.jongs.entitys.Cars;
import java.io.Serializable;

public class IntermediaryCar extends Cars implements Serializable{
    private static final long serialVersionUID = 1L;
    public IntermediaryCar() {
    }

    public IntermediaryCar(int id, String renavam, int yearOfManufacture) {
        super(id, renavam, CAR_CATEGORY.INTERMEDIARY, yearOfManufacture, renavam);
    }
}
