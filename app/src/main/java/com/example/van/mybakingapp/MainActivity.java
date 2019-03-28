package com.example.van.mybakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.van.mybakingapp.idlingResource.DownloaderRecipe;
import com.example.van.mybakingapp.idlingResource.SimplingResource;
import com.example.van.mybakingapp.ingredient.Model;
import com.example.van.mybakingapp.utils.JsonExtractor;
import com.example.van.mybakingapp.utils.Recipe;
import com.example.van.mybakingapp.utils.RecipeAdapter;
import com.example.van.mybakingapp.video.Video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements  DownloaderRecipe.CallbackInterface{

    public static ArrayList<Model> ingredients;
    public  static ArrayList<Video> videos;
    //ArrayList<Recipe> recipes;
    ListView listView;
    RecipeAdapter adapter;
   public static  ArrayList<Recipe> recipes;
   public  static int position;
   public  static  String url;
   private SimplingResource resource;


   public IdlingResource getIdlingResources(){
       if(resource==null){
           resource=new SimplingResource();
       }
return resource;
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipes=new ArrayList<>();
        listView=(ListView) findViewById(R.id.listView);
         url="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

        //String json=getString(R.string.json);

        ConnectivityManager manager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        if(networkInfo!=null &&networkInfo.isConnected()){
            Toast.makeText(this,"Network Connected",Toast.LENGTH_LONG).show();
             new Task().execute(url);
            adapter=new RecipeAdapter(this,recipes);
            listView.setAdapter(adapter);
        }else {
            Toast.makeText(this," Make Sure that Network Connected",Toast.LENGTH_LONG).show();


        }


                  getIdlingResources();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe=recipes.get(position);
                 Log.v("MainActivity","ssssssssssssssss"+recipes.get(0));
                      position=position;

               ingredients= recipe.getmIngredients();
               Model model=ingredients.get(position);


             Intent intent=new Intent(getApplicationContext(),RecipeDetail.class);
                intent.putParcelableArrayListExtra("array",ingredients);


                Log.v("MainActivity","ssssssssssssssss"+ingredients.size());

                videos=recipe.getmVideoSteps();
                Log.v("MainActivity","ssssssssssssssss"+videos.size());



                startActivity(intent);


                Intent intent1=new Intent(getApplicationContext(),WidgetServiceIntent.class);
                intent1.putExtra("position",position);
                Log.v("NewAppWidget2","position intent is"+position);
                getApplicationContext().startService(intent);


                Intent initialUpdateIntent=new Intent(AppWidgetManager.
                        ACTION_APPWIDGET_UPDATE);
                //initialUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);
                sendBroadcast(initialUpdateIntent);



            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        DownloaderRecipe.downloadImage(this,MainActivity.this,resource);
    }

    @Override
    public void onDone(ArrayList<Recipe> recipes) {
        adapter=new RecipeAdapter(this,recipes);
        listView.setAdapter(adapter);

    }

    class Task extends AsyncTask<String,Void,ArrayList<Recipe>>{

        @Override
        protected ArrayList<Recipe> doInBackground(String... strings) {
            ArrayList<Recipe> recipes=JsonExtractor.extractJson(strings[0]);
            return recipes;
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            super.onPostExecute(recipes);
           // adapter=new RecipeAdapter(getApplicationContext(),recipes);
            //listView.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
            if (recipes != null && !recipes.isEmpty()) {
                adapter.addAll(recipes);
            }



        }
    }


    public static void sartService(Context context){
        Intent intent=new Intent(context,WidgetServiceIntent.class);
        intent.putExtra("position",position);
        context.startService(intent);
    }
}
