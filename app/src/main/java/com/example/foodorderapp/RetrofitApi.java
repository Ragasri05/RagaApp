package com.example.foodorderapp;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// interface will define methods that we want to call to interact with the server.
public interface RetrofitApi {
    // ===2===
    @GET("databases/{dbName}") // "/" refers to the root part of the Url
    Call<JsonArray> getDatabaseData(@Path("dbName") String databaseName);
}
