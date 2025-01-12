package com.example.foodorderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class GetMenu extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_menu);
        recyclerView = findViewById(R.id.recyclerView); // Ensure this ID matches your XML layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager

        getTheMenu();
    }


    public void getTheMenu(){
            // Using a background thread for Room database operation
        FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "my_food_database").allowMainThreadQueries().build();
        FoodItemsDao foodItemsDao = fdb.foodItemsDao();
        List<FoodItemsEntity> foodItemsEntity = foodItemsDao.getTheMenu().getValue();
        Adapter adapter = new Adapter(foodItemsEntity);
        recyclerView.setAdapter(adapter);
    }
}