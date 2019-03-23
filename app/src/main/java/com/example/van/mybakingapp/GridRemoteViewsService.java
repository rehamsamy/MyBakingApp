package com.example.van.mybakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class GridRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViews(this.getApplicationContext());
    }


}
