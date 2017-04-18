package com.livetyping.core;


import android.app.Application;
import android.content.Context;

public class CoreApplication extends Application {

    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CoreApplication.appContext = getApplicationContext();
    }
}
