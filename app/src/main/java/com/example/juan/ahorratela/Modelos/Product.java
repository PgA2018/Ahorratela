package com.example.juan.ahorratela.Modelos;

/**
 * Created by MattSeidel on 28/09/18.
 */

public class Product {

    private int id;
    private String name;
    private int price;
    private int measure;
    private int unitMeasuerment;

    public Product(int id, String name, int price, int measure, int unitMeasuerment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.measure = measure;
        this.unitMeasuerment = unitMeasuerment;
    }

    public Product(String name, int price, int measure, int unitMeasuerment) {
        this.name = name;
        this.price = price;
        this.measure = measure;
        this.unitMeasuerment = unitMeasuerment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public int getUnitMeasuerment() {
        return unitMeasuerment;
    }

    public void setUnitMeasuerment(int unitMeasuerment) {
        this.unitMeasuerment = unitMeasuerment;
    }
}
