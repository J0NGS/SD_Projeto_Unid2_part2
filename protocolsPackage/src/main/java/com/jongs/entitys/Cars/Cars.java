package com.jongs.entitys.Cars;

import java.io.Serializable;


public class Cars implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private CAR_CATEGORY category;
    private int yearOfManufacture;

    public enum CAR_CATEGORY {
        ECONOMIC, INTERMEDIARY, EXECUTIVE;
    }

    public Cars() {
    }

    public Cars(int id, String name, CAR_CATEGORY category, int yearOfManufacture) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CAR_CATEGORY getCategory() {
        return this.category;
    }

    public void setCategory(CAR_CATEGORY category) {
        this.category = category;
    }

    public int getYearOfManufacture() {
        return this.yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

        @Override
        public String toString() {
            return "{" +
                    " id='" + getId() + "'" +
                    ", name='" + getName() + "'" +
                    ", category='" + getCategory() + "'" +
                    ", yearOfManufacture='" + getYearOfManufacture() + "'" +
                    "}";
        }

    public static Cars fromString(String str) {
        str = str.replace("{", "").replace("}", "").replace("'", "").trim();
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[0].split("=")[1].trim());
        String name = parts[1].split("=")[1].trim();
        CAR_CATEGORY category = CAR_CATEGORY.valueOf(parts[2].split("=")[1].trim());
        int year = Integer.parseInt(parts[3].split("=")[1].trim());
        return new Cars(id, name, category, year);
    }

}
