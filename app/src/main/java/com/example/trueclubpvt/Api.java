package com.example.trueclubpvt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("/")
    Call<CountryModel> getCountryGuess(@Query("name") String name);
}
