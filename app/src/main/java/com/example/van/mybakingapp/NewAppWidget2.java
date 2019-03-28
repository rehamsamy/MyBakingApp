package com.example.van.mybakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.van.mybakingapp.ingredient.Model;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget2 extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId,ArrayList<Model> ingredients,int position) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
//        StringBuilder builder=new StringBuilder();
//        Model model=DetailAdapter.ingredients.get(DetailAdapter.position);
//
//       // for(Model x: MainActivity.ingredients){
//        builder.append("-"+model.getmIngredient()+"("+model.getmQuantity()+":"+model.getmMeasure()+")"+"\n");
//        //}
        //quantity.setText(builder);


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget2);
       //ArrayList<Model> list= MainActivity.ingredients;

       if(ingredients==null){
           views.setTextViewText(R.id.appwidget_text, "no data found");
          // Log.v("NewAppWidget2","innnnnnnnn"+MainActivity.ingredients.size());


       }else {

           Model model=ingredients.get(position);
           Log.v("NewAppWidget2","pppppppp"+position);
           String xx= model.getmIngredient();
           StringBuilder builder=new StringBuilder();
            for(Model x: MainActivity.ingredients) {
                builder.append("-" + x.getmIngredient() + "(" + x.getmQuantity() + ":" + x.getmMeasure() + ")" + "\n");
            }
             views.setTextViewText(R.id.appwidget_text, builder);

            Intent intent=new Intent(context,RecipeDetail.class);
            intent.putExtra("position",position);
           PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);
           views.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent);



       }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent intent=new Intent(context,WidgetServiceIntent.class);
        context.startService(intent);
        // There may be multiple widgets active, so update all of them

    }
    public static  void updateWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds,ArrayList<Model> models,int position){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,models,position);
            Log.v("NewAppWidget2","position update is"+position);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("NewAppWidget2", "in on recieve  " + intent.getAction());
        if (intent != null) {
            if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(intent.getAction())) {

                Log.e("NewAppWidget2", "in on update  ");
                Intent intent1=new Intent(context,WidgetServiceIntent.class);
                context.startService(intent1);
                Log.e("NewAppWidget2", "in on recieve  " + intent.getAction());
            }
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
}

