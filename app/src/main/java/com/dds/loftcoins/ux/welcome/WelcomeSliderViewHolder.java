package com.dds.loftcoins.ux.welcome;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dds.loftcoins.databinding.WelcomePageBinding;

public class WelcomeSliderViewHolder extends RecyclerView.ViewHolder {
    private WelcomePageBinding binding;

    public WelcomeSliderViewHolder(@NonNull WelcomePageBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public WelcomePageBinding getBinding() {
        return binding;
    }
}
