package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecipeManagementActivity extends AppCompatActivity {

    private RecipeManager recipeManager;
    private LinearLayout recipesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_management);

        recipeManager = RecipeManager.getInstance();
        recipesContainer = findViewById(R.id.recipes_container);

        Button btnAdd = findViewById(R.id.btn_add_recipe);

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddRecipeActivity.class));
        });

        refreshRecipeList();
    }

    private void refreshRecipeList() {
        recipesContainer.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(this);

        for (Recipe r : recipeManager.listRecipes()) {

            LinearLayout card = (LinearLayout) inflater.inflate(R.layout.item_recipe, null);

            ImageView img = card.findViewById(R.id.img_recipe);
            TextView name = card.findViewById(R.id.text_recipe_name);
            TextView desc = card.findViewById(R.id.text_recipe_desc);

            img.setImageResource(r.getImageResId());
            name.setText(r.getName());
            desc.setText(r.getDescription());

            recipesContainer.addView(card);
        }
    }
    protected void onResume() {
        super.onResume();
        refreshRecipeList();
    }

}