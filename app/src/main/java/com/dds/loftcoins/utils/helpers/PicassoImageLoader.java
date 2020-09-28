package com.dds.loftcoins.utils.helpers;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class PicassoImageLoader implements IImageLoader {

    private final Picasso picasso;

    @Inject
    PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public IImageRequest load(String url) {
        return new PicassoImageRequest(picasso.load(url));
    }
}
