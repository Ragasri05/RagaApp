package com.example.foodorderapp;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

// interface will define methods that we want to call to interact with the server.
public interface RetrofitApi {
    @GET("/") // "/" refers to the root part of the Url
    Call<JsonArray> getDatabaseData();
}
