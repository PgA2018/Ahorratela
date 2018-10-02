package com.example.juan.ahorratela.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.R;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.LugaresViewHolder>{
    List<ComprasModel> compraList;
    Context context;
    AhorratelaDB lugaresDB;
    int posMinimo;

    public ComprasAdapter(List<ComprasModel> compraList) {
        this.compraList = compraList;
        posMinimo = menorCompra();
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
        ComprasModel compra = compraList.get(position);
        holder.id_producto.setText(compra.getNombre_producto());
        holder.valor.setText("$ "+compra.getValor_compra());
        if(posMinimo == position){
            holder.icono.setImageResource(R.drawable.ic_happy);
        }else {
            holder.icono.setImageResource(R.drawable.ic_sad);
        }

    }

    @Override
    public int getItemCount() {
        return compraList.size();
    }

    public class LugaresViewHolder extends RecyclerView.ViewHolder{
        TextView id_producto;
        TextView valor;
        ImageView icono;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            id_producto = (TextView) itemView.findViewById(R.id.producto_name);
            valor = (TextView) itemView.findViewById(R.id.producto_value);
            icono = (ImageView) itemView.findViewById(R.id.icono);
        }
    }

    public int menorCompra(){
        int posMinimo = 0;
        if(compraList.size() > 0) {
            ComprasModel compra = compraList.get(0);
            int valorMinimo = compra.getValor_compra();
            for (int i = 0; i < compraList.size(); i++) {
                ComprasModel comprasModel = compraList.get(i);
                if (comprasModel.getValor_compra() < valorMinimo) {
                    posMinimo = i;
                }
            }
        }
        return posMinimo;
    }
}
