package com.example.van.mybakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.van.mybakingapp.showVideo.ShowVideoFragment;
import com.example.van.mybakingapp.stepsNumbers.StepNumberAdapter;
import com.example.van.mybakingapp.video.Video;

import java.util.ArrayList;

public class VideoStepsActivity extends AppCompatActivity implements StepNumberAdapter.ItemClick {
    public static String videoUrl;

    StepNumberAdapter adapter;
    ArrayList<Video> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_video_steps);

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.steps_recycler_view);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         recyclerView.setLayoutManager(layoutManager);
        videos=MainActivity.videos;
        adapter=new StepNumberAdapter(this,videos,this);
        recyclerView.setAdapter(adapter);

        Intent intent=getIntent();
       videoUrl= intent.getStringExtra("url");
       String descriptionString=intent.getStringExtra("description");

        TextView description=(TextView) findViewById(R.id.description);
        description.setText(descriptionString);



        FragmentManager manager=getSupportFragmentManager();
        ShowVideoFragment fragment=new ShowVideoFragment();
        manager.beginTransaction()
                .add(R.id.video_view_fragment,fragment).commit();


    }

    @Override
    public void onItemClick(String url,String description) {
        VideoStepsActivity.this.videoUrl=url;
       // VideoStepsActivity.this.descriptionString=description;
        TextView descriptionText=(TextView) findViewById(R.id.description);
       descriptionText.setText(description);


        FragmentManager manager=getSupportFragmentManager();
        ShowVideoFragment fragment=new ShowVideoFragment();
        manager.beginTransaction()
                .replace(R.id.video_view_fragment,fragment).commit();


        Log.v("VideoStepsActivity","video url"+url);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }

}
