package com.maxinetabugon.ecommerceui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class HRecyclerViewAdapter extends RecyclerView.Adapter<HRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList brand_name, item_name, price;
    private ArrayList<byte[]> images;

    HRecyclerViewAdapter(Context context, ArrayList brand_name, ArrayList item_name, ArrayList price, ArrayList<byte[]> images) {
       this.context = context;
       this.brand_name = brand_name;
       this.item_name = item_name;
       this.price = price;
       this.images = images;
    }

    @NonNull
    @Override
    public HRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.p_horizonatal_layout, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvBrand.setText(String.valueOf(brand_name.get(position)));
        holder.tvItem.setText(String.valueOf(item_name.get(position)));
        holder.tvPrice.setText(String.valueOf(price.get(position)));
        byte[] imageBytes = images.get(position);
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imageView.setImageBitmap(bitmap);
        }

    }

    @Override
    public int getItemCount() {
        return brand_name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvBrand, tvItem, tvPrice;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView323);
            tvBrand = itemView.findViewById(R.id.branddisplay);
            tvItem = itemView.findViewById(R.id.itemnamedisplay);
            tvPrice = itemView.findViewById(R.id.pricedisplay);
        }
    }
}




