package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        userManager = UserManager.getInstance();

        EditText firstName = findViewById(R.id.input_firstname);
        EditText lastName = findViewById(R.id.input_lastname);
        EditText email = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.input_password);
        Spinner roleSpinner = findViewById(R.id.input_role);
        Button btnSave = findViewById(R.id.btn_save_user);

        // Dropdown
        String[] roles = {"Administrator", "Chef", "Waiter"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, roles);
        roleSpinner.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {

            String fn = firstName.getText().toString().trim();
            String ln = lastName.getText().toString().trim();
            String em = email.getText().toString().trim();
            String pw = password.getText().toString().trim();
            String role = roleSpinner.getSelectedItem().toString();

            if (fn.isEmpty() || ln.isEmpty() || em.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            User newUser;

            switch (role) {
                case "Administrator":
                    newUser = new Administrator(fn, ln, em, pw);
                    break;
                case "Chef":
                    newUser = new Chef(fn, ln, em, pw);
                    break;
                default:
                    newUser = new Waiter(fn, ln, em, pw);
            }

            boolean success = userManager.addUser(newUser);

            if (!success) {
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "User created!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}