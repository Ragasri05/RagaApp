package com.example.foodorderapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;


public class LoginScreen extends AppCompatActivity {
    EditText LEmail, LPassword;
    TextView LTextView;
    Button LButton, MenuButton, StopMusic, startMusic;
    FirebaseAuth fb;
    ImageView im1;
    SharedPreferences sharedPreferences;
    Switch aSwitch;
    Boolean nightMode;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);

        LEmail = findViewById(R.id.LEmail);
        LPassword = findViewById(R.id.LPassword);
        LButton = findViewById(R.id.LButton);
        LTextView = findViewById(R.id.LTextView);
        fb = FirebaseAuth.getInstance();
        MenuButton = findViewById(R.id.MenuButton);
        StopMusic = findViewById(R.id.StopMusicButton);
        startMusic =findViewById(R.id.startMusicButton);
        aSwitch = findViewById(R.id.aSwitch);

        LButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = LEmail.getText().toString().trim();
                String password = LPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    LEmail.setError("Please enter your email address");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    LPassword.setError("Please Enter your password");
                    return;
                }
                fb.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String ownerId = fb.getCurrentUser().getUid();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("ownerId", ownerId); // Pass the ownerId
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginScreen.this, "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        LTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
                finish();
            }
        });

        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this, Customer.class));
                finish();
            }
        });

        // when start Music is clicked it creates the background service.
        startMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(LoginScreen.this, ServiceProvider.class));
            }
        });

        StopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating a new Intent in stop service in to start the service provider class.
                stopService(new Intent(LoginScreen.this,ServiceProvider.class));
            }
        });


        //8.Loading Image udsing Picasso.
        im1 = findViewById(R.id.imageView2);
        String url = "https://thumbs.dreamstime.com/b/login-icon-button-vector-illustration-isolated-white-background-127001787.jpg";
        Picasso.get().load(url).into(im1);

        // Shared Preferences.
        sharedPreferences = getSharedPreferences("MySharedPreferences",MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night",false);
        if (nightMode){
            aSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTheme();
            }
        });
    }

    private void setTheme(){
        if (nightMode){  // If dark mode is currently ON
            // it switches to light mode
            // This method is used to set the app's theme mode globally.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //
            editor = sharedPreferences.edit();
            editor.putBoolean("night",false); // saving the new setting
        }
        else { // if light mode is currently on
            // them it switches to light mode.
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor = sharedPreferences.edit();
            editor.putBoolean("night",true); // saving the new setting.
        }
        editor.apply();
    }
}