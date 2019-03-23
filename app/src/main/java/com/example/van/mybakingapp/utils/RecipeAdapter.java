package com.example.van.mybakingapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.van.mybakingapp.R;

import java.util.ArrayList;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    final static String TAG=RecipeAdapter.class.getSimpleName();

    public RecipeAdapter(@NonNull Context context, ArrayList<Recipe> recipes) {
        super(context,0, recipes);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view= LayoutInflater.from(getContext()).inflate(R.layout.recipe_item,parent,false);
       Recipe recipe=getItem(position);

        TextView name=view.findViewById(R.id.recipe_namee);
        TextView servings=view.findViewById(R.id.serving_count);


        Log.v(TAG,"name"+recipe.getmName()+"size");


       name.setText(recipe.getmName());
        servings.setText("servings : "+recipe.getmServings());

       return view;
    }
}
