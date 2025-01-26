package com.example.foodorderapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeUtils;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {
    //Defining a variable to hold the recyclerView Interface.
    private final RecyclerViewInterface recyclerViewInterface;

    // list of strings that holds the names of database that you want to show in the RecyclerView.
    private List<String> DatabaseList;
    Context context;

    public DatabaseAdapter(RecyclerViewInterface recyclerViewInterface, List<String> DatabaseList) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.DatabaseList = DatabaseList;
    }


    @NonNull
    @Override
    // OnCreate is called when a View Holder for a new Item is Created.
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Layout Inflator is Used to load the Design for one item in the list.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdatabase,parent,false);
        return new DatabaseViewHolder(view, recyclerViewInterface);
    }


    @Override
    // onBindViewHolder assigns data to an item in the RecyclerView.
    // position tells which item in the list should be processed.
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        // gets the databse name of the current position and assings it to the variable databaseName.
        String databaseName = DatabaseList.get(position);
        // sets the name to the Text View in the Layout.
        holder.tableName.setText(databaseName);


    }

    @Override
    // this method returns the totak number of items in the List.
    // because the Recycler View needs to Know How many items to Display.
    public int getItemCount() {
        return DatabaseList.size();
    }

    // holds the Views for each item.
    public static class DatabaseViewHolder extends RecyclerView.ViewHolder{

        TextView tableName;

        public DatabaseViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            // itemView finds the textView inside the Layout.
            tableName = itemView.findViewById(R.id.tableName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        // gets the position of the item associated with a specific ViewHolder.
                        int position = getBindingAdapterPosition();
                        // checking if the position is Valid.
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}



