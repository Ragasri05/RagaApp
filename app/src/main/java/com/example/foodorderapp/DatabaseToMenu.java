package com.example.foodorderapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class DatabaseToMenu extends AppCompatActivity {

    private RecyclerView recyclerView;
    private finalMenuAdapter finalMenuAdapter;
    List<FoodItemsEntity> foodItemsEntities = new ArrayList<>();
    private String selectedDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_database_to_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);

        selectedDatabase = getIntent().getStringExtra("databaseName");
        new Thread(() -> {
            FoodDatabase database = Room.databaseBuilder(this, FoodDatabase.class,selectedDatabase).build();
            foodItemsEntities = database.foodItemsDao().getTheMenu();

            runOnUiThread(() -> {
                finalMenuAdapter = new finalMenuAdapter(foodItemsEntities);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(finalMenuAdapter);
            });
        }).start();

    }
}