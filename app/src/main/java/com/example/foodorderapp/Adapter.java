package com.example.foodorderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<FoodItemsEntity> foodItemsEntity;
    public Adapter(List<FoodItemsEntity> foodItemsEntity) {
        this.foodItemsEntity = foodItemsEntity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv1.setText(foodItemsEntity.get(position).getFood());
        holder.tv2.setText(String.valueOf(foodItemsEntity.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return foodItemsEntity.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        TextView tv1, tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);

        }
    }
}
