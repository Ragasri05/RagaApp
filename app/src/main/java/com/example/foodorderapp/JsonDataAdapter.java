package com.example.foodorderapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JsonDataAdapter extends RecyclerView.Adapter<JsonDataAdapter.JsonDataViewHolder> {


    @NonNull
    @Override
    public JsonDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull JsonDataViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class JsonDataViewHolder extends RecyclerView.ViewHolder {

        public JsonDataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
