package com.example.vin.myapplication.Announcement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vin.myapplication.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class AnnouncementDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);

        Intent intent =getIntent();
        String image = getIntent().getStringExtra("extra_image");
        String date = getIntent().getStringExtra("extra_date");
        String location = getIntent().getStringExtra("extra_location");
        String title = getIntent().getStringExtra("extra_title");
        String des = getIntent().getStringExtra("extra_des");

        ImageView imageView = (ImageView) findViewById(R.id.dImage);
        TextView sDate = (TextView)findViewById(R.id.dDate);
        TextView sLocation = (TextView)findViewById(R.id.dLocation);
        TextView sTitle = (TextView)findViewById(R.id.dTitle);
        TextView sDes = (TextView)findViewById(R.id.dDescription);

        sDate.setText(date);
        sLocation.setText(location);
        sTitle.setText(title);
        sDes.setText(des);
        Glide.with(this)
                .asBitmap()
                .load(image)
                .into(imageView);
    }
}
