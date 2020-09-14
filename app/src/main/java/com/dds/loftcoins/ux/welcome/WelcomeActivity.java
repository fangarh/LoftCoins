package com.dds.loftcoins.ux.welcome;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.dds.loftcoins.databinding.ActivityWelcomeBinding;
import com.dds.loftcoins.ux.main.MainActivity;
import com.dds.loftcoins.ux.widgets.CircleIndicator;

public class WelcomeActivity extends AppCompatActivity {
    public static final String KEY_SHOW_WELCOME = "SHOW_WELCOME";
    private ActivityWelcomeBinding binding;
    private SnapHelper snapHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitBinder();
        InitListeners();
    }

    @Override
    protected void onDestroy() {
        snapHelper.attachToRecyclerView(null);
        binding.recycler.setAdapter(null);
        super.onDestroy();
    }

    private void InitBinder(){
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recycler.setAdapter(new WelcomeSliderAdapter());
        binding.recycler.addItemDecoration(new CircleIndicator(this));
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recycler);
    }

    private void InitListeners(){
        binding.btnStart.setOnClickListener((event)->{

            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(KEY_SHOW_WELCOME, false).apply();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
