package com.example.myapplication;

// Import necessary Android and project-specific packages
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityDashboardBinding;

// DashboardActivity class represents a screen (Activity) in the application
public class DashboardActivity extends AppCompatActivity {

    // Binding object for accessing the views in the layout file activity_dashboard.xml
    ActivityDashboardBinding binding;

    // Called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the parent class's onCreate method
        super.onCreate(savedInstanceState);

        // Initialize the binding by inflating the layout
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());

        // Set the root view of the binding as the content view for this activity
        setContentView(binding.getRoot());
    }
}
