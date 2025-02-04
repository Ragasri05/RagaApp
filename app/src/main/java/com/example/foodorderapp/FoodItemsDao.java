package com.example.foodorderapp;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
// Dao provides methods for the rest of the app to interact with the Table.

@Dao
public interface FoodItemsDao {

    @Insert
    void addFoodItems(FoodItemsEntity foodItemsEntity);

    // to check if a particular fooditem exists in the database table
    //SELECT EXISTS if a sub query returns any rows (it returns a boolean true or False).
    //SELECT * FROM FoodItemsEntity selects all columns from the table.
    // WHERE food = :fooditem --> condition.
    @Query("SELECT EXISTS(SELECT * FROM FoodItemsEntity WHERE food = :fooditem)")
    Boolean is_exist(String fooditem);

    //SELECT * FROM FoodItemsEntity selects all columns from the table
    @Query("SELECT * FROM FoodItemsEntity")
    List<FoodItemsEntity> getTheMenu();

    //SET --> specifies the table  where the changes will be made.
    //WHERE food = :fooditem --> condition.
    @Query("UPDATE FoodItemsEntity SET food = :fooditem, price = :price WHERE uid = :uid")
    void update(int uid, String fooditem, Double price);

    // Deletes rows that match the given condition.
    @Query("DELETE FROM FoodItemsEntity WHERE food = :fooditem")
    void deletebyitem(String fooditem);


}
