package com.example.foodorderapp;

import android.view.Menu;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// entity class represents able in DataBase.
@Entity (tableName = "FoodItemsEntity")
public class FoodItemsEntity {
    // an entity must have a primary key.
    // primary key Ensures that each record in the table have a unique identity.
    // automatically generates the value for this field.
    @PrimaryKey (autoGenerate = true)
    public int uid;
    // column in the database table with name food.
    @ColumnInfo (name = "food")
    public String food;
    //column in the databse table with name price.
    @ColumnInfo (name = "price")
    public Double price;


    public FoodItemsEntity(String food, Double price) {
        this.uid = uid;
        this.food = food;
        this.price = price;
    }

    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}

