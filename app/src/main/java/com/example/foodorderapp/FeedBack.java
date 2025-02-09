package com.example.foodorderapp;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class FeedBack extends AppCompatActivity {

    Button sendFeedback;
    EditText feedback;
    TextView textView;
    Uri path;
    ImageView imageView;


    public final String fileAuthority = "com.example.foodorderapp.fileprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_back);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sendFeedback = findViewById(R.id.sendFeedback);
        feedback = findViewById(R.id.feedback);
        textView = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView3);

        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // adds values to the database.
                ContentValues values = new ContentValues();
                //fetching text from editText.
                values.put(ContentProvider.feedback, feedback.getText().toString());
                // inserting into databse through content URI.
                getContentResolver().insert(ContentProvider.contentUri, values);
                Toast.makeText(getBaseContext(), "Thankyou for the feedback!!", Toast.LENGTH_SHORT).show();
                feedback.setText("");
            }
        });
        ///sdcard/Download/download.jpg

        textView.setText("Share Image");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareImageUsingFileProvider();
            }
        });

    }

    private void ShareImageUsingFileProvider(){
        File imageFile = new File(getExternalFilesDir("Download"), "icon.jpg");

        Uri imageUri = FileProvider.getUriForFile(this, fileAuthority, imageFile);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Allow other apps to access it

        startActivity(Intent.createChooser(shareIntent, "Share Image via"));

    }
}