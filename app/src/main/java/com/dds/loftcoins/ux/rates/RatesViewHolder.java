package com.dds.loftcoins.ux.rates;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dds.loftcoins.databinding.LiRateBinding;
import com.dds.loftcoins.utils.view.OutlineCircle;

public class RatesViewHolder extends RecyclerView.ViewHolder {
    private final LiRateBinding binding;

    public LiRateBinding binding(){
        return binding;
    }

    public RatesViewHolder(@NonNull LiRateBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        OutlineCircle.apply(binding.logo);
    }
}
