package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditRecipeActivity extends AppCompatActivity {

    private RecipeManager recipeManager;
    private Recipe recipe;

    private EditText nameInput, descInput;
    private ImageView imageView;
    private LinearLayout ingredientsContainer;

    private Button btnAddIngredient, btnSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        recipeManager = RecipeManager.getInstance();

        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        recipe = recipeManager.getRecipeByName(recipeName);

        nameInput = findViewById(R.id.edit_recipe_name);
        descInput = findViewById(R.id.edit_recipe_description);
        imageView = findViewById(R.id.edit_recipe_image);
        ingredientsContainer = findViewById(R.id.edit_ingredients_container);
        btnAddIngredient = findViewById(R.id.btn_edit_add_ingredient);
        btnSaveChanges = findViewById(R.id.btn_save_changes);

        loadRecipe();

        btnAddIngredient.setOnClickListener(v -> addIngredientRow(null));
        btnSaveChanges.setOnClickListener(v -> saveChanges());
    }

    private void loadRecipe() {
        nameInput.setText(recipe.getName());
        descInput.setText(recipe.getDescription());
        imageView.setImageResource(recipe.getImageResId());

        for (Ingredient ing : recipe.getIngredients()) {
            addIngredientRow(ing);
        }
    }

    private void addIngredientRow(Ingredient ing) {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout row = (LinearLayout) inflater.inflate(R.layout.item_ingredient_edit, null);

        EditText name = row.findViewById(R.id.edit_ing_name);
        EditText qty = row.findViewById(R.id.edit_ing_qty);
        EditText unit = row.findViewById(R.id.edit_ing_unit);
        Button remove = row.findViewById(R.id.btn_remove_ing);

        if (ing != null) {
            name.setText(ing.getName());
            qty.setText(String.valueOf(ing.getQuantity()));
            unit.setText(ing.getUnit());
        }

        remove.setOnClickListener(v -> ingredientsContainer.removeView(row));

        ingredientsContainer.addView(row);
    }

    private void saveChanges() {
        recipe.setName(nameInput.getText().toString().trim());
        recipe.setDescription(descInput.getText().toString().trim());

        recipe.getIngredients().clear();

        for (int i = 0; i < ingredientsContainer.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) ingredientsContainer.getChildAt(i);

            EditText name = row.findViewById(R.id.edit_ing_name);
            EditText qty = row.findViewById(R.id.edit_ing_qty);
            EditText unit = row.findViewById(R.id.edit_ing_unit);

            if (!name.getText().toString().isEmpty()) {
                recipe.addIngredient(
                        new Ingredient(
                                name.getText().toString(),
                                Double.parseDouble(qty.getText().toString()),
                                unit.getText().toString()
                        )
                );
            }
        }

        Toast.makeText(this, "Recipe updated!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
