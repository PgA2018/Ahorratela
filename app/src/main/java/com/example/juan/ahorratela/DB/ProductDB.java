package com.example.juan.ahorratela.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.juan.ahorratela.Modelos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BMIX2 on 28/09/18.
 */

public class ProductDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "product.db";
    private final String TABLE_NAME = "product";
    private final String ID = "product_id";
    private final String NAME = "name";
    private final String PRICE = "price";
    private final String MEASURE = "measuer";
    private final String UNIT_MEASUERMENT = "unit_masuerment";

    public ProductDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT NOT NULL,"
                + PRICE + " REAL NOT NULL,"
                + MEASURE + " REAL NOT NULL,"
                + UNIT_MEASUERMENT + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long saveProduct(Product product){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME,product.getName());
        values.put(PRICE,product.getPrice());
        values.put(MEASURE,product.getMeasure());
        values.put(UNIT_MEASUERMENT,product.getUnitMeasuerment());

        return sqLiteDatabase.insert(TABLE_NAME,null,values);
    }

    public List<Product> getAll(){
        List<Product> products=new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor c= db.rawQuery("select * from "+DATABASE_NAME,null);
        if(c.moveToFirst()){
            do {
                products.add(new Product(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2),
                        c.getInt(3),
                        c.getInt(4)
                ));
            }while (c.moveToNext());
        }
        return products;
    }
}
