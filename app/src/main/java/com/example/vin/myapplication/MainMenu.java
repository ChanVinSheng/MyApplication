package com.example.vin.myapplication;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainMenu extends AppCompatActivity {

    private DrawerLayout drawer;   //COPY THIS

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);  //COPY THIS
        setSupportActionBar(toolbar);  //COPY THIS

        drawer = findViewById(R.id.drawer_layout); //COPY THIS

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close); //COPY THIS
        drawer.addDrawerListener(toggle);  //COPY THIS
        toggle.syncState();  //COPY THIS

    }

    @Override //COPY THIS
    public void onBackPressed() {  //COPY THIS
        if(drawer.isDrawerOpen(GravityCompat.START)){ //COPY THIS
            drawer.closeDrawer(GravityCompat.START);  //COPY THIS
        }  //COPY THIS
        else{   //COPY THIS
            super.onBackPressed();  //COPY THIS
        }  //COPY THIS
    }  //COPY THIS
}
