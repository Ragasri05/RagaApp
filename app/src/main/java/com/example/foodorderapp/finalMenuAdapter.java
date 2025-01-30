package com.example.foodorderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class finalMenuAdapter extends RecyclerView.Adapter<finalMenuAdapter.ViewHolder> {



    // data source for the adapter.
    List<FoodItemsEntity> foodItemsEntityList;


    // constructor to initialise the adapter.
    public finalMenuAdapter (List<FoodItemsEntity> foodItemsEntity) {
        this.foodItemsEntityList = foodItemsEntity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finalmenuonerow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItemsEntity foodItemsEntity = foodItemsEntityList.get(position);
        holder.bind(foodItemsEntity);
    }

    @Override
    public int getItemCount() {
        return foodItemsEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.jsonText);
        }
        public void bind(FoodItemsEntity foodItemsEntity){
            String price = String.valueOf(foodItemsEntity.getPrice());
            textView.setText(foodItemsEntity.getFood()+ "   " + price);
        }


    }
}
