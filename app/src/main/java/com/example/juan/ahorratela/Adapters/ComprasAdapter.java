package com.example.juan.ahorratela.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juan.ahorratela.DB.AhorratelaDB;
import com.example.juan.ahorratela.Modelos.ComprasModel;
import com.example.juan.ahorratela.Modelos.ProductosModel;
import com.example.juan.ahorratela.R;

import java.util.List;

/**
 * Created by juan on 05/12/2017.
 */

public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.LugaresViewHolder>{
    List<ComprasModel> compraList;
    Context context;
    AhorratelaDB ahorratelaDB;
    int posMinimo;
    int valorgramo;
    int valorAhorro;
    //int gramos;

    public ComprasAdapter(List<ComprasModel> compraList) {
        this.compraList = compraList;
        //posMinimo = menorCompra();
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compras, parent, false);
        LugaresViewHolder lugaresViewHolder = new LugaresViewHolder(v);
        context = v.getContext();
        ahorratelaDB = new AhorratelaDB(v.getContext());
        posMinimo = menorCompra();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        ComprasModel compra = compraList.get(position);
        ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());

        holder.idProducto.setText(""+compra.getId_producto());
        holder.idLugar.setText(""+compra.getId_ubicacion());
        holder.nombreProducto.setText(compra.getNombre_producto()+" "+prod.getPresentacion()+ " "+ prod.getMedida() +" " + prod.getUnidad());
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
        TextView idProducto, idLugar;
        TextView nombreProducto;
        TextView valor;
        ImageView icono;
        TableLayout layoutCompras;

        public LugaresViewHolder(final View itemView) {
            super(itemView);
            idProducto = (TextView) itemView.findViewById(R.id.producto_id);
            idLugar = (TextView) itemView.findViewById(R.id.lugar_id);
            nombreProducto = (TextView) itemView.findViewById(R.id.producto_name);
            valor = (TextView) itemView.findViewById(R.id.producto_value);
            icono = (ImageView) itemView.findViewById(R.id.icono);
            layoutCompras = (TableLayout) itemView.findViewById(R.id.layoutCompras);

            layoutCompras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ahorratelaDB.createCompra(new ComprasModel(Integer.parseInt(idProducto.getText().toString()),Integer.parseInt(idLugar.getText().toString())))){
                        Toast.makeText(itemView.getContext(), "Se ha guardado la compra", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public int menorCompra(){
        int posMinimo = 0;
        if(compraList.size() > 0) {
            ComprasModel compra = compraList.get(0);
            ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());
            float valorMinimo = (float) compra.getValor_compra()/unidad(prod.getUnidad(), prod.getMedida());
            for (int i = 0; i < compraList.size(); i++) {
                ComprasModel comprasModel = compraList.get(i);
                prod = ahorratelaDB.getProductoById(comprasModel.getId_producto());
                float valor = (float) comprasModel.getValor_compra()/unidad(prod.getUnidad(),prod.getMedida());
                if ( valor < valorMinimo) {
                    posMinimo = i;
                }
            }
        }
        return posMinimo;
    }

    private float unidad(String unidad, int medida){
        float gramos = 0;
        if (unidad.toString().equalsIgnoreCase("lb")){
            gramos = 450*medida;
        }
        if (unidad.toString().equalsIgnoreCase("kg")){
            gramos = 1000*medida;

        }
        if (unidad.toString().equalsIgnoreCase("mg")){
            gramos = (float) (0.001*medida);
        }
        if (unidad.toString().equalsIgnoreCase("gr")){
            gramos = medida;
        }
        if (unidad.toString().equalsIgnoreCase("ml")){
            gramos = medida;
        }
        return gramos;
    }



}
