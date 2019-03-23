package com.example.van.mybakingapp.idlingResource;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleIdlingresource implements IdlingResource {

    ResourceCallback mcallback;
    AtomicBoolean mIdleNow=new AtomicBoolean(true);


    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        callback=mcallback;

    }


    public void setIdleState(boolean isIdleNow) {
        mIdleNow.set(isIdleNow);
        if (isIdleNow && mcallback != null) {
           mcallback.onTransitionToIdle();
        }
    }
}
