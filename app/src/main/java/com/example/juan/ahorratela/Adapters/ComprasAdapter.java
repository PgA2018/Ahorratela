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
    float valorMinimo;
    float Vmayorg;
    float Vmenorg;
    float Gramos;
    float valorAhorro;
    //int gramos;

    public ComprasAdapter(List<ComprasModel> compraList) {
        this.compraList = compraList;
    }

    @Override
    public LugaresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compras, parent, false);
        LugaresViewHolder lugaresViewHolder = new LugaresViewHolder(v);
        context = v.getContext();
        ahorratelaDB = new AhorratelaDB(v.getContext());
        posMinimo = menorCompra();
        //Vmenorg = menorComparacion();
        //Vmayorg = mayorComparacion();
        return lugaresViewHolder;
    }

    @Override
    public void onBindViewHolder(LugaresViewHolder holder, int position) {
        ComprasModel compra = compraList.get(position);
        ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());

        Gramos = unidad(prod.getUnidad(),prod.getMedida());
        //Toast.makeText(context, ""+valorAhorro, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, ""+Gramos, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, ""+Vmayorg, Toast.LENGTH_SHORT).show();
        //Toast.makeText(context, ""+Vmenorg, Toast.LENGTH_SHORT).show();
        holder.idProducto.setText(""+compra.getId_producto());
        holder.idLugar.setText(""+compra.getId_ubicacion());
        holder.nombreProducto.setText(compra.getNombre_producto()+" "+prod.getPresentacion()+ " "+ prod.getMedida() +" " + prod.getUnidad());
       // holder.valor.setText("$ "+compra.getValor_compra());


        /*if(posMinimo == position && Vmayorg>Vmenorg){
            valorAhorro = Gramos*Vmayorg - Gramos*Vmenorg;
            holder.valor.setText("$ "+valorAhorro);
        }else{
            Vmayorg=0;
            valorAhorro = Gramos*Vmayorg - Gramos*Vmenorg;
            holder.valor.setText("$ "+valorAhorro);
        }*/

        //valorAhorro = Gramos*Vmayorg - compra.getValor_compra()/Gramos;
        //holder.valor.setText("$ "+valorAhorro);

        if(posMinimo == position){
            holder.icono.setImageResource(R.drawable.ic_happy);
        }else {
            holder.icono.setImageResource(R.drawable.ic_sad);
        }
        float aux = valorMinimo - compra.getValor_compra()/prod.getMedida();
        holder.valor.setText("$ "+aux);
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
            valorMinimo = (float) compra.getValor_compra()/unidad(prod.getUnidad(), prod.getMedida());
            for (int i = 0; i < compraList.size(); i++) {
                ComprasModel comprasModel = compraList.get(i);
                prod = ahorratelaDB.getProductoById(comprasModel.getId_producto());
                float valor = (float) comprasModel.getValor_compra()/unidad(prod.getUnidad(),prod.getMedida());
                if ( valor < valorMinimo) {
                    posMinimo = i;
                    valorMinimo = valor;
                }
            }
        }
        return posMinimo;
    }

    public float mayorComparacion(){
        int posMaximo = 0;
        float valorMaximo = 0;
        if(compraList.size() > 0) {
            ComprasModel compra = compraList.get(0);
            ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());
            valorMaximo = (float) compra.getValor_compra()/unidad(prod.getUnidad(), prod.getMedida());
            for (int i = 0; i < compraList.size(); i++) {
                ComprasModel comprasModel = compraList.get(i);
                prod = ahorratelaDB.getProductoById(comprasModel.getId_producto());
                float valor = (float) comprasModel.getValor_compra()/unidad(prod.getUnidad(),prod.getMedida());
                if ( valor > valorMaximo) {
                    posMaximo = i;
                }
            }
        }
        return valorMaximo;
    }

    public float menorComparacion(){
        int posMaximo = 0;
        float valorMinimo = 0;
        if(compraList.size() > 0) {
            ComprasModel compra = compraList.get(0);
            ProductosModel prod = ahorratelaDB.getProductoById(compra.getId_producto());
            valorMinimo = (float) compra.getValor_compra()/unidad(prod.getUnidad(), prod.getMedida());
            for (int i = 0; i < compraList.size(); i++) {
                ComprasModel comprasModel = compraList.get(i);
                prod = ahorratelaDB.getProductoById(comprasModel.getId_producto());
                float valor = (float) comprasModel.getValor_compra()/unidad(prod.getUnidad(),prod.getMedida());
                if ( valor < valorMinimo) {
                    posMaximo = i;
                }
            }
        }
        return valorMinimo;
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
