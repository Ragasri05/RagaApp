package com.example.foodorderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())){
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiState){
                case WifiManager.WIFI_STATE_ENABLED:
                    Toast.makeText(context, "Wi-Fi is ENABLED", Toast.LENGTH_SHORT).show();
                    break;

                case WifiManager.WIFI_STATE_DISABLED:
                    Toast.makeText(context, "Wi-Fi is DISABLED", Toast.LENGTH_SHORT).show();
                    break;

                case WifiManager.WIFI_STATE_ENABLING:
                    Toast.makeText(context, "Wi-Fi is ENABLING", Toast.LENGTH_SHORT).show();
                    break;

                case WifiManager.WIFI_STATE_DISABLING:
                    Toast.makeText(context, "Wi-Fi is DISABLING", Toast.LENGTH_SHORT).show();
                    break;

                case WifiManager.WIFI_STATE_UNKNOWN:
                default:
                    Toast.makeText(context, "Wi-Fi state is UNKNOWN", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
