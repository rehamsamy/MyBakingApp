package com.example.van.mybakingapp.idlingResource;

import android.content.Context;

import com.example.van.mybakingapp.utils.Recipe;

import java.util.ArrayList;
import java.util.logging.Handler;

public class RecipeDownloader {
    private static final int DELAY_MILLIS = 3000;

    // Create an ArrayList of mTeas
    final static ArrayList<Recipe> mRecipes = new ArrayList<>();

    interface DelayerCallback{
        void onDone(ArrayList<Recipe> recipes);
    }

    static void downloadREcipe(Context context, final DelayerCallback callback, final SimpleIdlingresource resource){
        if(resource!=null){
           resource.setIdleState(false);
        }

        mRecipes.add(new Recipe("Nutella Pie"));
        mRecipes.add(new Recipe("Brownies"));
        mRecipes.add(new Recipe("Yellow Cake"));
        mRecipes.add(new Recipe("Cheescake"));


        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(callback!=null){
                    callback.onDone(mRecipes);
                }
                if(resource!=null){
                 resource.setIdleState(true);
                }

            }
        },DELAY_MILLIS);


    }

}
