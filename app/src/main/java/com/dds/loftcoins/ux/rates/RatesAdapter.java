package com.dds.loftcoins.ux.rates;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dds.loftcoins.BuildConfig;
import com.dds.loftcoins.R;
import com.dds.loftcoins.databinding.LiRateBinding;
import com.dds.loftcoins.domain.coins.dtc.Coin;
import com.dds.loftcoins.utils.Formatter;
import com.squareup.picasso.Picasso;

import java.util.Locale;
import java.util.Objects;

public class RatesAdapter extends ListAdapter<Coin, RatesViewHolder> {
    private final Formatter<Double> priceFormatter;

    private LayoutInflater inflater;

    private int negative = Color.RED;

    private int positive = Color.GREEN;

    RatesAdapter(Formatter<Double> priceFormatter) {
        super(new DiffUtil.ItemCallback<Coin>() {
            @Override
            public boolean areItemsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return oldItem.id() == newItem.id();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
        this.priceFormatter = priceFormatter;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id();
    }

    @NonNull
    @Override
    public RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RatesViewHolder(LiRateBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position) {
        final Coin coin = getItem(position);
        holder.binding().symbol.setText(coin.symbol());
        holder.binding().price.setText(priceFormatter.format(coin.price()));
        holder.binding().change.setText(String.format(Locale.US, "%.2f%%", coin.change24h()));
        if (coin.change24h() > 0) {
            holder.binding().change.setTextColor(positive);
        } else {
            holder.binding().change.setTextColor(negative);
        }
        Picasso.get()
                .load(BuildConfig.IMG_ENDPOINT + coin.id() + ".png")
                .into(holder.binding().logo);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final Context context = recyclerView.getContext();
        inflater = LayoutInflater.from(context);
        TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.textNegative, v, true);
        negative = v.data;
        context.getTheme().resolveAttribute(R.attr.textPositive, v, true);
        positive = v.data;
    }
}
