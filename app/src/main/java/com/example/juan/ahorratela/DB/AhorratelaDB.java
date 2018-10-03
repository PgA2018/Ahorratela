package com.example.juan.ahorratela.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.Modelos.PresentacionesModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.Modelos.UnidadModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by juan on 28/09/2018.
 */

public class AhorratelaDB extends SQLiteOpenHelper{
    Context context;

    static String STRING_TYPE = "text";
    static String INT_TYPE = "integer";
    static String BOOLEAN_TYPE = "boolean";

    static String TABLE_LUGARES = "Lugares";
    static String TABLE_PRODUCTOS = "Productos";
    static String TABLE_COMPRAS = "Compras";
    static String TABLE_UNIDAD = "Unidad";
    static String TABLE_PRESENTACIONES = "Presentaciones";


    static String ID = "id";
    static String NOMBRE = "nombre";
    static String UBICACION = "ubicacion";
    static String ID_PRODUCTO = "id_producto";
    static String ID_UBICACION = "id_ubicacion";
    static String ID_PRESENTACION = "id_presentacion";
    static String ID_UNIDAD = "id_unidad";
    static String MEDIDA = "medida";
    static String VALOR_COMPRA = "valor_compra";
    static String VALOR_AHORRO = "valor_ahorro";
    static String FECHA_COMPRA = "fecha_Compra";

    private static final String CREATE_TABLE_LUGARES = "CREATE TABLE " + TABLE_LUGARES + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            +NOMBRE+" "+STRING_TYPE+" NOT NULL, "
            +UBICACION+" "+STRING_TYPE+" NOT NULL) ";

    private static final String CREATE_TABLE_PRODUCTOS = "CREATE TABLE " + TABLE_PRODUCTOS + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            +NOMBRE+" "+STRING_TYPE+" NOT NULL,"
            +ID_PRESENTACION+" "+STRING_TYPE+","
            +ID_UNIDAD+" "+STRING_TYPE+","
            +MEDIDA+" "+INT_TYPE+") ";

    private static final String CREATE_TABLE_COMPRAS = "CREATE TABLE " + TABLE_COMPRAS + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            + ID_PRODUCTO +" "+STRING_TYPE+" NOT NULL, "
            + ID_UBICACION +" "+STRING_TYPE+" NOT NULL,"
            + VALOR_COMPRA +" "+STRING_TYPE+" NOT NULL,"
            + VALOR_COMPRA +" "+STRING_TYPE+" NOT NULL,"
            + FECHA_COMPRA +" "+STRING_TYPE+"NOT NULL) ";

    private static final String CREATE_TABLE_UNIDAD = "CREATE TABLE " + TABLE_UNIDAD + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            + NOMBRE +" "+STRING_TYPE+" NOT NULL) ";

    private static final String CREATE_TABLE_PRESENTACIONES = "CREATE TABLE " + TABLE_PRESENTACIONES + " ("
            +ID+" "+INT_TYPE+" PRIMARY KEY, "
            +NOMBRE+" "+STRING_TYPE+" NOT NULL)";

