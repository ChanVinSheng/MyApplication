package com.example.vin.myapplication.FixLocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vin.myapplication.R;

import java.util.ArrayList;

public class FixedLocationRecycle extends AppCompatActivity {
    //vars
    private ArrayList<String> mLocationNames = new ArrayList<>();
    private ArrayList<String> mLocationImage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_location);

        initImageBitmaps();
    }

    private void initImageBitmaps(){

        mLocationImage.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mLocationNames.add("Trondheim");

        mLocationImage.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mLocationNames.add("Portugal");

        mLocationImage.add("https://i.redd.it/j6myfqglup501.jpg");
        mLocationNames.add("Rocky Mountain National Park");


        mLocationImage.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mLocationNames.add("Mahahual");

        mLocationImage.add("https://i.redd.it/k98uzl68eh501.jpg");
        mLocationNames.add("Frozen Lake");


        mLocationImage.add("https://i.redd.it/glin0nwndo501.jpg");
        mLocationNames.add("White Sands Desert");

        mLocationImage.add("https://i.redd.it/obx4zydshg601.jpg");
        mLocationNames.add("Austrailia");

        mLocationImage.add("https://i.imgur.com/ZcLLrkY.jpg");
        mLocationNames.add("Washington");

        initRecycleView();
    }

    private void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.fixLocationRecycle);
         LocationAdapter adapter = new LocationAdapter(this, mLocationNames,mLocationImage);
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
