package com.example.van.mybakingapp.utils;

import com.example.van.mybakingapp.ingredient.Model;

import java.util.ArrayList;
import com.example.van.mybakingapp.video.Video;

public class Recipe {
    String mId;
    String mName;
    String mServings;
    ArrayList<Model> mIngredients;
    ArrayList<Video> mVideoSteps;

    public  Recipe(String id, String name,String servings, ArrayList<Model> ingredients,ArrayList<Video>videoSteps){
        mId=id;
        mName=name;
        mServings=servings;
        mIngredients=ingredients;
        mVideoSteps=videoSteps;


    }

    public  Recipe(String name){
        mName=name;
    }





        public String getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmServings() {
        return mServings;
    }
//////////////////////////////////////////////////////////////////////////




    public ArrayList<Video> getmVideoSteps() {
        return mVideoSteps;
    }


    public ArrayList<Model> getmIngredients() {
        return mIngredients;
    }

}


