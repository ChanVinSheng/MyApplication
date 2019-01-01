package com.example.vin.myapplication.Announcement;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vin.myapplication.R;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {
    String dDate,dLocation,dTitle,dDescription;
    String dImage;
    Context context;
    ArrayList<Reports> reports;

    public myAdapter(Context c, ArrayList<Reports> p){
        context = c;
        reports = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder,final int i) {
        myViewHolder.date.setText(reports.get(i).getDate());
        myViewHolder.location.setText(reports.get(i).getLocation());
        myViewHolder.title.setText(reports.get(i).getTitle());
        myViewHolder.description.setText(reports.get(i).getDescription());
        Picasso.get().load(reports.get(i).getmImageUrl()).into(myViewHolder.imageView1);

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dDate = reports.get(i).getDate();
                dLocation = reports.get(i).getLocation();
                dTitle = reports.get(i).getTitle();
                dDescription = reports.get(i).getDescription();
                dImage = reports.get(i).getmImageUrl();
                String passDate = dDate.toString();
                String passLocation = dLocation.toString();
                String passTitle = dTitle.toString();
                String passDes = dDescription.toString();
                String passImage = dImage.toString();
                Intent intent = new Intent(context,AnnouncementDetail.class);
                intent.putExtra("extra_image",passImage);
                intent.putExtra("extra_date",passDate);
                intent.putExtra("extra_location",passLocation);
                intent.putExtra("extra_title",passTitle);
                intent.putExtra("extra_des",passDes);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView date,location,title,description;
        LinearLayout parentLayout;
        CircleImageView imageView1;
        public MyViewHolder( View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.rDate);
            location = (TextView) itemView.findViewById(R.id.aLocation);
            title = (TextView) itemView.findViewById(R.id.rTitle);
            description = (TextView) itemView.findViewById(R.id.rDescription);
            imageView1 = (CircleImageView) itemView.findViewById(R.id.image1);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
