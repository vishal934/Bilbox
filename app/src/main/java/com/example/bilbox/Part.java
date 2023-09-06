package com.example.bilbox;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Part  implements  Parcelable{
    private String name;
    private int imageResId;
    private boolean isSelected;
    private int color;
    public static List<Part> partArrayList = new ArrayList<>();

    public Part(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.isSelected = false;
        this.color = Color.TRANSPARENT;
    }


    public Part() {

    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    protected Part(Parcel in) {
        name = in.readString();
        imageResId = in.readInt();
        isSelected = in.readByte() != 0;
        color = in.readInt();
    }

    public static final Creator<Part> CREATOR = new Creator<Part>() {
        @Override
        public Part createFromParcel(Parcel in) {
            return new Part(in);
        }

        @Override
        public Part[] newArray(int size) {
            return new Part[size];
        }
    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageResId);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeInt(color);
    }


    public int describeContents() {
        return 0;
    }
}

