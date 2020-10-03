package com.dds.loftcoins.ux.converter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dds.loftcoins.BuildConfig;
import com.dds.loftcoins.databinding.LiCoinSheetBinding;
import com.dds.loftcoins.domain.coins.ICoin;
import com.dds.loftcoins.utils.IImageLoader;
import com.dds.loftcoins.utils.OutlineCircle;

import java.util.Objects;

import javax.inject.Inject;

class CoinsSheetAdapter extends ListAdapter<ICoin, CoinsSheetAdapter.ViewHolder> {

    private final IImageLoader imageLoader;
    private LayoutInflater inflater;

    @Inject
    CoinsSheetAdapter(IImageLoader imageLoader) {
        super(new DiffUtil.ItemCallback<ICoin>() {
            @Override
            public boolean areItemsTheSame(@NonNull ICoin oldItem, @NonNull ICoin newItem) {
                return oldItem.id() == newItem.id();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ICoin oldItem, @NonNull ICoin newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
        this.imageLoader = imageLoader;
    }

    @Override
    public ICoin getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiCoinSheetBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ICoin coin = getItem(position);
        holder.binding.name.setText(coin.symbol() + " | " + coin.name());
        imageLoader
                .load(BuildConfig.IMG_ENDPOINT + coin.id() + ".png")
                .into(holder.binding.logo);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final LiCoinSheetBinding binding;

        ViewHolder(@NonNull LiCoinSheetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            OutlineCircle.apply(binding.logo);
        }
    }
}
