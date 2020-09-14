package com.dds.loftcoins;

import android.app.Application;
import android.os.StrictMode;

public class LoftCoins extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
        }
    }
}
