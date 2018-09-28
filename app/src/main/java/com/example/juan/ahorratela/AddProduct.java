package com.example.juan.ahorratela;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {


    private EditText txtNombre;
    private EditText txtPrecio;
    private EditText txtMedida;
    private Spinner unidadDeMedida;
    private ArrayAdapter<CharSequence> unidadMedidaAdapter;
    private Button save;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setupComponents();

    }
    private void setupComponents(){
        context=this;
        txtNombre = setEditText(R.id.nombre_producto_field);
        txtMedida= setEditText(R.id.medida_field);
        txtPrecio=setEditText(R.id.precio_producto_field);
        inicializateSpinner();
        save = (Button) findViewById(R.id.save_add_product_btn);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                
            }
        });

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
