package com.dds.loftcoins.domain.coins.dtc;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICmcApi {
    String API_KEY = "X-CMC_PRO_API_KEY";

    @GET("cryptocurrency/listings/latest")
    Call<CoinsListingDTC> GetCoinsListings(@Query("convert") String convert);
}
