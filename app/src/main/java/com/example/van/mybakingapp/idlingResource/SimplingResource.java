package com.example.van.mybakingapp.idlingResource;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimplingResource implements IdlingResource {
    ResourceCallback mResource;
    AtomicBoolean msetIdle=new AtomicBoolean(true);
    @Override
    public String getName() {
        return SimplingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {

        return  msetIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResource=callback;

    }

    public void setIdleState(boolean isIdle){
       msetIdle.set(isIdle);
       if(isIdle&&mResource!=null){

           mResource.onTransitionToIdle();
       }


    }


}
