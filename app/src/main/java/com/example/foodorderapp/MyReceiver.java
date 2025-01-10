package com.example.foodorderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Boolean isOn = intent.getBooleanExtra("state", false);
        if (isOn) {
            Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT).show();
        }
    }
}