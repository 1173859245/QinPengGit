package com.qp.inst_life.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class MyApplication extends Application {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
//        app=getApplicationContext();
//        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }
}
