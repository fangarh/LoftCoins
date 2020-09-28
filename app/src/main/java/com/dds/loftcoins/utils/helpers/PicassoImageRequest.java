package com.dds.loftcoins.utils.helpers;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.squareup.picasso.RequestCreator;

public class PicassoImageRequest  implements IImageRequest {

    private final RequestCreator request;

    public PicassoImageRequest(RequestCreator request) {
        this.request = request;
    }

    @Override
    public void into(@NonNull ImageView view) {
        request.into(view);
    }

}