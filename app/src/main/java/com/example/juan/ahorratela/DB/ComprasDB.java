package com.example.juan.ahorratela.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.juan.ahorratela.Modelos.Compra;
import com.example.juan.ahorratela.Modelos.Compra;

import java.util.ArrayList;

/**
 * Created by juan on 28/09/2018.
 */

public class ComprasDB extends SQLiteOpenHelper{
    Context context;

    String STRING_TYPE = "text";
    String INT_TYPE = "integer";
    String BOOLEAN_TYPE = "boolean";

    String TABLE_NAME = "Compras";

    String ID = "id";
    String PRODUCTO = "id_producto";
    String UBICACION = "id_ubicacion";


    private final String CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            +PRODUCTO+" "+STRING_TYPE+" NOT NULL, "
            +UBICACION+" "+STRING_TYPE+" NOT NULL) ";

    public ComprasDB(Context context) {
        super(context, "Compra",null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SCRIPT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        sqLiteDatabase.execSQL(CREATE_SCRIPT);
    }

    public boolean create(Compra compra) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_NAME + " (" + PRODUCTO + ", " + UBICACION + ") " + "VALUES(" + "'" + compra.getId_producto()+ "', " + "'" + compra.getId_ubicacion() + "')";
                db.execSQL(q);
                ret = true;
            } catch (Exception e) {
                e.getStackTrace();
                return false;
            }
            db.close();
        }
        return ret;
    }

    public ArrayList<Compra> getAll() {

        ArrayList<Compra> array = new ArrayList<Compra>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_NAME;
            try {
                Cursor c = db.rawQuery(q, null);
                Compra compra = null;
                while (c.moveToNext()) {
                    compra = new Compra(
                            c.getInt(0),
                            c.getInt(1)
                    );
                    array.add(compra);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public Compra getOne(String id) {

        Compra compra = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q, null);
                compra = null;
                while (c.moveToNext()) {
                    compra = new Compra(
                            c.getInt(0),
                            c.getInt(1)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return compra;
    }

    public Compra update(String id, String id_producto, String id_ubicacion) {

        Compra compra = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            String q = "UPDATE " + TABLE_NAME +" SET "+PRODUCTO +"='"+id_producto+"',"+ UBICACION +"='"+id_ubicacion+"'" +" WHERE " + ID + " = '"+ id +"'";
            db.execSQL(q);

            String q2 = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q2, null);
                compra = null;
                while (c.moveToNext()) {
                    compra = new Compra(
                            c.getInt(0),
                            c.getInt(1)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return compra;
    }

    public void deleteAll(){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            try {
                String q = "DELETE FROM " + TABLE_NAME;
                db.execSQL(q);
            } catch (Exception e) {
                e.getStackTrace();
            }
            db.close();
        }
    }

    public boolean deleteOne(String id) {
        boolean ret = false;
        Compra lugar = null;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "DELETE FROM " + TABLE_NAME + " WHERE "+ ID + " = "+ id +"";
                db.execSQL(q);
                ret = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return ret;
    }
}
