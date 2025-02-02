/*package com.example.foodorderapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerMenu extends AppCompatActivity {
    JsonHttpServer jsonHttpServer;
    RecyclerView recyclerView;

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
        /*
        // ===3===
        String selectedDatabasePath = getIntent().getStringExtra("selectedDatabaseName");
        if (selectedDatabasePath == null || selectedDatabasePath.isEmpty()) {
            Toast.makeText(this, "Database path is missing", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new finalMenuAdapter(new ArrayList<>()));

        // Get the JSON file path
        File jsonFile = new File(selectedDatabasePath); // Replace "example.json" with your actual JSON file name.

        // Check if the JSON file exists
        if (!jsonFile.exists()) {
            Toast.makeText(this, "JSON file not found!", Toast.LENGTH_SHORT).show();
        }else{
        try {
                jsonHttpServer = new JsonHttpServer(9090, jsonFile); // Use port 8080 or any available port.
                jsonHttpServer.start();
                Toast.makeText(this, "Server started on port 8080", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to start server", Toast.LENGTH_SHORT).show();
            }

            String url = "http://10.0.2.2:9090";
            fetchDataUsingRetrofit(url, selectedDatabasePath);

        }
    }

    protected void fetchDataUsingRetrofit(String url, String databaseName){
        // Retrofit Builder.
        // Retrofit.Builder is used to configure the Retrofit Instance
        Retrofit retrofit = new Retrofit.Builder()
                // tells the retrofit where to send the request.
                .baseUrl(url+ "/") // "/" ensures that the bae url ends with forwanrd slash.
                // tells the Retrofit how to convert the http response to a java object.
                .addConverterFactory(GsonConverterFactory.create()) // will convert using Gson Library
                // Once this call is made retrofit Instance is Ready to use for making network request.
                .build();

                // Implementing the Api interface which is made.
                // using Create method we will be able to implement the Api interface.
                RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

                // calling the method which is defined in RetrofitApi method.
                // @GET will send the Network Request to the server.
                // enqueue is to run the requesst Asynchronously.
                retrofitApi.getDatabaseData(databaseName).enqueue(new Callback<JsonArray>() {

                    // onResponse method is called when the request sucesseds.
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                        if (response.isSuccessful() && response.body() != null) {
                        //if(!response.isSuccessful()){
                            setRecyclerView(response.body().toString());
                            //Toast.makeText(CustomerMenu.this, "success!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CustomerMenu.this, "Failed to fetch Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // this method is called when something goes wrong in the network request.
                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e("Retrofit Error", t.getMessage(), t);
                        Toast.makeText(CustomerMenu.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setRecyclerView(String jsonData){
        List<Map<String, String>> dataList = parseJsonData(jsonData);

        finalMenuAdapter jsonDataAdapter = new finalMenuAdapter(dataList);
        recyclerView.setAdapter(jsonDataAdapter);
        jsonDataAdapter.notifyDataSetChanged();
    }

    private List<Map<String, String>> parseJsonData(String jsonData){
        // all the parsed data will be stored in the dataList.
        List<Map<String, String>> dataList = new ArrayList<>();
        try{
            JsonArray jsonArray = JsonParser.parseString(jsonData).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                Map<String, String> row = new HashMap<>();
                for (String key : jsonObject.keySet()) {
                    row.put(key, jsonObject.get(key).getAsString());
                }
                dataList.add(row);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop the server when the activity is destroyed
        if (jsonHttpServer != null) {
            jsonHttpServer.stop();
            Toast.makeText(this, "Server stopped", Toast.LENGTH_SHORT).show();
        }
    }


    }
}
*/