package com.example.emptyness;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterrface {
    @GET("times/today.json")
    public Call<Example>getdata(
            @Query("city")String city

    );
}
