package com.example.juan.ahorratela.Modelos;

/**
 * Created by MattSeidel on 28/09/18.
 */

public class ComprasModel {

    private int id;
    private int id_producto;
    private int id_ubicacion;
    private int valor_compra;
    private int valor_ahorro;

    public ComprasModel(int producto, int ubicacion) {
        this.id_producto = producto;
        this.id_ubicacion = ubicacion;
    }

    public ComprasModel(int id_producto, int id_ubicacion, int valor_compra) {
        this.id_producto = id_producto;
        this.id_ubicacion = id_ubicacion;
        this.valor_compra = valor_compra;
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
