package com.example.van.mybakingapp.video;

import android.os.Parcel;
import android.os.Parcelable;

public class Video implements Parcelable{


    String mShortDescription,mDescription,mVideoUrl;

    public Video(String shortDescription, String description, String videoUrl) {
        mShortDescription = shortDescription;
        mDescription = description;
        mVideoUrl = videoUrl;
    }


    protected Video(Parcel in) {
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoUrl = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getmShortDescription() {
        return mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmVideoUrl() {
        return mVideoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mShortDescription);
        dest.writeString(mDescription);
        dest.writeString(mVideoUrl);
    }
}
