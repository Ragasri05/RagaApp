package com.example.foodorderapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonArray;

import org.jetbrains.annotations.ApiStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fetchDataUsingRetrofit(url);
    }

    protected void fetchDataUsingRetrofit(String url){
        // Retrofit Builder.
        // Retrofit.Builder is used to configure the Retrofit Instance
        Retrofit retrofit = new Retrofit.Builder()
                // tells the retrofit where to send the request.
                .baseUrl(url+"/") // "/" ensures that the bae url ends with forwanrd slash.
                // tells the Retrofit how to convert the http response to a java object.
                .addConverterFactory(GsonConverterFactory.create()) // will convert using Gson Library
                // Once this call is made retrofit Instance is Ready to use for making network request.
                .build();

                // Implementing the Api interface which is made.
                // using Create method we will be able to implement the Api interface.
                RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

                // calleing the method which is defined in RetrofitApi method.
                // @GET will send the Network Request to the server.
                // enqueue is to run the requesst Asynchronously.
                retrofitApi.getDatabaseData().enqueue(new Callback<JsonArray>() {

                    // onResponse method is called when the request sucesseds.
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        if (response.isSuccessful() && response.body() != null){
                            Toast.makeText(CustomerMenu.this, "Successfully Implemented", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CustomerMenu.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // this method is called when something goes wrong in the metwrk request.
                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Toast.makeText(CustomerMenu.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}