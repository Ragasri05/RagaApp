/*package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class Update extends AppCompatActivity {
    int uid;
    EditText updateFood, updatePrice;
    Button Update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        updateFood = findViewById(R.id.updatefood);
        updatePrice = findViewById(R.id.updatePrice);
        Update = findViewById(R.id.Update);

        updateFood.setText(getIntent().getStringExtra("updateFood"));
        //getDoubleExtra ensures the price is fetched as a double. If no value is passed, it defaults to 0.0.
        updatePrice.setText(String.valueOf(getIntent().getDoubleExtra("updatePrice",0.0)));

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodDatabase fdb = Room.databaseBuilder( getApplicationContext(),
                        FoodDatabase.class, "my_food_database").allowMainThreadQueries().build();
                FoodItemsDao foodItemsDao = fdb.foodItemsDao();
                foodItemsDao.update(uid, updateFood.getText().toString(),Double.valueOf(updatePrice.getText().toString()));
                //6.
                // going back to GetMenu activity.
                Intent j = new Intent(com.example.foodorderapp.Update.this, GetMenu.class);
                j.putExtra("updatedId",uid);
                j.putExtra("updatedFood",updateFood.getText().toString());
                j.putExtra("updatedPrice",Double.parseDouble(updatePrice.getText().toString()));
                setResult(RESULT_OK,j);
                finish();
                //closes the updated activity.
            }
        });
    }
}

 */