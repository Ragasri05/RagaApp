package com.example.foodorderapp;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<FoodItemsEntity> foodItemsEntity;
    // 1.
    private ActivityResultLauncher<Intent> updateFoodLauncher;
    public Adapter(List<FoodItemsEntity> foodItemsEntity,ActivityResultLauncher<Intent> updateFoodLauncher) {
        this.updateFoodLauncher = updateFoodLauncher;
        this.foodItemsEntity = foodItemsEntity;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Use holder.getAdapterPosition() dynamically instead of relying on the static 'position' parameter
        holder.tv1.setText(foodItemsEntity.get(holder.getBindingAdapterPosition()).getFood());
        holder.tv2.setText(String.valueOf(foodItemsEntity.get(holder.getBindingAdapterPosition()).getPrice()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the adapter position dynamically
                int currentPosition = holder.getBindingAdapterPosition();

                // Ensure position is valid
                if (currentPosition != RecyclerView.NO_POSITION) {
                    FoodDatabase fdb = Room.databaseBuilder(holder.tv1.getContext(),
                            FoodDatabase.class, "my_food_database").allowMainThreadQueries().build();
                    FoodItemsDao foodItemsDao = fdb.foodItemsDao();

                    // Delete the record from Room Database
                    foodItemsDao.deletebyitem(foodItemsEntity.get(currentPosition).getFood());

                    // Remove the item from the list
                    foodItemsEntity.remove(currentPosition);

                    // Notify RecyclerView about the change
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, foodItemsEntity.size());
                }
            }
        });
        //2.
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(holder.editButton.getContext(), Update.class);
                i.putExtra("uid",foodItemsEntity.get(holder.getBindingAdapterPosition()).getUid());
                i.putExtra("updateFood",foodItemsEntity.get(holder.getBindingAdapterPosition()).getFood());
                i.putExtra("updatePrice",foodItemsEntity.get(holder.getBindingAdapterPosition()).getPrice());
                updateFoodLauncher.launch(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemsEntity.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tv1, tv2;
        ImageButton deleteButton;
        ImageView editButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }

    public void updateFood(String updatedFood, Double updatedPrice){

    }



}

