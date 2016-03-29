package com.waggieetales.androidapp.playfootball.config;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppConfig extends Application {
    public  static RequestQueue requestQueue;
    public static AppConfig instance ;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        requestQueue = Volley.newRequestQueue(instance.getApplicationContext());
    }

    public static AppConfig getInstance(){
        return instance;
    }


}
