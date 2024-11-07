package com.example.myapplication;

// Import necessary packages for Android activity and view binding
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.myapplication.databinding.ActivitySignupBinding;

// SignupActivity class represents the signup screen where new users can register
public class SignupActivity extends AppCompatActivity {

    // Binding object to access views in activity_signup.xml layout file
    ActivitySignupBinding binding;
    // Database helper instance for managing user data
    DatabaseHelper databaseHelper;

    // onCreate method, called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the view binding for the signup activity
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize the database helper for data operations
        databaseHelper = new DatabaseHelper(this);

        // Set up an onClickListener for the signup button
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve email, password, and confirmation password from user input
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                // Check if any of the input fields are empty
                if(email.equals("") || password.equals("") || confirmPassword.equals("")) {
                    // Display a message if fields are left blank
                    Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the password and confirmPassword match
                    if(password.equals(confirmPassword)) {
                        // Verify if a user already exists with this email
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        // If no user exists with this email, proceed with signup
                        if(!checkUserEmail) {
                            // Insert the new user data into the database
                            Boolean insert = databaseHelper.insertData(email, password);

                            if(insert) {
                                // If insertion is successful, show success message and redirect to login
                                Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                // If insertion fails, show error message
                                Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If the email is already registered, prompt the user to log in
                            Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // If passwords do not match, show a warning message
                        Toast.makeText(SignupActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set up an onClickListener for the login redirect text
        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect the user to the LoginActivity if they click on "Log in" text
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
