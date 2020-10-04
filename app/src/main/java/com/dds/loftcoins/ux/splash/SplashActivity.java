package com.dds.loftcoins.ux.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.dds.loftcoins.R;
import com.dds.loftcoins.ux.main.MainActivity;
import com.dds.loftcoins.ux.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {
    private final Handler handler = new Handler();

    private Runnable goNext;

    private SharedPreferences preferences;

    @VisibleForTesting
    ISplashIdling idling = new NoopIdling();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(preferences.getBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true)){
            goNext = () -> {
                startActivity(new Intent(this, WelcomeActivity.class));
                idling.idle();
            };
        }else{
            goNext = () -> {
                startActivity(new Intent(this, MainActivity.class));
                idling.idle();
            };
        }

        handler.postDelayed(goNext, 2000);
        idling.busy();
    }

    @Override
    protected void onStop() {
        handler.removeCallbacks(goNext);
        super.onStop();
    }

    private static class NoopIdling implements ISplashIdling{
        @Override
        public void busy() {

        }

        @Override
        public void idle() {

        }
    }
}
