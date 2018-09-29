package com.example.juan.ahorratela.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.juan.ahorratela.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ComprasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ComprasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComprasFragment extends Fragment {
    Dialog dialog;
    View v;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView rv;
    //List<Estudiante> estudentList;
    Context context;
    FloatingActionButton buttonA;

    public ComprasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComprasFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void ShowPopup(View v) {
        dialog.setContentView(R.layout.popup_registrar_compra);
        RecyclerView lista;
        final EditText editTextProducto;
        final EditText editTextLugar;
        final EditText editTextPrecio;
        FloatingActionButton buttonGuardar;
        FloatingActionButton buttonCancelar;
        FloatingActionButton buttonBuscarProducto;
        FloatingActionButton buttonBuscarLugar;

        editTextProducto = (EditText) dialog.findViewById(R.id.editTextProducto);
        editTextLugar = (EditText) dialog.findViewById(R.id.editTextLugar);
        editTextPrecio = (EditText) dialog.findViewById(R.id.editTextPrecio);
        buttonGuardar = (FloatingActionButton) dialog.findViewById(R.id.btnGuardarCompra);
        buttonCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelarCompra);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
