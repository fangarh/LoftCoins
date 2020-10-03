package com.dds.loftcoins.domain.coins;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ICmcApi {
    String API_KEY = "X-CMC_PRO_API_KEY";
    @GET("cryptocurrency/listings/latest")
    Observable<Listing> listing(@Query("convert") String convert);
}
