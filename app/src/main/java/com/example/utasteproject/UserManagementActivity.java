package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserManagementActivity extends AppCompatActivity {

    private UserManager userManager;
    private LinearLayout usersContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        userManager = UserManager.getInstance();
        usersContainer = findViewById(R.id.user_list_container);

        Button btnAdd = findViewById(R.id.btn_add_user);

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddUserActivity.class));
        });

        refreshUserList();
    }

    private void refreshUserList() {
        usersContainer.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(this);

        for (User u : userManager.listUsers()) {

            LinearLayout card = (LinearLayout) inflater.inflate(R.layout.item_user, null);

            ImageView img = card.findViewById(R.id.img_user);
            TextView name = card.findViewById(R.id.text_user_name);
            TextView role = card.findViewById(R.id.text_user_role);

            img.setImageResource(R.drawable.icon_account);
            name.setText(u.getFirstName() + " " + u.getLastName());
            role.setText(u.getClass().getSimpleName());

            card.setOnClickListener(v -> {
                Intent intent = new Intent(this, UserDetailsActivity.class);
                intent.putExtra("USER_EMAIL", u.getEmail());
                startActivity(intent);
            });

            usersContainer.addView(card);
        }
    }

    protected void onResume() {
        super.onResume();
        refreshUserList();
    }
}