package com.example.foodorderapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodItemsDao {
    @Insert
    public void addFoodItems(FoodItemsEntity foodItemsEntity);

    @Query("SELECT EXISTS(SELECT * FROM FoodItemsEntity WHERE food = :fooditem)")
    Boolean is_exist(String fooditem);

    @Query("SELECT * FROM FoodItemsEntity")
    List<FoodItemsEntity> getTheMenu();

    @Query("UPDATE FoodItemsEntity SET food = :fooditem, price = :price WHERE uid = :uid")
    public void updateById(int uid, String fooditem, Double price);


}
