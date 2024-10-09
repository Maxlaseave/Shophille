package com.maxinetabugon.ecommerceui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopularProductsActivity extends AppCompatActivity {


    ArrayList<ProductModel> productModels = new ArrayList<>();
    int[] productImages = {R.drawable.relax_dry, R.drawable.jogger, R.drawable.woolskirt,R.drawable.relax_dry, R.drawable.jogger, R.drawable.woolskirt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_popular_products);

        RecyclerView recyclerView = findViewById(R.id.pRecyclerView);
        setUpProductModels();
        ProductRecyclerViewAdapter adapter = new ProductRecyclerViewAdapter(this,productModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void setUpProductModels(){
        String[] brandNames = getResources().getStringArray(R.array.branddisplay);
        String[] itemNames = getResources().getStringArray(R.array.itemnamedisplay);
        String[] prices = getResources().getStringArray(R.array.pricedisplay);

        for(int i = 0; i<brandNames.length; i++){
            productModels.add(new ProductModel (productImages[i],brandNames[i],
                    itemNames[i], prices[i]));
        }
    }

}