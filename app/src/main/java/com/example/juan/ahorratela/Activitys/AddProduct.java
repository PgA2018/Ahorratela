package com.example.juan.ahorratela.Activitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juan.ahorratela.DB.ProductDB;
import com.example.juan.ahorratela.Modelos.Product;
import com.example.juan.ahorratela.R;

public class AddProduct extends AppCompatActivity {


    private EditText txtNombre;
    private EditText txtPrecio;
    private EditText txtMedida;
    private Spinner unidadDeMedida;
    private ArrayAdapter<CharSequence> unidadMedidaAdapter;
    private Button save;
    private Context context;
    private ProductDB productoDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setupComponents();

    }

    private void setupComponents() {
        context = this;
        txtNombre = setEditText(R.id.nombre_producto_field);
        txtMedida = setEditText(R.id.medida_field);
        txtPrecio = setEditText(R.id.precio_producto_field);
        inicializateSpinner();
        productoDbHelper = new ProductDB(context, "product.db", null, 1);
        save = (Button) findViewById(R.id.save_add_product_btn);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast toast;
                Product product = null;
                try {
                    product = new Product(readString(txtNombre).toString(),
                            Integer.parseInt(readString(txtPrecio).toString()),
                            Integer.parseInt(readString(txtMedida).toString()),
                            unidadDeMedida.getSelectedItemPosition());
                    if (productoDbHelper.saveProduct(product) == -1)
                        Toast.makeText(context, "Error al guardar", Toast.LENGTH_SHORT).show();

                    else {
                        Toast.makeText(context, "Exito al guardar", Toast.LENGTH_SHORT).show();
                        limpiarTxt(txtNombre);
                        limpiarTxt(txtMedida);
                        limpiarTxt(txtPrecio);
                    }
                } catch (Exception e) {
                    toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });

    }

    private void limpiarTxt(EditText txt) {
        txt.setText("");
    }

    private Object readString(EditText text) throws Exception {
        Object texto = text.getText();

        if (text.getText().toString().length() == 0) {
            text.requestFocus();
            throw new Exception("campo no puede estar vacío");
        }
        return texto;
    }


    private EditText setEditText(int id) {
        return (EditText) findViewById(id);
    }

    private void inicializateSpinner() {
        unidadDeMedida = (Spinner) findViewById(R.id.unidad_medida_spinner);
        unidadMedidaAdapter = ArrayAdapter.createFromResource(context,
                R.array.unidad_medida_array, android.R.layout.simple_spinner_item);
        unidadMedidaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unidadDeMedida.setAdapter(unidadMedidaAdapter);
    }

}