    public AhorratelaDB(Context context) {
        super(context, "ahorratela.db",null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE_LUGARES);
            sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCTOS);
            sqLiteDatabase.execSQL(CREATE_TABLE_COMPRAS);
            sqLiteDatabase.execSQL(CREATE_TABLE_UNIDAD);
            sqLiteDatabase.execSQL(CREATE_TABLE_PRESENTACIONES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LUGARES);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPRAS);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIDAD);
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESENTACIONES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase);
    }

    //LUGARES

    public boolean createLugar(LugaresModel lugar) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_LUGARES + " (" + NOMBRE + ", " + UBICACION + ") " + "VALUES(" + "'" + lugar.getNombre().toString()+ "', " + "'" + lugar.getUbicacion().toString() + "')";
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

    public ArrayList<LugaresModel> getAllLugares() {

        ArrayList<LugaresModel> array = new ArrayList<LugaresModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_LUGARES;
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

    public LugaresModel getLugarById(String id) {

        LugaresModel lugar = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_LUGARES + " WHERE " + ID + " = '"+ id +"'";
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

    public LugaresModel getLugarByNombreBool(String nombre) {

        LugaresModel lugar = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_LUGARES + " WHERE " + NOMBRE + " = '"+ nombre +"'";
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

    public ArrayList<LugaresModel> getLugarByNombre(String nombre) {

        ArrayList<LugaresModel> array = new ArrayList<LugaresModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_LUGARES + " WHERE " + NOMBRE + " LIKE '%" + nombre + "%'";
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

    public LugaresModel updateLugar(String id, String nombre, String ubicacion) {

        LugaresModel lugar = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            String q = "UPDATE " + TABLE_LUGARES +" SET "+NOMBRE +"='"+nombre+"',"+ UBICACION +"='"+ubicacion+"'" +" WHERE " + ID + " = '"+ id +"'";
            db.execSQL(q);

            String q2 = "SELECT * FROM " + TABLE_LUGARES + " WHERE " + ID + " = '"+ id +"'";
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

    public void deleteAllLugares(){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            try {
                String q = "DELETE FROM " + TABLE_LUGARES;
                db.execSQL(q);
            } catch (Exception e) {
                e.getStackTrace();
            }
            db.close();
        }
    }

    public boolean deleteLugar(String id) {
        boolean ret = false;
        LugaresModel lugar = null;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "DELETE FROM " + TABLE_LUGARES + " WHERE "+ ID + " = "+ id +"";
                db.execSQL(q);
                ret = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return ret;
    }

    //PRODUCTOS

    public boolean createProducto(ProductosModel producto) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_PRODUCTOS + " ("+NOMBRE+","+ID_PRESENTACION+","+ID_UNIDAD+","+MEDIDA+") " + "VALUES("+"'"+producto.getNombre()+"','"+producto.getPresentacion()+"','"+producto.getUnidad()+"',"+producto.getMedida()+")";
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

    public ArrayList<ProductosModel> getAllProductos() {

        ArrayList<ProductosModel> array = new ArrayList<ProductosModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_PRODUCTOS;
            try {
                Cursor c = db.rawQuery(q, null);
                ProductosModel producto = null;
                while (c.moveToNext()) {
                    producto = new ProductosModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getInt(4)
                    );
                    array.add(producto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public ProductosModel getProductoById(int id) {

        ProductosModel producto = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q, null);
                producto = null;
                while (c.moveToNext()) {
                    producto = new ProductosModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getInt(4)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return producto;
    }

    public ProductosModel getProductoByNombreBool(String nombre) {

        ProductosModel producto = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + NOMBRE + " = '"+ nombre +"'";
            try {
                Cursor c = db.rawQuery(q, null);
                producto = null;
                while (c.moveToNext()) {
                    producto = new ProductosModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getInt(4)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return producto;
    }

    public ArrayList<ProductosModel> getProductoByNombre(String nombre) {

        ArrayList<ProductosModel> array = new ArrayList<ProductosModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + NOMBRE + " LIKE '%" + nombre + "%'";
            try {
                Cursor c = db.rawQuery(q, null);
                ProductosModel producto = null;
                while (c.moveToNext()) {
                    producto = new ProductosModel(
                            c.getInt(0),
                            c.getString(1),
                            c.getString(2),
                            c.getString(3),
                            c.getInt(4)
                    );
                    array.add(producto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public ProductosModel updateProducto(String id, String nombre) {

        ProductosModel producto = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            String q = "UPDATE " + TABLE_PRODUCTOS +" SET "+NOMBRE +"='"+nombre+ "' WHERE " + ID + " = '"+ id +"'";
            db.execSQL(q);

            String q2 = "SELECT * FROM " + TABLE_PRODUCTOS + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q2, null);
                producto = null;
                while (c.moveToNext()) {
                    producto = new ProductosModel(
                            c.getInt(0),
                            c.getString(1)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return producto;
    }

    public void deleteAllProductos(){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            try {
                String q = "DELETE FROM " + TABLE_PRODUCTOS;
                db.execSQL(q);
            } catch (Exception e) {
                e.getStackTrace();
            }
            db.close();
        }
    }

    public boolean deleteProducto(String id) {
        boolean ret = false;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "DELETE FROM " + TABLE_PRODUCTOS + " WHERE "+ ID + " = "+ id +"";
                db.execSQL(q);
                ret = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return ret;
    }

    //COMPRAS

    public boolean createCompra(ComprasModel comprasModel) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_COMPRAS + " (" + ID_PRODUCTO + ", " + ID_UBICACION + ", " + VALOR_COMPRA + ", " + VALOR_AHORRO + ", " + FECHA_COMPRA + ") " + "VALUES(" + "'" + comprasModel.getId_producto()+ "', " + "'" + comprasModel.getId_ubicacion() + "'" + comprasModel.getValor_compra() + "'" + comprasModel.getValor_ahorro() + "'" + Locale.getDefault() + "')";
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

    public ArrayList<ComprasModel> getAllCompras() {

        ArrayList<ComprasModel> array = new ArrayList<ComprasModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_COMPRAS;
            try {
                Cursor c = db.rawQuery(q, null);
                ComprasModel comprasModel = null;
                while (c.moveToNext()) {
                    comprasModel = new ComprasModel(
                            c.getInt(0),
                            c.getInt(1)
                    );
                    array.add(comprasModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public ComprasModel getCompraById(String id) {

        ComprasModel comprasModel = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_COMPRAS + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q, null);
                comprasModel = null;
                while (c.moveToNext()) {
                    comprasModel = new ComprasModel(
                            c.getInt(0),
                            c.getInt(1)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return comprasModel;
    }

    public ComprasModel updateCompra(String id, String id_producto, String id_ubicacion) {

        ComprasModel comprasModel = null;

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            String q = "UPDATE " + TABLE_COMPRAS +" SET "+ ID_PRODUCTO +"='"+id_producto+"',"+ ID_UBICACION +"='"+id_ubicacion+"'" +" WHERE " + ID + " = '"+ id +"'";
            db.execSQL(q);

            String q2 = "SELECT * FROM " + TABLE_COMPRAS + " WHERE " + ID + " = '"+ id +"'";
            try {
                Cursor c = db.rawQuery(q2, null);
                comprasModel = null;
                while (c.moveToNext()) {
                    comprasModel = new ComprasModel(
                            c.getInt(0),
                            c.getInt(1)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }

        return comprasModel;
    }

    public void deleteAllCompras(){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            try {
                String q = "DELETE FROM " + TABLE_COMPRAS;
                db.execSQL(q);
            } catch (Exception e) {
                e.getStackTrace();
            }
            db.close();
        }
    }

    public boolean deleteCompra(String id) {
        boolean ret = false;
        ComprasModel lugar = null;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "DELETE FROM " + TABLE_COMPRAS + " WHERE "+ ID + " = "+ id +"";
                db.execSQL(q);
                ret = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return ret;
    }

    //UNIDAD

    public boolean createUnidad(String unidad) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_UNIDAD + " (" + NOMBRE + ") " + "VALUES(" + "'" + unidad+ "')";
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

    public ArrayList<UnidadModel> getAllUnidades() {

        ArrayList<UnidadModel> array = new ArrayList<UnidadModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_UNIDAD+";";
            try {
                Cursor c = db.rawQuery(q, null);
                UnidadModel unidadModel = null;
                while (c.moveToNext()) {
                    unidadModel = new UnidadModel(
                            c.getInt(0),
                            c.getString(1)
                    );
                    array.add(unidadModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public void deleteAllUnidades(){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            try {
                String q = "DELETE FROM " + TABLE_UNIDAD;
                db.execSQL(q);
            } catch (Exception e) {
                e.getStackTrace();
            }
            db.close();
        }
    }

    //PRESENTACIONES

    public boolean createPresentacion(String presentacion) {
        boolean ret = false;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String q = "INSERT INTO " + TABLE_PRESENTACIONES + " (" + NOMBRE + ") " + "VALUES(" + "'" + presentacion+"')";
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

    public ArrayList<PresentacionesModel> getAllPresentaciones() {

        ArrayList<PresentacionesModel> array = new ArrayList<PresentacionesModel>();

        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {

            String q = "SELECT * FROM " + TABLE_PRESENTACIONES;
            try {
                Cursor c = db.rawQuery(q, null);
                PresentacionesModel presentaciones = null;
                while (c.moveToNext()) {
                    presentaciones = new PresentacionesModel(
                            c.getInt(0),
                            c.getString(1)
                    );
                    array.add(presentaciones);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
        }
        return array;
    }

    public void deleteAllPresentaciones(){

        SQLiteDatabase db = getWritableDatabase();
        if(db != null) {
            try {
                String q = "DELETE FROM " + TABLE_PRESENTACIONES;
                db.execSQL(q);
            } catch (Exception e) {
                e.getStackTrace();
            }
            db.close();
        }
    }



}
