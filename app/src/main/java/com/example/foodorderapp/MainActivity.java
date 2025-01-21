package com.example.foodorderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.room.Room;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Receiver receiver = new Receiver();

    Button logoutButton, savebutton, fetchButton;
    EditText food, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        logoutButton = findViewById(R.id.logoutButton);
        savebutton = findViewById(R.id.savebutton);
        fetchButton = findViewById(R.id.fetchbutton);
        food = findViewById(R.id.food);
        price = findViewById(R.id.price);

        // when save button is clicked and if the food item doesn't exist in the database, then addFoodItems Method is called.
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiating the Room database.
                // Room.databaseBuilder returns the instance of FoodDatabase.
                // build() creates the instance of the database.
                FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(),
                        FoodDatabase.class, "my_food_database").allowMainThreadQueries().build();

                // Access the FoodItemsDao from the FoodDatabase instance
                FoodItemsDao foodItemsDao = fdb.foodItemsDao();
                // is_exist will return true or false.
                Boolean checkIfFoodExists = foodItemsDao.is_exist(food.getText().toString()); // if food item exists it returns True else False.
                if (!checkIfFoodExists) {
                    foodItemsDao.addFoodItems(new FoodItemsEntity(food.getText().toString(), Double.parseDouble(price.getText().toString())));
                    food.setText("");
                    price.setText("");
                    Toast.makeText(MainActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                } else { // if is_exist is true.
                    food.setText("");
                    price.setText("");
                    Toast.makeText(MainActivity.this, "Food already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //goes to Getmenu.java when fetch button is clicked.
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GetMenu.class));
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver,intentFilter);

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}