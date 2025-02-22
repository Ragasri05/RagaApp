package com.example.foodorderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;


public class GetMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    String Oid, DatabaseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_menu);
        recyclerView = findViewById(R.id.recyclerView); // Ensure this ID matches your XML layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set layout manager
        Oid = getIntent().getStringExtra("ownerId");
        DatabaseName = "foodDataBase"+Oid+".db";
        getTheMenu();
    }


    public void getTheMenu(){
        //Instantiating the Room database.
        // Room.databaseBuilder returns the instance of FoodDatabase.
        // build() creates the instance of the database.
        FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class,DatabaseName).allowMainThreadQueries().build();
        // Access the FoodItemsDao from the FoodDatabase instance
        FoodItemsDao foodItemsDao = fdb.foodItemsDao();
        //calling the method
        List<FoodItemsEntity> foodItemsEntity = foodItemsDao.getTheMenu();

        //initialises adapter.
        Adapter adapter = new Adapter(foodItemsEntity);
        //The RecyclerView will call the Adapter methods to manage and display its items.
        recyclerView.setAdapter(adapter);
    }
}