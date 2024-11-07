package com.example.myapplication;

// Import necessary packages for Android functionality and view binding
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivityLoginBinding;

// LoginActivity class represents the login screen of the application
public class LoginActivity extends AppCompatActivity {

    // Binding object for accessing views in the layout file activity_login.xml
    ActivityLoginBinding binding;
    // Database helper for managing user data operations
    DatabaseHelper databaseHelper;

    // onCreate method, called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding for activity_login.xml layout
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the database helper for user data validation
        databaseHelper = new DatabaseHelper(this);

        // Set up an onClickListener for the login button
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the email and password entered by the user
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                // Check if either email or password fields are empty
                if(email.equals("") || password.equals("")) {
                    // Display a message prompting the user to fill all fields
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Validate the user's credentials with the database
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                    // If credentials are valid, proceed to DashboardActivity
                    if(checkCredentials) {
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        // If credentials are invalid, show an error message
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set up an onClickListener for the signup redirect text
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect the user to the SignupActivity
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
