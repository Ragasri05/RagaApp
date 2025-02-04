package com.example.foodorderapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {
    // https://run.mocky.io/v3/  8fc15918-82a6-465a-97e0-a455f682126b
    @GET("8fc15918-82a6-465a-97e0-a455f682126b")
    Call<PojoClass> useRetrofit();
}
