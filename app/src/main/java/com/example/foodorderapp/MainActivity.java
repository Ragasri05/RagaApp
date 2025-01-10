package com.example.foodorderapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {





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


        // when save button is clicked and if the food item doesn't exixst in the databse, then addFoodItems Method is called.
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(),
                        FoodDatabase.class, "my_food_database").allowMainThreadQueries().build();

                FoodItemsDao foodItemsDao = fdb.foodItemsDao();
                Boolean checkIfFoodExists = foodItemsDao.is_exist(food.getText().toString()); // if food item exists it returns True else False.
                if (!checkIfFoodExists){
                    foodItemsDao.addFoodItems(new FoodItemsEntity (food.getText().toString(), Double.parseDouble(price.getText().toString())));
                    food.setText("");
                    price.setText("");
                    Toast.makeText(MainActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                }
                else{
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
    }
    // this method is called when the user clicks the logout button.
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginScreen.class));
        finish();
    }

    MyReceiver myReceiver = new MyReceiver();

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(myReceiver);
    }
}