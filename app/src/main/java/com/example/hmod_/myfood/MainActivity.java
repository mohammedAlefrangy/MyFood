package com.example.hmod_.myfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler_profile_lists;
    private FirebaseRecyclerAdapter adapter;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ref = FirebaseDatabase.getInstance().getReference().child("TableItems");
        ArrayList<ItemList> itemLists = new ArrayList<ItemList>();
        recycler_profile_lists = (RecyclerView)findViewById(R.id.recycle_profile_list);
        fetchProfileLists();
    }

    public static class ProfileListHolder extends RecyclerView.ViewHolder {

        View mView;
        public ItemList my = new ItemList();
        public TextView NameOfItem;
        public TextView PriceOfItem;
        public ImageView ImageOfItem;

        View view;

        public ProfileListHolder(final View itemView) {
            super(itemView);
            mView = itemView;

            NameOfItem = (TextView) itemView.findViewById(R.id.item_name);
            PriceOfItem = (TextView) itemView.findViewById(R.id.price);
            ImageOfItem = (ImageView) itemView.findViewById(R.id.iv_img_item);
            }  }



    private void fetchProfileLists() {

        recycler_profile_lists.setHasFixedSize(true);
        recycler_profile_lists.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FirebaseRecyclerAdapter<ItemList, ProfileListHolder>(ItemList.class, R.layout.list_item, ProfileListHolder.class, ref) {


            @Override
            protected void populateViewHolder(final ProfileListHolder viewHolder, ItemList model, int position) {


                final ItemList itemList = model;

//                 viewHolder.ImageOfItem.setImageResource(Integer.parseInt(itemList.getImgItem()));
                 viewHolder.PriceOfItem.setText(itemList.getPrice());
                 viewHolder.NameOfItem.setText(itemList.getName());

                Picasso.get()
                        .load(itemList.getImage())
                        .into(viewHolder.ImageOfItem);



                     viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {

                              Intent intent= new Intent(MainActivity.this, DetailsItemActivity.class);
                              intent.putExtra("itemlist", itemList);
                              startActivity(intent);
                         }
                     });
            }


        };


        recycler_profile_lists.setAdapter(adapter);
    }
}
