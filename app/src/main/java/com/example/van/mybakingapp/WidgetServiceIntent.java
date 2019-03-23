package com.example.van.mybakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.van.mybakingapp.ingredient.Model;

import java.util.ArrayList;

public  class WidgetServiceIntent extends IntentService {

    int position;
    public WidgetServiceIntent() {
        super("WidgetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
         position=  intent.getIntExtra("position",0);
        Log.v("WidgetServiceIntent","position is"+position);
       updateWidget(getApplicationContext());

    }

    private void updateWidget(Context context) {
        AppWidgetManager manager= AppWidgetManager.getInstance(context);
        int [] ids=manager.getAppWidgetIds(new ComponentName(context,NewAppWidget2.class));
        ArrayList<Model> models= MainActivity.ingredients;
        NewAppWidget2.updateWidget(context,manager,ids,models,position);

    }

}

