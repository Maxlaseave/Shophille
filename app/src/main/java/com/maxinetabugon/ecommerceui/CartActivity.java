package com.maxinetabugon.ecommerceui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CartActivity extends AppCompatActivity {

    ImageView incrBtn, decrBtn;
    TextView cartQuantity, totalAmount, amountPayable, checkoutBtn, brandDisplay, itemNameDisplay;


    private int ctr = 1;
    private static final int PRICE = 49;  //
    private static final int SHIPPING_COST = 29;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        incrBtn = (ImageView)findViewById(R.id.incrbtn);
        decrBtn = (ImageView)findViewById(R.id.decrbtn);
        cartQuantity = (TextView)findViewById(R.id.cartquantity);
        totalAmount = (TextView)findViewById(R.id.totalamount);
        amountPayable = (TextView)findViewById(R.id.amountpayable);
        checkoutBtn =(TextView)findViewById(R.id.checkoutbtn);
        brandDisplay=(TextView)findViewById(R.id.branddisplay);
        itemNameDisplay=(TextView)findViewById(R.id.itemnamedisplay);


        incrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctr++;
                updateCart();
            }
        });

        decrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ctr > 0) {
                    ctr--;
                    updateCart();
                } else {
                    Toast.makeText(CartActivity.this, "Minimum quantity is 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(CartActivity.this, OrderActivity.class);
                startActivity(cartIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void updateCart() {
        cartQuantity.setText(String.valueOf(ctr));

        int total = ctr * PRICE;
        int amountPayableValue = total + SHIPPING_COST;

        totalAmount.setText(String.format("$%d", total));
        amountPayable.setText(String.format("$%d", amountPayableValue));
    }
}
