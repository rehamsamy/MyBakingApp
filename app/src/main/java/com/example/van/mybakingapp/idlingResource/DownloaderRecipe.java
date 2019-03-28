package com.example.van.mybakingapp.idlingResource;

import android.content.Context;

import com.example.van.mybakingapp.MainActivity;
import com.example.van.mybakingapp.utils.JsonExtractor;
import com.example.van.mybakingapp.utils.Recipe;

import java.util.ArrayList;
import java.util.logging.Handler;

public class DownloaderRecipe {
   public interface  CallbackInterface{
        void onDone(ArrayList<Recipe> recipes);
    }


    public  static void downloadImage(Context context, final CallbackInterface callbackInterface, final SimplingResource resource){


        if(resource!=null){
         resource.setIdleState(false);


        }

        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(callbackInterface!=null){
                    ArrayList<Recipe> recipes= JsonExtractor.extractJson(MainActivity.url);
                     callbackInterface.onDone(recipes);
                }
                if(resource!=null){

                  resource.setIdleState(true);
                }
            }
        },10000);


    }

}
