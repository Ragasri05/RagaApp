package com.example.foodorderapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeUtils;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {
    //Defining a variable to hold the recyclerView Interface.
    private final RecyclerViewInterface recyclerViewInterface;

    // list of strings that holds the names of database that you want to show in the RecyclerView.
    private List<String> DatabaseList;
    Context context;

    public DatabaseAdapter(Context context, RecyclerViewInterface recyclerViewInterface, List<String> DatabaseList) {
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        this.DatabaseList = DatabaseList;
    }


    @NonNull
    @Override
    // OnCreate is called when a View Holder for a new Item is Created.
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Layout Inflator is Used to load the Design for one item in the list.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdatabase,parent,false);
        return new DatabaseViewHolder(view, recyclerViewInterface, DatabaseList);
    }


    @Override
    // onBindViewHolder assigns data to an item in the RecyclerView.
    // position tells which item in the list should be processed.
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        // gets the databse name of the current position and assings it to the variable databaseName.
        String databaseName = DatabaseList.get(position);
        // sets the name to the Text View in the Layout.
        holder.tableName.setText(databaseName);
        /*
        // ===1===
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CustomerMenu.class);
            intent.putExtra("selectedDatabaseName",databaseName);
            context.startActivity(intent);
        });

         */
        holder.itemView.setOnClickListener(v -> {
           recyclerViewInterface.onItemClick(position);
        });
    }

    @Override
    // this method returns the total number of items in the List.
    // because the Recycler View needs to Know How many items to Display.
    public int getItemCount() {

        return DatabaseList.size();
    }

    // holds the Views for each item.
    public static class DatabaseViewHolder extends RecyclerView.ViewHolder{

        TextView tableName;

        public DatabaseViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface, List<String> DatabseList) {
            super(itemView);
            // itemView finds the textView inside the Layout.
            tableName = itemView.findViewById(R.id.tableName);
        }
    }




}



