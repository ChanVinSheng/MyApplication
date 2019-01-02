package com.example.vin.myapplication.FixLocation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vin.myapplication.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder>{

    private ArrayList<String> mLocationNames = new ArrayList<>();
    private ArrayList<String> mLocationImages = new ArrayList<>();
    private Context mContext;

    public LocationAdapter(Context context, ArrayList<String> locationName, ArrayList<String> locationImage){
        mLocationImages = locationImage;
        mLocationNames = locationName;
        mContext = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_fixed_location,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        Glide.with(mContext)
                .asBitmap()
                .load(mLocationImages.get(i))
                .into(viewHolder.locationImage);

        viewHolder.locationName.setText(mLocationNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mLocationNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLocationNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView locationImage;
        TextView locationName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locationImage = itemView.findViewById(R.id.locationImage);
            locationName = itemView.findViewById(R.id.locationName);
            parentLayout = itemView.findViewById(R.id.fixedLocation_layout);
        }
    }
}
