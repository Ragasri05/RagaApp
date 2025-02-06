package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// http-client for android.
// used for communicating with a server by making Api calls.
public class SampleRetrofit extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sample_retrofit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Creating an instanceof the interface.
        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<PojoClass> call = apiCall.useRetrofit();

        call.enqueue(new Callback<PojoClass>() {
            @Override
            public void onResponse(Call<PojoClass> call, Response<PojoClass> response) {
                if (response.isSuccessful()){
                    textView.setText(response.body().getWelcome());

                    String url = response.body().getImgLink();
                    Picasso.get().load(url).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<PojoClass> call, Throwable t) {
                Toast.makeText(SampleRetrofit.this, "Failed to implement Retrofit", Toast.LENGTH_SHORT).show();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SampleRetrofit.this,LoginScreen.class));
            }
        });
    }
}