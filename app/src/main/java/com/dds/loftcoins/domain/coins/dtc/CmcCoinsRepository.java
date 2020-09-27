package com.dds.loftcoins.domain.coins.dtc;

import androidx.annotation.NonNull;

import com.dds.loftcoins.BuildConfig;
import com.dds.loftcoins.utils.faces.ICoinsRepository;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CmcCoinsRepository implements ICoinsRepository {
    private final ICmcApi api;

    public CmcCoinsRepository() {
        api = retrofitPrebuild(createHttpClient()).create(ICmcApi.class);
    }

    @NonNull
    @Override
    public List<? extends Coin> GetCoinsRates(@NonNull String currency) throws IOException {
        final Response<CoinsListingDTC> response = api.GetCoinsListings(currency).execute();
        if (response.isSuccessful()) {
            final CoinsListingDTC listings = response.body();
            if (listings != null) {
                return listings.data();
            }
        } else {
            final ResponseBody responseBody = response.errorBody();
            if (responseBody != null) {
                throw new IOException(responseBody.string());
            }
        }
        return Collections.emptyList();
    }

    private OkHttpClient createHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            final Request request = chain.request();
            return chain.proceed(request.newBuilder()
                    .addHeader(ICmcApi.API_KEY, BuildConfig.API_KEY)
                    .build());
        });
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            interceptor.redactHeader(ICmcApi.API_KEY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }
    private Retrofit retrofitPrebuild(OkHttpClient httpClient){
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(httpClient);
        builder.baseUrl(BuildConfig.API_ENDPOINT);
        final Moshi moshi = new Moshi.Builder().build();

        builder.baseUrl(BuildConfig.API_ENDPOINT);
        builder.addConverterFactory(MoshiConverterFactory.create(
                moshi.newBuilder()
                .add(Coin.class, moshi.adapter(AutoValue_Coin.class))
                .add(CoinsListingDTC.class, moshi.adapter(AutoValue_CoinsListingDTC.class))
                .build()
        ));
        return builder.build();
    }
}
