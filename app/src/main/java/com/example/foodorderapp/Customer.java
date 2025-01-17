package com.example.foodorderapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class Customer extends AppCompatActivity {


    TextView Text;
    Button getMenuButton;
    //15.Deeplinking.1
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer);

        Text = findViewById(R.id.CPrice);
        getMenuButton = findViewById(R.id.getMenuButton);

        //15.Deeplinking.2
        // https://www.customer.com -Link to open the customer Menu.
        // getting the data from intent into the Uri.
        uri = getIntent().getData();
        //checking if the uri is null or not.
        if (uri != null){
            // getting the path segments and storing it into the list.
            List<String> parameters = uri.getPathSegments();
            if (parameters != null && !parameters.isEmpty()) {
                // Extract the last segment safely
                String param = parameters.get(parameters.size() - 1);
                Toast.makeText(this, "Parameter: " + param, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No parameters found in the URI.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "URI is null.", Toast.LENGTH_SHORT).show();
        }

        getMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodDatabase fdb = Room.databaseBuilder(getApplicationContext(), FoodDatabase.class, "my_food_database").allowMainThreadQueries().build();
                FoodItemsDao foodItemsDao = fdb.foodItemsDao();
                List<FoodItemsEntity> foodItemsEntityList = foodItemsDao.getTheMenu();

                String str = "";
                for (FoodItemsEntity foodItemsEntity: foodItemsEntityList){
                    str = str +"\t"+foodItemsEntity.getFood()+"   "+foodItemsEntity.getPrice()+"\n\n";
                }
                Text.setText(str);
            }
        });


    }


}