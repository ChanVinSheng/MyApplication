package com.example.vin.myapplication.NearbyMap;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.vin.myapplication.FixLocation.FixedLocationRecycle;
import com.example.vin.myapplication.MAP.MapsActivity;
import com.example.vin.myapplication.MainMenu;
import com.example.vin.myapplication.R;

import java.util.ArrayList;

public class navigationMenu extends AppCompatActivity {
private RecyclerView mRecyclerView;
private RecyclerAdapter mAdapter;
private RecyclerView.LayoutManager mLayoutManager;
String tempPosition,tempPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final ArrayList<RecyclerItem> recyclerList = new ArrayList<>();
        recyclerList.add(new RecyclerItem(R.drawable.butkitb,"Bukit Bintang","Changkat Bukit Bintang,Jalan Alor,Berjaya Times Square,Menara KL","3.145894, 101.708163"));
        recyclerList.add(new RecyclerItem(R.drawable.cheras,"Cheras","Cheras Sentral Mall,Tamman Connaught,Cannaught Night Market","3.107392, 101.726547"));
        recyclerList.add(new RecyclerItem(R.drawable.chow,"Chow Kit","Petaling Street,Chow Kit Central Market,Gudware Tatt Khalsa Sikh Temple","3.163300, 101.697254"));
        recyclerList.add(new RecyclerItem(R.drawable.kepong,"Kepong","FRIM,Metropolitan Park,Desa Park City,Tzu-Chi Jing Si Hall","3.195924, 101.646087"));
        recyclerList.add(new RecyclerItem(R.drawable.sentul,"Sentul","Putra World Centre,Federal Territory Mosque,National Art Gallery","3.194247, 101.686419"));
        recyclerList.add(new RecyclerItem(R.drawable.setiawangsa,"Setia Wangsa","KLCC Park","3.179895, 101.744489"));
        recyclerList.add(new RecyclerItem(R.drawable.wangsa,"Wangsa Maju","Wangsa Walk,Aeon Big","3.197310, 101.742831"));


        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(recyclerList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                tempPosition = recyclerList.get(position).getLongLat();
                tempPlace = recyclerList.get(position).getStr();
                mAdapter.notifyItemChanged(position);
                Intent intent= new Intent(navigationMenu.this,page2.class);
                intent.putExtra("Position",tempPosition);
                intent.putExtra("Place",tempPlace);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed()
    {

        Intent intent = new Intent(navigationMenu.this,MainMenu.class);

        intent.putExtra("Check",1);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
