package com.example.foodorderapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;


public class GetMenu extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_menu);

        getTheMenu();
    }

    public void getTheMenu(){
        FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class,"my_food_database").allowMainThreadQueries().build();
        FoodItemsDao foodItemsDao = fdb.foodItemsDao();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<FoodItemsEntity> foodItemsEntity = foodItemsDao.getTheMenu();// datasource.
        Adapter adapter = new Adapter(foodItemsEntity);
        recyclerView.setAdapter(adapter);
    }
}