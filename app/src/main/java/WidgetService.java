import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.van.mybakingapp.MainActivity;
import com.example.van.mybakingapp.NewAppWidget2;
import com.example.van.mybakingapp.ingredient.Model;

import java.util.ArrayList;

public  class WidgetService extends IntentService {


    public WidgetService() {
        super("WidgetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        updateWidget(getApplicationContext());

    }

    private void updateWidget(Context context) {
        AppWidgetManager manager= AppWidgetManager.getInstance(context);
        int [] ids=manager.getAppWidgetIds(new ComponentName(context,NewAppWidget2.class));
        ArrayList<Model> models= MainActivity.ingredients;
        int postiom=MainActivity.position;
        NewAppWidget2.updateWidget(context,manager,ids,models,postiom);

    }

}
