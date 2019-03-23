package com.example.van.mybakingapp.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.van.mybakingapp.MainActivity;
import com.example.van.mybakingapp.R;
import com.example.van.mybakingapp.VideoStepsActivity;

public class VideoFragment  extends Fragment {
    private final static String TAG=VideoFragment.class.getSimpleName();
    public static String videoUrl;
    public  VideoFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_steps,container,false);
        ListView listView=(ListView) view.findViewById(R.id.step_list);

        VideoAdapter adapter=new VideoAdapter(getContext(), MainActivity.videos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Video video=MainActivity.videos.get(position);


                videoUrl=video.getmVideoUrl();
                String description=video.getmDescription();
                Log.v(TAG,"vvvvvvvvvvvv"+videoUrl);

                Intent intent=new Intent(getContext(), VideoStepsActivity.class);

                intent.putExtra("url",videoUrl);
                intent.putExtra("description",description);

                startActivity(intent);


            }
        });


        return view;
    }
}
