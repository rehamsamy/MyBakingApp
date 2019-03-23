package com.example.van.mybakingapp.ingredient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.van.mybakingapp.DetailAdapter;
import com.example.van.mybakingapp.MainActivity;
import com.example.van.mybakingapp.R;
import com.example.van.mybakingapp.RecipeDetail;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {
    private static final String TAG=IngredientFragment.class.getSimpleName();

    public IngredientFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_ingredient,container,false);


        ListView listView=(ListView) view.findViewById(R.id.list);


        listView.setAdapter(new DetailAdapter(getContext(), MainActivity.ingredients));

        Log.v(TAG,"ffffffffff"+MainActivity.ingredients.size());



       return view;
    }
}
