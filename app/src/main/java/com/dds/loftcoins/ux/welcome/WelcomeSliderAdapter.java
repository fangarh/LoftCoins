package com.dds.loftcoins.ux.welcome;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dds.loftcoins.databinding.WelcomePageBinding;
import com.dds.loftcoins.domain.welcome.WelcomePageData;

public class WelcomeSliderAdapter extends RecyclerView.Adapter<WelcomeSliderViewHolder> {
    private LayoutInflater inflater;

    private WelcomePageData [] welcomeData = WelcomePageData.GetDefaultWelcomePageData();

    @NonNull
    @Override
    public WelcomeSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WelcomeSliderViewHolder(WelcomePageBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WelcomeSliderViewHolder holder, int position) {
        holder.getBinding().image.setImageResource(welcomeData[position].Image);
        holder.getBinding().title.setText(welcomeData[position].Title);
        holder.getBinding().subtitle.setText(welcomeData[position].SubTitle);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    @Override
    public int getItemCount() {
        return welcomeData.length;
    }
}
