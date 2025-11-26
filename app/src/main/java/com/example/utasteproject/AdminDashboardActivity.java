package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button btnRecipes = findViewById(R.id.btn_manage_recipes);
        Button btnUsers = findViewById(R.id.btn_manage_users);
        Button btnLogout = findViewById(R.id.btn_logout);

        btnRecipes.setOnClickListener(v -> {
            startActivity(new Intent(this, RecipeManagementActivity.class));
        });

        btnUsers.setOnClickListener(v -> {
            startActivity(new Intent(this, UserManagementActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // close dashboard so user can't go back with the back button
        });
    }
}