package com.example.foodorderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;
// Adapter class is the bridge between datasouce and the UI componenet.

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // data source for the adapter.
    List<FoodItemsEntity> foodItemsEntity;

    // constructor to initialise the adapter.
    public Adapter(List<FoodItemsEntity> foodItemsEntity) {
        this.foodItemsEntity = foodItemsEntity;
    }

    //onCreate is a method in RecyclerView.Adapter class.
    //creates and inflates a single item view (singlerow.xml) for the RecyclerView.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater os used to convert xml file into view object.
        // didn't understand the parameters.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        //returns a view older object
        return new ViewHolder(view);
    }
    // gets the data from the list and binds it to the views in the view older.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1.setText(foodItemsEntity.get(holder.getBindingAdapterPosition()).getFood());
        holder.tv2.setText(String.valueOf(foodItemsEntity.get(holder.getBindingAdapterPosition()).getPrice()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the adapter position dynamically
                int currentPosition = holder.getBindingAdapterPosition();

                // Ensure position is valid
                if (currentPosition != RecyclerView.NO_POSITION) {
                    //Instantiating the Room database.
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
}