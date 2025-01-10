package com.example.foodorderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class GetMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    private ActivityResultLauncher<Intent> updateFoodLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_menu);


        recyclerView = findViewById(R.id.recyclerView); // Ensure this ID matches your XML layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager

        //4.
        // Initialising the Launcher.
        //3.
        updateFoodLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {

                            int id = data.getIntExtra("updatedId", -1);
                            String updatedFood = data.getStringExtra("updatedFood");
                            Double updatedPrice = data.getDoubleExtra("updatedPrice", 0.0);

                            FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class,"my_food_database").allowMainThreadQueries().build();
                            FoodItemsDao foodItemsDao = fdb.foodItemsDao();
                            foodItemsDao.update(id,updatedFood,updatedPrice);
                        }
                    }
                });
        //5.
        getTheMenu();
    }

    public void getTheMenu(){
        FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class,"my_food_database").allowMainThreadQueries().build();
        FoodItemsDao foodItemsDao = fdb.foodItemsDao();

        List<FoodItemsEntity> foodItemsEntity = foodItemsDao.getTheMenu();// datasource.
        Adapter adapter = new Adapter(foodItemsEntity,updateFoodLauncher);
        recyclerView.setAdapter(adapter);

    }

}