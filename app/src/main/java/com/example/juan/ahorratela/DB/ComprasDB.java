package com.example.juan.ahorratela.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.juan.ahorratela.Modelos.LugaresModel;

import java.util.ArrayList;

/**
 * Created by juan on 28/09/2018.
 */

public class ComprasDB extends SQLiteOpenHelper{
    Context context;

    String STRING_TYPE = "text";
    String INT_TYPE = "integer";
    String BOOLEAN_TYPE = "boolean";

    String TABLE_NAME = "LugaresModel";

    String ID = "id";
    String PRODUCTO = "id_producto";
    String UBICACION = "id_ubicacion";


    private final String CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            +PRODUCTO+" "+STRING_TYPE+" NOT NULL, "
            +UBICACION+" "+STRING_TYPE+" NOT NULL) ";

    public ComprasDB(Context context) {
        super(context, "LugaresModel",null,1);
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

    public boolean create(LugaresModel lugar) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_NAME + " (" + PRODUCTO + ", " + UBICACION + ") " + "VALUES(" + "'" + lugar.getNombre().toString()+ "', " + "'" + lugar.getUbicacion().toString() + "')";
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

    public ArrayList<LugaresModel> getAll() {

        ArrayList<LugaresModel> array = new ArrayList<LugaresModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_NAME;
            try {
                Cursor c = db.rawQuery(q, null);
                LugaresModel lugar = null;
                while (c.moveToNext()) {
                    lugar = new LugaresModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2)
                    );
                    array.add(lugar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public LugaresModel getOne(String id) {

        LugaresModel lugar = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q, null);
                lugar = null;
                while (c.moveToNext()) {
                    lugar = new LugaresModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return lugar;
    }

    public LugaresModel update(String id, String nombre, String ubicacion) {

        LugaresModel lugar = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            String q = "UPDATE " + TABLE_NAME +" SET "+PRODUCTO +"='"+nombre+"',"+ UBICACION +"='"+ubicacion+"'" +" WHERE " + ID + " = '"+ id +"'";
            db.execSQL(q);

            String q2 = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q2, null);
                lugar = null;
                while (c.moveToNext()) {
                    lugar = new LugaresModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return lugar;
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
        LugaresModel lugar = null;

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
