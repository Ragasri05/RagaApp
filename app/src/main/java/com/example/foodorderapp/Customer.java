package com.example.foodorderapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Customer extends AppCompatActivity {

    //15.Deeplinking.1
    Uri uri;
    RecyclerView recyclerView;
    List<String> databaseList;
    private DatabaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer);


        recyclerView = findViewById(R.id.recyclerView);


        //15.Deeplinking.2
        // https://www.customer.com -Link to open the customer Menu.
        // getting the data from intent into the Uri.
        uri = getIntent().getData();
        //checking if the uri is null or not.
        if (uri != null) {
            // getting the path segments and storing it into the list.
            List<String> parameters = uri.getPathSegments();
            if (parameters != null && !parameters.isEmpty()) {
                // Extract the last segment safely
                String param = parameters.get(parameters.size() - 1);
                Toast.makeText(this, "Parameter: " + param, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No parameters found in the URI.", Toast.LENGTH_SHORT).show();
            }
        }

        // getting all the databases
        databaseList = getAllDatabases();
        //setting the adapter.
        adapter = new DatabaseAdapter(this, recyclerViewInterface, databaseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));// Vertical list
        recyclerView.setAdapter(adapter);

    }


    // this method is used to get the list od databases.
    // this method can be called from anywhere in your app.
    // Context is something that gives your app access to its resources and environment, like databases and files.
    public List<String> getAllDatabases() {
        // File is class which belongs to java.io package. it provides methods to create delete or access files and directories.
        // getDatabasePath("fakePath") returns a File object for /data/user/0/com.example.app/databases/dummy.
        // getParentFile() returns a File object for /data/user/0/com.example.app/databases.
        File databaseDir = new File(getApplicationContext().getFilesDir().getParent(), "databases");
        List<String> databaseList = new ArrayList<>();

        if (databaseDir != null && databaseDir.exists()) {
            // gets all the files and folders in in databseDir and stored in files.
            // files is an array of file objects.
            File[] files = databaseDir.listFiles();
            // Checking if there are no files in the databaseDir.
            if (files != null) {
                // loop through each file of the folder.
                for (File file : files) {
                    // getting the name of the file which ends with .db
                    if (file.getName().endsWith(".db")) {
                        // adds the file in the database list.
                        databaseList.add(file.getName());
                    }
                }
            }
        }
        // returns the databse list.
        return databaseList;
    }



    // All the code which want to execute when the item is clicked must be placed in the onItemClick method.

    RecyclerViewInterface recyclerViewInterface = new RecyclerViewInterface() {
        @Override
        public void onItemClick(int position) {
            Intent intent = new Intent(Customer.this,DatabaseToMenu.class);
            intent.putExtra("databaseName", databaseList.get(position));
            startActivity(intent);

            /*
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                try {
                    File jsonFile = DatabaseToJsonConvertor.convertDatabaseToJson(Customer.this, databaseList.get(position));

                    runOnUiThread(() -> {
                        Intent intent = new Intent(Customer.this, CustomerMenu.class);
                        intent.putExtra("selectedDatabaseName", jsonFile.getAbsolutePath());
                        startActivity(intent);
                        Toast.makeText(Customer.this, "JSON File Created: " + jsonFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        //Log.d("DatabaseToJson", "JSON File Path: " + jsonFile.getAbsolutePath());
                    });

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(Customer.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            });

             */
        }
    };
}
