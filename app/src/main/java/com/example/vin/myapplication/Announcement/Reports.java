package com.example.vin.myapplication.Announcement;

import java.util.Date;

public class Reports {
    private String username, title, description, location;
    private String date;
    private int id;
    private String mImageUrl;
    public Reports(){

    }

    public String getmImageUrl(){return mImageUrl;}

    public void setmImageUrl(){this.mImageUrl= mImageUrl;}

    public String getLocation(){return location;}

    public void setLocation(String location){this.location = location;}

    public String getUser(){ return username;}

    public void setUser(String username){this.username=username;}

    public String getTitle(){return title;}

    public void setTitle(){this.title = title;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    public int getId(){return id;}

    public void setId(int id){this.id = id; }



    public Reports(String username,String location, String title, String description, String date, int id, String mImageUrl){
        this.username = username;
        this.location = location;
        this.title = title;
        this.description = description;
        this.date = date;
        this.id = id;
        this.mImageUrl = mImageUrl;

    }
}

