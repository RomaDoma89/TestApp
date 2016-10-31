package com.example.flexihub.test.testapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Roman on 27.10.2016.
 */

public class Line implements Parcelable {

    private String title;
    private String availability;

    public Line(String title, String availability) {
        this.title = title;
        this.availability = availability;
    }

    private Line(Parcel in) {
        title = in.readString();
        availability = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(availability);
    }

    public static final Creator<Line> CREATOR = new Creator<Line>() {
        @Override
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        @Override
        public Line[] newArray(int size) {
            return new Line[size];
        }
    };

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
