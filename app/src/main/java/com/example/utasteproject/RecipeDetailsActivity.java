package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;

public class RecipeDetailsActivity extends AppCompatActivity {

    private RecipeManager recipeManager;
    private Recipe recipe;

    private ImageView recipeImage;
    private TextView recipeName, recipeDesc;
    private LinearLayout ingredientsContainer;
    private Button btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeManager = RecipeManager.getInstance();

        // Get recipe name passed from previous screen
        String recipeNameKey = getIntent().getStringExtra("RECIPE_NAME");
        recipe = recipeManager.getRecipeByName(recipeNameKey);

        recipeImage = findViewById(R.id.detail_recipe_image);
        recipeName = findViewById(R.id.detail_recipe_name);
        recipeDesc = findViewById(R.id.detail_recipe_description);
        ingredientsContainer = findViewById(R.id.detail_ingredients_container);
        btnEdit = findViewById(R.id.btn_edit_recipe);
        btnDelete = findViewById(R.id.btn_delete_recipe);

        if (recipe != null) {
            displayRecipeDetails();
        }

        btnDelete.setOnClickListener(v -> confirmDelete());

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditRecipeActivity.class);
            intent.putExtra("RECIPE_NAME", recipe.getName());
            startActivity(intent);
        });
    }

    private void displayRecipeDetails() {
        recipeImage.setImageResource(recipe.getImageResId());
        recipeName.setText(recipe.getName());
        recipeDesc.setText(recipe.getDescription());

        ingredientsContainer.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Ingredient ing : recipe.getIngredients()) {
            TextView tv = new TextView(this);
            tv.setText("- " + ing.getName() + " (" + ing.getQuantity() + " " + ing.getUnit() + ")");
            tv.setTextSize(16);
            tv.setTextColor(0xFF555555);
            tv.setPadding(0, 8, 0, 8);
            ingredientsContainer.addView(tv);
        }
    }

    protected void onResume() {
        super.onResume();
        recipe = recipeManager.getRecipeByName(recipe.getName());
        displayRecipeDetails();
    }


    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Recipe")
                .setMessage("Are you sure you want to delete this recipe?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    recipeManager.removeRecipe(recipe.getName());
                    finish(); // close screen
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}