package com.example.foodorderapp;

import android.app.Application;

import androidx.room.Database;
import androidx.room.RoomDatabase;


// @Database connects all the Dao's to the database.
//foodItemsDao(), Room will generate the necessary code to interact with the FoodItemsEntity table using the defined DAO methods.
@Database(entities = {FoodItemsEntity.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {    //FoodDatabase acts as a control centre
    public abstract FoodItemsDao foodItemsDao();         // this methods allows us to use FoodItemsDao to interact with the FoodItemsEntity table.
}
