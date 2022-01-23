package com.application.bton;

import android.app.Application;
import android.util.Log;

import com.backendless.Backendless;

public class DefaultApplication extends Application {
    public static final String APPLICATION_ID = "58DFB477-559A-DB3D-FF3B-19E8039AF500";
    public static final String API_KEY = "0F0B611D-08F5-4614-9E53-D19E9B0720E1";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("server url", SERVER_URL);
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY);
    }
}
