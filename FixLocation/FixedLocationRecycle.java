package com.example.vin.myapplication.FixLocation;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.vin.myapplication.MainMenu;
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
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initImageBitmaps();
    }

    private void initImageBitmaps(){

        mLocationImage.add("https://www.pavilion-kl.com/assets/web/images/banner_pavilion.jpg");
        mLocationNames.add("Pavilion");

        mLocationImage.add("https://www.tunehotels.com/wp-content/uploads/Blog/why-klcc-should-be-at-the-heart-of-your-holiday-in-malaysia/klcc-outdoor-park-morning.jpg");
        mLocationNames.add("Petronas Towers");

        mLocationImage.add("https://www.lipstiq.com/wp-content/uploads/2017/01/Screen_Shot_2015-01-02_at_3.14.28_PM_small.png");
        mLocationNames.add("Titiwangsa Lake Garden");

        mLocationImage.add("https://d3avoj45mekucs.cloudfront.net/rojakdaily/media/letchumy-tamboo/batu%20caves%20temple/2.jpg");
        mLocationNames.add("Batu Cave");

        mLocationImage.add("https://s-ec.bstatic.com/images/hotel/max1024x768/698/69854936.jpg");
        mLocationNames.add("Genting Highland");


        mLocationImage.add("https://klshopper.com/wp-content/uploads/2017/05/Merdeka-Square-1024x765.jpg");
        mLocationNames.add("Merdeka Square");



        initRecycleView();
    }

    private void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.fixLocationRecycle);
         LocationAdapter adapter = new LocationAdapter(this, mLocationNames,mLocationImage);
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(FixedLocationRecycle.this,MainMenu.class);

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
