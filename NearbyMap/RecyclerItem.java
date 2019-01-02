package com.example.vin.myapplication.NearbyMap;

public class RecyclerItem {
    private int mImageResource;
    private String mStr;
    private String mStr1;
    private String LongLat;

    public RecyclerItem(int ImageResource, String Str, String Str1, String position) {
        mImageResource = ImageResource;
        mStr = Str;
        LongLat = position;
        mStr1 = Str1;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getStr() {
        return mStr;
    }

    public String getStr1() {
        return mStr1;
    }

    public String getLongLat() {
        return LongLat;
    }

    public void setStrText(String text) {
        mStr = text;
    }

    public void setStr1Text(String text) {
        mStr1 = text;
    }
}
