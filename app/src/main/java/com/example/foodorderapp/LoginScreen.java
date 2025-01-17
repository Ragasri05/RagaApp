package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                startActivity(new Intent(getApplicationContext(), RegisterScreen.class));
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

        startMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(LoginScreen.this, ServiceProvider.class));
            }
        });

        StopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(LoginScreen.this,ServiceProvider.class));
            }
        });

        //8.Loading Image udsing Picasso.
        im1 = findViewById(R.id.imageView2);
        String url = "https://thumbs.dreamstime.com/b/login-icon-button-vector-illustration-isolated-white-background-127001787.jpg";
        Picasso.get().load(url).into(im1);
    }
}