package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UserManager
        userManager = new UserManager();

        setupDemoUsers();

        // Connect the XML UI to Java
        EditText emailInput = findViewById(R.id.email_input);
        EditText passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {

            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = userManager.authenticateUser(email, password);

            if (user == null) {
                Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login successful! Welcome " + user.getFirstName(), Toast.LENGTH_SHORT).show();
                // Navigate to the next activity or perform other actions
                if(user instanceof Administrator){
                    startActivity(new Intent(this, AdminDashboardActivity.class));
                }
                else if (user instanceof Chef){
                    startActivity(new Intent(this, ChefActivity.class));
                }
                else if (user instanceof Waiter){
                    startActivity(new Intent(this, WaiterActivity.class));
                }
            }
        });
    }

    private void setupDemoUsers() {
        userManager.addUser(new Administrator("Berdy", "Querette", "admin@utaste.ca", "admin-pwd"));
        userManager.addUser(new Chef("Samuel", "IRIE", "chef@utaste.ca", "chef-pwd"));
        userManager.addUser(new Waiter("John", "Doe", "waiter@utaste.ca", "waiter-pwd"));
    }
}