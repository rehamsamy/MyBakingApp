package com.example.van.mybakingapp.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.van.mybakingapp.R;
import com.example.van.mybakingapp.ingredient.Model;
import com.example.van.mybakingapp.video.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JsonExtractor {
    private  static final String TAG=JsonExtractor.class.getSimpleName();

  private static ArrayList<Recipe> recipes;

  private static ArrayList<Recipe> ingredients;



    public static ArrayList<Recipe> extractJson(String url){
        URL url1=createUrl(url);
        String json=readStream(url1);
        ArrayList<Recipe> recipes=extract(json);
        return  recipes;
    }


    private static URL createUrl(String url) {
        URL url1=null;
        try {
            url1=new URL(url);
            if(url1==null){
                Log.v("MainActivity","url is null");
            }else{
                Log.v("MainActivity","url is sucess");

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url1;
    }


    private static String readStream(URL url1) {

        StringBuilder builder = null;
        String jsonResponse = "";
        InputStream inputStream=null;

        // If the URL is null, then return early.
        if (url1 == null) {
            Log.v("MainActivity","url is null"+url1);
            return jsonResponse;

        }

        try {
            HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();
            //httpURLConnection.setRequestMethod("200_ok");
            if(httpURLConnection.getResponseCode()==200){
                Log.v("MainActivity","sucessed"+httpURLConnection.getResponseCode());
                inputStream=httpURLConnection.getInputStream();
                builder=new StringBuilder();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                while (line!=null){
                    builder.append(line);
                    line=bufferedReader.readLine();
                    Log.v("MainActivity","line not null"+line);
                }

            }else {
                Log.v("MainActivity","ffffffailed"+httpURLConnection.getResponseCode());


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return  builder.toString();
    }




    public static  ArrayList<Recipe> extract(String jsonResponce) {
        ArrayList<Model> ingredients=new ArrayList<>();
        ArrayList<Recipe> recipes=new ArrayList<>();
        ArrayList<Video> videos=new ArrayList<>();
        {
            try {
                if (TextUtils.isEmpty(jsonResponce)) {
                    Log.v(TAG, "json is null");
                    return null;
                }
                JSONArray   array = new JSONArray(jsonResponce);
                for(int i=0;i<array.length();i++){
                    JSONObject item=array.getJSONObject(i);
                    Log.v(TAG,"size is"+array.length());
                    String itemName=item.getString("name");
                    String itemId=item.getString("id");
                    String servings=item.getString("servings");
                   Log.v(TAG,"name +id" +itemName+itemId);

                    JSONArray array1=item.getJSONArray("ingredients");
                    ingredients = new ArrayList<>();
                    videos = new ArrayList<>();
                     for (int j=0;j<array1.length();j++){
                        JSONObject ingredientItem=array1.getJSONObject(j);
                        String quantity=ingredientItem.getString("quantity");
                        String measure=ingredientItem.getString("measure");
                        String ingredient=ingredientItem.getString("ingredient");
                        Log.v(TAG,"size1 is"+array1.length());
                         Model model=new Model(quantity,measure,ingredient);
                         ingredients.add(model);
                    }
                    Log.v(TAG,"size1 is"+array1.length());

                    JSONArray array2=item.getJSONArray("steps");
                    for(int k=0;k<array2.length();k++){
                        JSONObject stepItem=array2.getJSONObject(k);
                        String shortDescription=stepItem.getString("shortDescription");
                        String description=stepItem.getString("description");
                        String videoURL=stepItem.getString("videoURL");
                        Log.v(TAG,"size2 is"+array2.length());

                        Video video=new Video(shortDescription,description,videoURL);
                        videos.add(video);
                    }

                    Recipe recipe=new Recipe(itemId,itemName,servings,ingredients,videos);
                    recipes.add(recipe);

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  recipes;

    }




}
