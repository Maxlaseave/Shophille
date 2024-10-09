package com.maxinetabugon.ecommerceui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ProductModel> productModels;

    public ProductRecyclerViewAdapter(Context context, ArrayList<ProductModel> productModels){
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ProductRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //where you inflate the layout (giving look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.products_layout, parent,false);
        return new ProductRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views we created in the product_layout file
        //based on the position of the recycler view
        holder.tvBrand.setText(productModels.get(position).getBrand());
        holder.tvItem.setText(productModels.get(position).getItemName());
        holder.tvPrice.setText(productModels.get(position).getPrice());
        holder.imageView.setImageResource(productModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        //how many items you want to display

        return productModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing all the vies from the product_layout
        ImageView imageView;
        TextView tvBrand, tvItem, tvPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView4);
            tvBrand = itemView.findViewById(R.id.branddisplay);
            tvItem = itemView.findViewById(R.id.itemnamedisplay);
            tvPrice = itemView.findViewById(R.id.pricedisplay);
        }
    }
}
