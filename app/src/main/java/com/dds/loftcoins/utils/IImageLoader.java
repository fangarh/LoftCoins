package com.dds.loftcoins.utils;

import android.widget.ImageView;

import androidx.annotation.NonNull;



public interface IImageLoader {
    @NonNull
    IImageRequest load(String url);
    
    interface IImageRequest {
        void into(@NonNull ImageView view);
    }
}
