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
import android.widget.EditText;
import android.widget.Toast;

import com.example.juan.ahorratela.Adapters.LugaresAdapter;
import com.example.juan.ahorratela.DB.LugaresDB;
import com.example.juan.ahorratela.Modelos.LugaresModel;
import com.example.juan.ahorratela.R;

import java.util.ArrayList;

public class LugaresFragment extends Fragment {

    RecyclerView recyclerView;
    View v;
    FloatingActionButton buttonAdd;
    ArrayList<LugaresModel> lugares = new ArrayList<>();
    LugaresAdapter lugaresAdapter;
    LinearLayoutManager llm;
    Dialog dialog;
    LugaresDB lugaresDB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LugaresFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LugaresFragment newInstance(String param1, String param2) {
        LugaresFragment fragment = new LugaresFragment();
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
        v = inflater.inflate(R.layout.fragment_lugares, container, false);

        lugaresDB = new LugaresDB(getContext());

        lugares = lugaresDB.getAll();

        buttonAdd = (FloatingActionButton) v.findViewById(R.id.addLugar);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup(view);
            }
        });

        dialog = new Dialog(getContext());

        llm = new LinearLayoutManager(v.getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.lugares);
        recyclerView.setLayoutManager(llm);

        lugaresAdapter = new LugaresAdapter(lugares);
        recyclerView.setAdapter(lugaresAdapter);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void ShowPopup(View v) {
        final EditText editTextNombre;
        final EditText editTextUbicacion;
        FloatingActionButton buttonGuardar;
        FloatingActionButton buttonCancelar;

        dialog.setContentView(R.layout.popup_registrar_lugares);

        editTextNombre = (EditText) dialog.findViewById(R.id.editTextNombre);
        editTextUbicacion = (EditText) dialog.findViewById(R.id.editTextUbicacion);
        buttonGuardar = (FloatingActionButton) dialog.findViewById(R.id.btnGuardarLugar);
        buttonCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarLugar);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarTexto(editTextNombre.getText().toString()) && validarTexto(editTextUbicacion.getText().toString())){
                    boolean bool = lugaresDB.create(new LugaresModel(
                            1,
                            editTextNombre.getText().toString(),
                            editTextUbicacion.getText().toString())
                    );
                    if(bool){
                        lugares = lugaresDB.getAll();
                        lugaresAdapter = new LugaresAdapter(lugares);
                        recyclerView.setAdapter(lugaresAdapter);
                        dialog.dismiss();
                    }
                }else{
                    Toast.makeText(view.getContext(), "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
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