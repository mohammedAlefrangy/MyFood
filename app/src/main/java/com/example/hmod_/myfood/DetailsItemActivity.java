package com.example.hmod_.myfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsItemActivity extends AppCompatActivity {

    Button order_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        ImageView imagItem =(ImageView)findViewById(R.id.imagItem);
        TextView Description_of_item = (TextView) findViewById(R.id.Description_of_item);
        TextView Discount_of_item = (TextView) findViewById(R.id.Discount_of_item);
        TextView name_of_food = (TextView) findViewById(R.id.name_of_food);
        TextView pric_of_item = (TextView) findViewById(R.id.pric_of_item);
        TextView seller_Of_item = (TextView) findViewById(R.id.seller_Of_item);

        ItemList itemList =  (ItemList)getIntent().getSerializableExtra("itemlist");

        name_of_food.setText(itemList.getName());
        pric_of_item.setText(itemList.getPrice());
        Description_of_item.setText(itemList.getDescription());
        Discount_of_item.setText(itemList.getDiscount());
        seller_Of_item.setText(itemList.getSeller());

        Picasso.get()
                .load(itemList.getImage())
                .into(imagItem);

        order_btn = (Button) findViewById(R.id.Order_now);

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsItemActivity.this, DeliveryInfo.class);
                startActivity(i);
            }
        });

    }
}
