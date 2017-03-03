package com.aqiu.rxzone.application;

import android.app.Application;
import android.content.Context;

/**
 * 初始化application
 * Created by a on 2016/5/6.
 */
public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getObjectContext() {
        return context;
    }
}
