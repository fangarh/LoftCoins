package com.dds.loftcoins.ux.welcome;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.dds.loftcoins.R;
import com.dds.loftcoins.ux.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class WelcomeActivityTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void OpenMainIfWelcomeButtonPressed() {
        ActivityScenario.launch(WelcomeActivity.class);
        Intents.init();
        onView(withId(R.id.btn_start)).perform(click());
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}