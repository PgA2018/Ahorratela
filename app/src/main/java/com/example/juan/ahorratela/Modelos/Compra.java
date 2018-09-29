package com.example.juan.ahorratela.Modelos;

/**
 * Created by MattSeidel on 28/09/18.
 */

public class Compra {

    private int id;
    private int id_producto;
    private int id_ubicacion; 



    public Compra(int price, int measure) {
        this.id_producto = price;
        this.id_ubicacion = measure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return id_producto;
    }

    public void setPrice(int price) {
        this.id_producto = price;
    }

    public int getMeasure() {
        return id_ubicacion;
    }

    public void setMeasure(int measure) {
        this.id_ubicacion = measure;
    }

}
