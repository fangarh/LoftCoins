package com.dds.loftcoins.utils;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class PicassoImageLoader implements IImageLoader {

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

    private static class PicassoImageRequest implements IImageRequest {

        private final RequestCreator request;

        PicassoImageRequest(RequestCreator request) {
            this.request = request;
        }

        @Override
        public void into(@NonNull ImageView view) {
            request.into(view);
        }

    }

}