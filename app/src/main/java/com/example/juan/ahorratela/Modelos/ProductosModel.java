package com.example.juan.ahorratela.Modelos;

/**
 * Created by juan on 28/09/2018.
 */

public class ProductosModel {
    private Integer id;
    private String nombre;

    public ProductosModel(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
