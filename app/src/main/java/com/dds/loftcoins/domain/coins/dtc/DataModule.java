package com.dds.loftcoins.domain.coins.dtc;

import android.content.Context;

import androidx.room.Room;

import com.dds.loftcoins.BuildConfig;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class DataModule {

    @Provides
    static Moshi moshi() {
        final Moshi moshi = new Moshi.Builder().build();
        return moshi.newBuilder()
                .add(ICoin.class, moshi.adapter(AutoValue_CmcCoin.class))
                .add(CoinsListingDTC.class, moshi.adapter(AutoValue_CoinsListingDTC.class))
                .build();
    }

    @Provides
    static Retrofit retrofit(OkHttpClient httpClient, Moshi moshi) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(httpClient.newBuilder()
                .addInterceptor(chain -> {
                    final Request request = chain.request();
                    return chain.proceed(request.newBuilder()
                            .addHeader(ICmcApi.API_KEY, BuildConfig.API_KEY)
                            .build());
                })
                .build());
        builder.baseUrl(BuildConfig.API_ENDPOINT);
        builder.addConverterFactory(MoshiConverterFactory.create(moshi));
        return builder.build();
    }

    @Provides
    static ICmcApi cmcApi(Retrofit retrofit) {
        return retrofit.create(ICmcApi.class);
    }

    @Provides
    @Singleton
    static DatabaseAdapter loftDatabase(Context context) {
        if (BuildConfig.DEBUG) {
            return Room.inMemoryDatabaseBuilder(context, DatabaseAdapter.class).build();
        } else {
            return Room.databaseBuilder(context, DatabaseAdapter.class, "loft.db").build();
        }
    }

    @Binds
    abstract ICoinsRepository coinsRepo(CmcCoinsRepository impl);

    @Binds
    abstract ICurrencyRepository currencyRepo(CurrencyRepository impl);

}
