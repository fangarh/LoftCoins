package com.dds.loftcoins.utils.helpers;

import androidx.annotation.NonNull;

public interface IImageLoader {
    @NonNull
    IImageRequest load(String url);
}
