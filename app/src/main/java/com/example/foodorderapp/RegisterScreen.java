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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class RegisterScreen extends AppCompatActivity {

    FirebaseAuth fb;
    EditText REmail, RPassword;
    TextView RTextView;
    Button RButton;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);

            fb = FirebaseAuth.getInstance();
            REmail = findViewById(R.id.Ret1);
            RPassword = findViewById(R.id.Ret2);
            RTextView = findViewById(R.id.Rtv1);
            RButton = findViewById(R.id.Rb1);
            imageView = findViewById(R.id.imageView);

            // checking if the user already logged in
            if (fb.getCurrentUser() != null){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            RButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = REmail.getText().toString().trim();
                    String password = RPassword.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        REmail.setError("Please enter your email address");
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        RPassword.setError("Please Enter your password");
                        return;
                    }

                    // creating a new user.
                    //registering the user with firebase;
                    fb.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterScreen.this, "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else{
                                Toast.makeText(RegisterScreen.this, "Opps!, an error occured."+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            });

            RTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), LoginScreen.class));
                    finish();
                }
            });

            //8.ImageLoading Using Picasso.
            String url = "https://img.freepik.com/premium-vector/register-now-badge-vector-isolated-white-vector-button-registration-services-blogs-websites_735449-447.jpg?semt=ais_hybrid";
            Picasso.get().load(url).error(R.drawable.ic_launcher_foreground).into(imageView);
    }
}