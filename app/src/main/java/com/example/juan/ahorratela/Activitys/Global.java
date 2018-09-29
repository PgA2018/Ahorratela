package com.example.juan.ahorratela.Activitys;

import android.app.Application;

import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.Modelos.Product;

/**
 * Created by CRISTIAN on 29/09/2018.
 */

public class Global extends Application {
    private LugaresModel lugar;
    private Product product;

    public LugaresModel getLugar() {
        return lugar;
    }

    public void setLugar(LugaresModel lugar) {
        this.lugar = lugar;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
