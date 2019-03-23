package com.example.van.mybakingapp.ingredient;

import android.os.Parcel;
import android.os.Parcelable;

public class Model  implements Parcelable{

    String mQuantity,mMeasure,mIngredient;


    public  Model(String quantity,String measure,String ingredient){

        mQuantity=quantity;
        mMeasure=measure;
        mIngredient=ingredient;


    }


    protected Model(Parcel in) {
        mQuantity = in.readString();
        mMeasure = in.readString();
        mIngredient = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public String getmQuantity() {
        return mQuantity;
    }

    public String getmMeasure() {
        return mMeasure;
    }

    public String getmIngredient() {
        return mIngredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
        dest.writeString(mIngredient);
    }
}
