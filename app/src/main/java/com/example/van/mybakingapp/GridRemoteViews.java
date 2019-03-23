package com.example.van.mybakingapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.van.mybakingapp.utils.Recipe;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


class GridRemoteViews   implements RemoteViewsService.RemoteViewsFactory{
    private static final String TAG=GridRemoteViews.class.getSimpleName();

     ArrayList<Recipe> recipes;
    Context mcontext;
    public GridRemoteViews(Context context){
        mcontext=context;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        recipes=MainActivity.recipes;


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.v(TAG,"ssssssss"+recipes.size());
        return recipes.size();

    }

    @Override
    public RemoteViews getViewAt(int position) {
        Recipe recipe=recipes.get(position);
        RemoteViews views=new RemoteViews(mcontext.getPackageName(),R.layout.new_app_widget);
        views.setImageViewResource(R.id.recipe_widget_image,R.drawable.muffin);
       // views.setTextViewText(R.id.recipe_widget_name,recipe.getmName());
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
