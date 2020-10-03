package com.dds.loftcoins;

import android.app.Application;
import android.os.StrictMode;

import com.google.firebase.iid.FirebaseInstanceId;

import timber.log.Timber;

public class LoftCoins extends Application {
    private BaseComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            StrictMode.enableDefaults();
        }
        component = DaggerAppComponent.builder()
                .application(this)
                .build();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            Timber.d("fcm: %s", instanceIdResult.getToken());
        });
    }

    public BaseComponent getComponent() {
        return component;
    }
}
