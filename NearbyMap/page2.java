package com.example.vin.myapplication.NearbyMap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vin.myapplication.R;

public class page2 extends AppCompatActivity {
TextView tv1 ;
String position;
ImageButton ibBus,ibBank,ibRest,ibGas,ibHotel,ibShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        tv1 = (TextView) findViewById(R.id.textView2);
        tv1.setText(getIntent().getStringExtra("Place"));
        position=getIntent().getStringExtra("Position");

        ibBus = (ImageButton) findViewById(R.id.ibBus);
        ibBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+position+"?q=Bus Station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
        ibRest = (ImageButton) findViewById(R.id.ibRest);
        ibRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+position+"?q=Restaurant");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
        ibGas = (ImageButton) findViewById(R.id.ibGas);
        ibGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+position+"?q=Gas Station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
        ibHotel = (ImageButton) findViewById(R.id.ibHotel);
        ibHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+position+"?q=Hotel");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
        ibShop = (ImageButton) findViewById(R.id.ibShop);
        ibShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+position+"?q=Retail Shop");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });
        ibBank = (ImageButton) findViewById(R.id.ibBank);
        ibBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:"+position+"?q=Bank");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });


    }
}
