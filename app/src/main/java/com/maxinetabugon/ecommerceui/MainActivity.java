package com.maxinetabugon.ecommerceui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout newArrivalLayout;
    ConstraintLayout popularProductsLayout;
    ImageView cartButton;
    DBHelper dbHelper;
    HRecyclerViewAdapter hRecyclerViewAdapter;

    ArrayList<String> brand_name, item_name, price;
    ArrayList<byte[]> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);
        //Initial Insert data into the database
        try{
            byte[] image1 = getImageBytes(R.drawable.relax_dry);
            dbHelper.addProduct("Uniqlo", "Relax Dry Fit Shorts", "$59", image1);
            byte[] image2 = getImageBytes(R.drawable.jogger);
            dbHelper.addProduct("H&M", "Jogger", "$79", image2);
            byte[] image3 = getImageBytes(R.drawable.woolskirt);
            dbHelper.addProduct("Zara", "Wool Skirt", "$70", image3);
        }catch (Exception e){
            e.printStackTrace();
        }


        brand_name = new ArrayList<>();
        item_name = new ArrayList<>();
        price = new ArrayList<>();
        images = new ArrayList<>();

        storeDataInArrays();

        hRecyclerViewAdapter = new HRecyclerViewAdapter(MainActivity.this, brand_name, item_name, price, images);;

        RecyclerView recyclerView = findViewById(R.id.horizontalRv);
        recyclerView.setAdapter(hRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));


        newArrivalLayout = (ConstraintLayout)findViewById(R.id.newarrivallayout);
        popularProductsLayout = (ConstraintLayout)findViewById(R.id.popularproductslayout);
        cartButton = (ImageView)findViewById(R.id.cartbutton);


        newArrivalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Directing to New Arrivals", Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(MainActivity.this, NewArrivalsActivity.class);
                startActivity(mainIntent);
            }
        });

        popularProductsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Directing to Popular Products", Toast.LENGTH_SHORT).show();
                Intent mainIntent = new Intent(MainActivity.this, PopularProductsActivity.class);
                startActivity(mainIntent);
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(mainIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private byte[] getImageBytes(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                brand_name.add(cursor.getString(1));
                item_name.add(cursor.getString(2));
                price.add(cursor.getString(3));
                images.add(cursor.getBlob(4));
            }
        }
        cursor.close();
    }

}