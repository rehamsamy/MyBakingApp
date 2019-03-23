package com.example.van.mybakingapp.stepsNumbers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.van.mybakingapp.R;
import com.example.van.mybakingapp.video.Video;

import java.util.ArrayList;

public class StepNumberAdapter extends RecyclerView.Adapter<StepNumberAdapter.Holder> {
    private static final String TAG=StepNumberAdapter.class.getSimpleName();

    ArrayList<Video> mvideos;
    Context mcontext;
    private ItemClick mOnClick;

    public interface ItemClick{
        void onItemClick(String url,String description);

    }


    public  StepNumberAdapter(Context context,ArrayList<Video> videos,ItemClick click){
        mvideos=videos;
        mcontext=context;
        mOnClick=click;
    }


    @NonNull
    @Override
    public StepNumberAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.step_item,viewGroup,false);


        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepNumberAdapter.Holder holder, int position) {
        final Video video=mvideos.get(position);
        String description=video.getmShortDescription();
        final String url=video.getmVideoUrl();
        final String decriptionText=video.getmDescription();


        holder.imageView.setVisibility(View.GONE);
        holder.textView.setText("step"+ ":"+position+"  ");
        holder.textView.setTextSize(20);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClick.onItemClick(url,decriptionText);
                Log.v(TAG,"urllll"+url);
            }
        });

       // holder.textView.setBackgroundColor(gR.color.colorAccent);



    }

    @Override
    public int getItemCount() {
        Log.v(TAG,"wwwwwwww"+mvideos.size());
        return mvideos.size();

    }

    public class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            textView=(TextView) itemView.findViewById(R.id.decription_text);
            imageView=(ImageView) itemView.findViewById(R.id.play);

        }
    }

}
