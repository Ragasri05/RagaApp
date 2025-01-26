package com.example.foodorderapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Customer extends AppCompatActivity implements RecyclerViewInterface {

    //15.Deeplinking.1
    Uri uri;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer);


        recyclerView = findViewById(R.id.recyclerView);
        // Set a LayoutManager for the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Vertical list


        //15.Deeplinking.2
        // https://www.customer.com -Link to open the customer Menu.
        // getting the data from intent into the Uri.
        uri = getIntent().getData();
        //checking if the uri is null or not.
        if (uri != null){
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
        List<String> databaseList = getAllDatabases(this);
        //setting the adapter.
        DatabaseAdapter adapter = new DatabaseAdapter(this, databaseList);
        recyclerView.setAdapter(adapter);

    }

    // this method is used to get the list od databases.
    // this method can be called from anywhere in your app.
    // Context is something that gives your app access to its resources and environment, like databases and files.
    public List<String> getAllDatabases(Context context){
        // File is class which belogs to java.io package. it provides methods to create delete or access files and directories.
        // getDatabasePath("fakePath") returns a File object for /data/user/0/com.example.app/databases/dummy.
        // getParentFile() returns a File object for /data/user/0/com.example.app/databases.
        File databaseDir = context.getDatabasePath("fakePath").getParentFile();
        if (databaseDir != null && databaseDir.isDirectory()){
            // Storing all the files in the database directory as an array of String.
            String[] files = databaseDir.list();
            // Creating an empty list to all the databases.
            List<String> DatabaseList = new ArrayList<>();
            // Checking if there are no files in the databaseDir.
            if (files != null){
                // loop through each file of the folder.
                for (String file : files){
                    if (file.endsWith("shm")) {
                        // adding the database to the list.
                        DatabaseList.add(file);
                    }
                }
            }
            return DatabaseList;
        }
        // if DatabseDir doesn't exist, it will return a new Directory.
        return new ArrayList<>();
    }
    // All the code which want to execute when the item is clicked must be placed in the onItemClick method.
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Customer.this, CustomerMenu.class);
        startActivity(intent);
    }
}