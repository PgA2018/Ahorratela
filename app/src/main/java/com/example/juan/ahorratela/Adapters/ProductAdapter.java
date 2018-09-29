package com.example.juan.ahorratela.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juan.ahorratela.Modelos.Product;
import com.example.juan.ahorratela.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by BMIX2 on 29/09/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> items;

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_productos, parent, false);
        return new ProductViewHolder(v);
    }

    public ProductAdapter(List<Product> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        holder.id.setText(items.get(position).getId());
        holder.name.setText(items.get(position).getName());
        holder.price.setText(items.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView name;
        private TextView price;
        private View v;

        public ProductViewHolder(View itemView) {

            super(itemView);
            v = itemView;
            id = inicializateView(R.id.idProducto);
            name = inicializateView(R.id.nombreproductlbl);
            price = inicializateView(R.id.precioProductoLBL);
        }

        private TextView inicializateView(int id) {
            return (TextView) v.findViewById(id);
        }
    }
}
