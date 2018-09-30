package com.example.juan.ahorratela.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.juan.ahorratela.Activitys.buttonClickInterface;
import com.example.juan.ahorratela.Adapters.ComprasLugaresAdapter;
import com.example.juan.ahorratela.Adapters.ComprasProductosAdapter;
import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComprasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComprasFragment#newInstance} factory method to
 * createLugar an instance of this fragment.
 */

public class ComprasFragment extends Fragment implements buttonClickInterface {
    Dialog dialog;
    View v;

    ComprasLugaresAdapter comprasLugaresAdapter;
    ComprasProductosAdapter comprasProductosAdapter;

    ArrayList<LugaresModel> lugares;
    ArrayList<ProductosModel> productos;

    LugaresModel lugar;
    ProductosModel producto;

    LinearLayoutManager llm;
    EditText editTextLugar;
    EditText editTextProducto;
    RecyclerView lista;
    FloatingActionButton buttonA;
    AhorratelaDB ahorratelaDB;
    InputMethodManager imm;
    buttonClickInterface buttonClickInterface;

    private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public ComprasFragment() {
        // Required empty public constructor
    }

    public static ComprasFragment newInstance(String param1, String param2) {
        ComprasFragment fragment = new ComprasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_compras, container, false);
        dialog = new Dialog(v.getContext());

        buttonA = (FloatingActionButton) v.findViewById(R.id.addProductoCompra);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(view);
            }
        });

        imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        buttonClickInterface = this;

        return v;
    }

    // TODO: Rename method, updateLugar argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void lugar(LugaresModel lugar) {
        this.lugar = lugar;
        editTextLugar.setText(this.lugar.getNombre().toString());
        lugares = new ArrayList<>();
        comprasLugaresAdapter = new ComprasLugaresAdapter(lugares,getActivity(),buttonClickInterface);
        lista.setAdapter(comprasLugaresAdapter);
    }

    @Override
    public void producto(ProductosModel producto) {
        this.producto = producto;
        editTextProducto.setText(this.producto.getNombre().toString());
        productos = new ArrayList<>();
        comprasProductosAdapter = new ComprasProductosAdapter(productos,getActivity(),buttonClickInterface);
        lista.setAdapter(comprasProductosAdapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void ShowPopup(final View v) {
        dialog.setContentView(R.layout.popup_registrar_compra);

        final EditText editTextPrecio;
        FloatingActionButton buttonGuardar;
        FloatingActionButton buttonCancelar;
        final FloatingActionButton buttonBuscarProducto;
        final FloatingActionButton buttonBuscarLugar;

        editTextProducto = (EditText) dialog.findViewById(R.id.editTextProducto);
        editTextLugar = (EditText) dialog.findViewById(R.id.editTextLugar);
        editTextPrecio = (EditText) dialog.findViewById(R.id.editTextPrecio);
        buttonGuardar = (FloatingActionButton) dialog.findViewById(R.id.btnGuardarCompra);
        buttonCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarCompra);
        buttonBuscarProducto = (FloatingActionButton) dialog.findViewById(R.id.btnBuscarProducto);
        buttonBuscarLugar = (FloatingActionButton) dialog.findViewById(R.id.btnBuscarLugar);
        lista = (RecyclerView) dialog.findViewById(R.id.list_item);

        ahorratelaDB = new AhorratelaDB(v.getContext());
        llm = new LinearLayoutManager(v.getContext());
        lista.setLayoutManager(llm);

        buttonBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productos = ahorratelaDB.getProductoByNombre(editTextProducto.getText().toString());
                comprasProductosAdapter = new ComprasProductosAdapter(productos, getActivity(), buttonClickInterface);
                lista.setAdapter(comprasProductosAdapter);
                imm.hideSoftInputFromWindow(buttonBuscarProducto.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        buttonBuscarLugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lugares = ahorratelaDB.getLugarByNombre(editTextLugar.getText().toString());
                comprasLugaresAdapter = new ComprasLugaresAdapter(lugares, getActivity(), buttonClickInterface);
                lista.setAdapter(comprasLugaresAdapter);
                imm.hideSoftInputFromWindow(buttonBuscarLugar.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public boolean validarTexto(String texto){
        boolean bool = true;
        if(texto.isEmpty()){
            bool = false;
        }
        if(texto == ""){
            bool = false;
        }
        if(texto == null){
            bool = false;
        }
        return bool;
    }
}