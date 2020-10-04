package com.dds.loftcoins.ux.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.intercepting.SingleActivityFactory;

import com.dds.loftcoins.ux.main.MainActivity;
import com.dds.loftcoins.ux.welcome.WelcomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {
    private SharedPreferences pref;
    private TestIdling idling;


    @Rule
    public final ActivityTestRule<SplashActivity> rule = new ActivityTestRule<>(
        new SingleActivityFactory<SplashActivity>(SplashActivity.class) {
            @Override
            protected SplashActivity create(Intent intent) {
                final SplashActivity activity = new SplashActivity();
                activity.idling = idling;
                return activity;
            }
        }, false, false);

    @Before
    public void setUp() throws Exception {
        final Context context = ApplicationProvider.getApplicationContext();
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        idling = new TestIdling();
        IdlingRegistry.getInstance().register(idling.res);
    }


    @Test
    public void OpenWelcomeActivityFirstRun() throws InterruptedException {
        pref.edit().putBoolean(WelcomeActivity.KEY_SHOW_WELCOME, true).apply();

        rule.launchActivity(new Intent());

        Intents.init();

        Intents.intended(IntentMatchers.hasComponent(WelcomeActivity.class.getName()));
    }

    @Test
    public void OpenWelcomeActivityAlreadyRuned() throws InterruptedException {
        pref.edit().putBoolean(WelcomeActivity.KEY_SHOW_WELCOME, false).apply();

        rule.launchActivity(new Intent());

        Intents.init();

        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(idling.res);
        Intents.release();
    }

    private static class TestIdling implements ISplashIdling{
        CountingIdlingResource res = new CountingIdlingResource("splash");
        @Override
        public void busy() {
            res.increment();
        }

        @Override
        public void idle() {
            res.decrement();
        }
    }
}