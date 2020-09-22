package com.dds.loftcoins.ux.wallets;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dds.loftcoins.databinding.LiWalletBinding;


class WalletsSliderAdapter extends RecyclerView.Adapter<WalletsSliderViewHolder> {

    private LayoutInflater inflater;

    @Override
    public int getItemCount() {
        return 10;
    }

    @NonNull
    @Override
    public WalletsSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WalletsSliderViewHolder(LiWalletBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WalletsSliderViewHolder holder, int position) {

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }



}