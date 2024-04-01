package com.jongs.entitys.Cars;

import java.io.Serializable;
import java.util.Objects;


public class Cars implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private CAR_CATEGORY category;
    private int yearOfManufacture;
    private String renavam;

    public enum CAR_CATEGORY {
        ECONOMIC, INTERMEDIARY, EXECUTIVE;
    }


    public Cars() {
    }

    public Cars(int id, String name, CAR_CATEGORY category, int yearOfManufacture, String renavam) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.yearOfManufacture = yearOfManufacture;
        this.renavam = renavam;
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

    public String getRenavam() {
        return this.renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public Cars id(int id) {
        setId(id);
        return this;
    }

    public Cars name(String name) {
        setName(name);
        return this;
    }

    public Cars category(CAR_CATEGORY category) {
        setCategory(category);
        return this;
    }

    public Cars yearOfManufacture(int yearOfManufacture) {
        setYearOfManufacture(yearOfManufacture);
        return this;
    }

    public Cars renavam(String renavam) {
        setRenavam(renavam);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cars)) {
            return false;
        }
        Cars cars = (Cars) o;
        return id == cars.id && Objects.equals(name, cars.name) && Objects.equals(category, cars.category) && yearOfManufacture == cars.yearOfManufacture && Objects.equals(renavam, cars.renavam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, yearOfManufacture, renavam);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", category='" + getCategory() + "'" +
            ", yearOfManufacture='" + getYearOfManufacture() + "'" +
            ", renavam='" + getRenavam() + "'" +
            "}";
    }


    public static Cars fromString(String str) {
        str = str.replace("{", "").replace("}", "").replace("'", "").trim();
        String[] parts = str.split(",");
        int id = Integer.parseInt(parts[0].split("=")[1].trim());
        String name = parts[1].split("=")[1].trim();
        CAR_CATEGORY category = CAR_CATEGORY.valueOf(parts[2].split("=")[1].trim());
        int year = Integer.parseInt(parts[3].split("=")[1].trim());
        String renavam = parts[4].split("=")[1].trim();
        return new Cars(id, name, category, year, renavam);
    }

}
