package com.jongs.entitys.Cars;
import java.io.Serializable;
import java.util.Objects;

public class EconomicCar extends Cars implements Serializable{
    private static final long serialVersionUID = 1L;
    private String renavam;


    public EconomicCar() {
    }

    public EconomicCar(int id, String renavam, int yearOfManufacture) {
        super(id, renavam, CAR_CATEGORY.ECONOMIC, yearOfManufacture);
        this.renavam = renavam;
    }

    public String getRenavam() {
        return this.renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public EconomicCar renavam(String renavam) {
        setRenavam(renavam);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EconomicCar)) {
            return false;
        }
        EconomicCar economicCar = (EconomicCar) o;
        return Objects.equals(renavam, economicCar.renavam);
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") + ", renavam='" + renavam + "'" +
                "}";
    }
    
    public static EconomicCar fromString(String str) {
        str = str.replace("{", "").replace("}", "").replace("'", "").trim();
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[0].split("=")[1].trim());
        String name = parts[1].split("=")[1].trim();
        CAR_CATEGORY category = CAR_CATEGORY.valueOf(parts[2].split("=")[1].trim());
        int year = Integer.parseInt(parts[3].split("=")[1].trim());
        String renavam = parts[4].split("=")[1].trim();
        return new EconomicCar(id, renavam, year);
    }
    
    
}
