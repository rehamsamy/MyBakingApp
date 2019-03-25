package com.example.van.mybakingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.van.mybakingapp.ingredient.IngredientFragment;
import com.example.van.mybakingapp.ingredient.Model;
import com.example.van.mybakingapp.showVideo.ShowVideoFragment;
import com.example.van.mybakingapp.utils.JsonExtractor;
import com.example.van.mybakingapp.utils.Recipe;
import com.example.van.mybakingapp.video.Video;
import com.example.van.mybakingapp.video.VideoFragment;

import java.util.ArrayList;

public class RecipeDetail extends AppCompatActivity implements VideoFragment.OnItemClickInterface {

    public static ArrayList<Model> ingredient;
    Boolean towPane;

    DetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ListView listView=(ListView) findViewById(R.id.list);


        if(findViewById(R.id.linear_layout_tow_pane)!=null){
            towPane=true;
        }
        else{
            towPane=false;
        }

    }

    @Override
    public void onItemClick(int position,String descriptionText) {


        if(towPane){

            // VideoStepsActivity.this.descriptionString=description;
            TextView textView=(TextView) findViewById(R.id.description);
            textView.setText(descriptionText);


            FragmentManager manager=getSupportFragmentManager();
            ShowVideoFragment fragment=new ShowVideoFragment();
            manager.beginTransaction()
                    .replace(R.id.video_view_fragment,fragment).commit();


        }
        else {

            Video video = MainActivity.videos.get(position);


            String videoUrl = video.getmVideoUrl();
            String description = video.getmDescription();
            //Log.v(TAG,"vvvvvvvvvvvv"+videoUrl);

            Intent intent = new Intent(this, VideoStepsActivity.class);

            intent.putExtra("url", videoUrl);
            intent.putExtra("description", description);
            startActivity(intent);
        }
    }
}
