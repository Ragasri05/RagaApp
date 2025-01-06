package com.example.foodorderapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodItemsEntity {
    @PrimaryKey (autoGenerate = true)
    public int uid;
    @ColumnInfo (name = "food")
    public String food;
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
