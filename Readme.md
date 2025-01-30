# Owner Flow:
- if owner has account, then owner has to login.
- if owner doesn't have account, then owner has to regidter first.
- once the owner logs in he will be able to add food item and price.
- when the owner clicks on getMenu button, he/she will be able to view all the item and can delete a particular item by clicking on delete button.
# Customer Flow:
- In the login login Page, there is a Button for Customer.
- when the Customer Clicks on it, he will be able to view the list of databases stored in the app.
- when the user clicks on a particular Databse the app redirects then to that menu and will be able to View the menu.

# Components Used in the app.
- TextView
- EditText
- Button
- RecyclerView.
- Intent
- Firebase
# Basics:
## onCreate method:
  * One of the life cycle methods of Android Activity.
  * it is called when the Activity is first Created.
  * it is used to initialise the components, set up the Ui, restore the saved instance state of the app.
```
// passing the object of Bundle class as a parameter.
protected void onCreate(Bundle savedInstanceState) {
    // the parent class initialises the activity properly before the custom code runs.
    // if not done properly the activity might crash.
    super.onCreate(savedInstanceState);
    // it ensures that our app content uses the full screen.
    EdgeToEdge.enable(this);
    // setContentView method belongs to Activity class in android.
    // it loads the xml file and displays it on the screen.
    setContentView(R.layout.activity_register_screen);
}
```
## getApplicationContext():
- Context: it is like a gateway to apps resources and data.
- getApplicationCOntext() is a method from Context class in Android that gives you global application context instead of a particular apps's context.

###### NOTE:
- Bundle: Bundle is a Data Structure in Android that Stores Key value pairs.</br>it is used to store and restore activity state configurations like Screnn Rotation etc.
- super: it is used to call the parent class's method
# Explaination for the Code.
## Register Screen:
```
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

    // Declaring references.
    //1.1
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

            //1.2 INitialises the firebase Authentication Instance.
            // this method connects our app to firebase Authentications services.
            // it makes sure that all the authentication related ations are handled by a single user.
            fb = FirebaseAuth.getInstance();
            REmail = findViewById(R.id.Ret1);
            RPassword = findViewById(R.id.Ret2);
            RTextView = findViewById(R.id.Rtv1);
            RButton = findViewById(R.id.Rb1);
            imageView = findViewById(R.id.imageView);

            // checking if the user already logged in
            // fb.getCurrentUser returns the currently signed in user.
            if (fb.getCurrentUser() != null){
                // if the user is already authenticated then it redirects them to main activity.
                // the first parameter is the context and the second parameter is the actvity which you want to open.
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            // when the button is clicked then the user will register to the app
            // setOnClickListener tells android to perform the following code when the button is clicked.
            // OnClickListener is an interface in View class and OnClick() is a method that belongs to the interface.
            RButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // creating Strings ts read the text from edit Text.
                    // getText() belongs to editText class. it gets the Text from Edit Text.
                    // toString() is called to get the string representation of entered Text.
                    //trim() belongs to String class it removes the spaces which are there in the begining and the ending.
                    String email = REmail.getText().toString().trim();
                    String password = RPassword.getText().toString().trim();

                    // TextUtils is a utility class in android package.
                    // isEnpty() is a method which returns true is the String is Empty.
                    if (TextUtils.isEmpty(email)) {
                        // setError method belongs to editText class.
                        // it sets error if it is empty.
                        REmail.setError("Please enter your email address");
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        RPassword.setError("Please Enter your password");
                        return;
                    }

                    // creating a new user.
                    //registering the user with firebase;
                    // this is am ethod in firebase authentication which creates a new user with password.
                    // addOnCompleteListener add a listener to the Task. it listens for the task and adds executes the OnCompleteMethod.
                    fb.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        // this is the onComplete() method, which is called once the task is complete. It takes the Task<AuthResult> as a parameter, which contains the result of the operation.
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // if it is successfull.
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
```
