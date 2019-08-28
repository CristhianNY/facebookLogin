package com.cristhianbonilla.com.vivikey.core;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.cristhianbonilla.com.vivikey.BuildConfig;
import com.cristhianbonilla.com.vivikey.core.di.Components;
import com.cristhianbonilla.com.vivikey.core.di.DaggerComponents;
import com.cristhianbonilla.com.vivikey.core.di.Modules;

import timber.log.Timber;

public class VivikeyApp  extends Application {
    private Components components;

    public static Components getControllerComponent(Context context) {
     Components component = ((VivikeyApp) context.getApplicationContext()).getComponents();
        return component;
    }

    @Override public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        components();
    }

    @Override protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
         MultiDex.install(this);
    }


    void components() {
        if (components == null) {
           components = DaggerComponents.builder().modules(new Modules(this)).build();
        }
    }

    public Components getComponents() {
        return components;
    }
}



