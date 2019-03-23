package com.example.van.mybakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.van.mybakingapp.ingredient.Model;

import java.util.ArrayList;

public class DetailAdapter extends ArrayAdapter<Model> {
    private static final String TAG=DetailAdapter.class.getSimpleName();
   public static String ingredients ;

    ArrayList<Model> mingredients;
    public DetailAdapter(@NonNull Context context, ArrayList<Model> ingredients) {
        super(context,0, ingredients);
        mingredients=ingredients;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.ingredient_item,parent,false);
       // position=position;
        Model model=getItem(position);

        TextView quantity=view.findViewById(R.id.quantity);


        Log.v(TAG,"nameeeeeee"+model.getmQuantity()+"size");
       ingredients= model.getmIngredient();

             StringBuilder builder=new StringBuilder();
        //for(Model x: mingredients){
            builder.append("-"+model.getmIngredient()+"("+model.getmQuantity()+":"+model.getmMeasure()+")"+"\n");
       // }
        quantity.setText(builder);

        Log.v(TAG,"nameeeeeee"+builder.toString()+"size");

        return view;
    }

}