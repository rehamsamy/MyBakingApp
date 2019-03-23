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
import com.example.van.mybakingapp.utils.JsonExtractor;
import com.example.van.mybakingapp.utils.Recipe;

import java.util.ArrayList;

public class RecipeDetail extends AppCompatActivity {

    public static ArrayList<Model> ingredient;

    DetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ListView listView=(ListView) findViewById(R.id.list);


//
//        Intent intent=getIntent();
//      ingredient=intent.getParcelableArrayListExtra("array");



      // adapter=new DetailAdapter(this,ingredient);
//        listView.setAdapter(adapter);
//
//



    }
}
