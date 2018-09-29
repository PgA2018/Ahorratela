package com.example.juan.ahorratela.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juan.ahorratela.DB.LugaresDB;
import com.example.juan.ahorratela.Modelos.Compra;
import com.example.juan.ahorratela.Activitys.buttonClickInterface;
import com.example.juan.ahorratela.R;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.LugaresViewHolder>{
    List<Compra> compraList;
    Context context;
    String plato;
    LugaresDB lugaresDB;


    public ComprasAdapter(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compras, parent, false);
        LugaresViewHolder lugaresViewHolder = new LugaresViewHolder(v);
        context = v.getContext();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        Compra lugares = compraList.get(position);
        holder.id.setText(lugares.getId());
        holder.producto.setText(lugares.getId_producto());
        holder.valor.setText(lugares.getId_ubicacion());
    }

    @Override
    public int getItemCount() {
        return compraList.size();
    }

    public class LugaresViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView producto;
        TextView valor;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.producto_id);
            producto = (TextView) itemView.findViewById(R.id.producto_name);
            valor = (TextView) itemView.findViewById(R.id.producto_value);
        }
    }
}
