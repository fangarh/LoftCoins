package com.dds.loftcoins.ux.wallets;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WalletsCarouselScroller extends RecyclerView.OnScrollListener  {
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        final int centerX = (recyclerView.getLeft() + recyclerView.getRight()) / 2;
        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
            final View child = recyclerView.getChildAt(i);
            final int childCenterX = (child.getLeft() + child.getRight()) / 2;
            final float childOffset = Math.abs(centerX - childCenterX) / (float) centerX;
            float factor = (float) (Math.pow(0.85, childOffset));
            child.setScaleX(factor);
            child.setScaleY(factor);
        }
    }
}
