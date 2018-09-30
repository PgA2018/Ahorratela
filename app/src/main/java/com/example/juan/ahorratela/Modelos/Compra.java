package com.example.juan.ahorratela.Modelos;

/**
 * Created by MattSeidel on 28/09/18.
 */

public class Compra {

    private int id;
    private int id_producto;
    private int id_ubicacion;

    public Compra(int producto, int ubicacion) {
        this.id_producto = producto;
        this.id_ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int producto) {
        this.id_producto = producto;
    }

    public int getId_ubicacion() {
        return id_ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.id_ubicacion = ubicacion;
    }

}
