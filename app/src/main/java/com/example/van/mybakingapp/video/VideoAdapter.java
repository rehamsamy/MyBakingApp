package com.example.van.mybakingapp.video;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.van.mybakingapp.R;

import java.util.ArrayList;

public class VideoAdapter extends ArrayAdapter<Video> {
    public VideoAdapter(@NonNull Context context, ArrayList<Video> videos) {
        super(context, 0,videos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.step_item,parent,false);
        Video video=getItem(position);

        TextView textView=(TextView)view.findViewById(R.id.decription_text);
        textView.setText(position+" : "+video.getmShortDescription());
        textView.setPadding(18,18,18,18);

        return view;


    }
}
