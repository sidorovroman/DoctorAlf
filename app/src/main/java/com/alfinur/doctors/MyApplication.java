package com.alfinur.doctors;


import android.app.Application;
import android.content.Context;
import android.os.Environment;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        MyApplication.context = getApplicationContext();

    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}