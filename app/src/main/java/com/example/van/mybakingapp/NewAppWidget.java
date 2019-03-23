package com.example.van.mybakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.van.mybakingapp.ingredient.Model;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
       // RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.grid_view_layout);
//        Intent intent=new Intent(context,GridRemoteViewsService.class);
//        views.setRemoteAdapter(R.id.grid_view,intent);
//
//        Intent intent1=new Intent(context,MainActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent1,0);
//        views.setPendingIntentTemplate(R.id.recipe_widget_image,pendingIntent);

        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.new_app_widget);

        ArrayList<Model> arrayList=MainActivity.ingredients;

        Model model=arrayList.get(0);
       String text= model.getmIngredient();
        views.setTextViewText(R.id.recipe_ingredient,text);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

    }
}

