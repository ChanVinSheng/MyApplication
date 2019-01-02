package com.example.vin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.vin.myapplication.FixLocation.FixedLocationRecycle;
import com.example.vin.myapplication.MAP.MapsActivity;
import com.example.vin.myapplication.NearbyMap.navigationMenu;

public class Place extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,container,false);


        ImageView myButton = (ImageView) view.findViewById(R.id.placenearby);
        myButton.setClickable(true);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), navigationMenu.class));
            }
        });

        ImageView myButton2 = (ImageView) view.findViewById(R.id.placemap);
        myButton2.setClickable(true);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });

        ImageView myButton3 = (ImageView) view.findViewById(R.id.placetop);
        myButton3.setClickable(true);
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FixedLocationRecycle.class));
            }
        });



        return view;
    }
}
