package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRecipeActivity extends AppCompatActivity {

    private RecipeManager recipeManager;

    private EditText inputName, inputDescription;
    private ImageView recipeImage;
    private LinearLayout ingredientsContainer;
    private Button btnAddIngredient, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        //Connect UI
        inputName = findViewById(R.id.input_recipe_name);
        inputDescription = findViewById(R.id.input_recipe_description);
        ingredientsContainer = findViewById(R.id.ingredients_container);
        btnAddIngredient = findViewById(R.id.btn_add_ingredient);
        btnSave = findViewById(R.id.btn_save_recipe);
        recipeImage = findViewById(R.id.recipe_image);

        recipeManager = RecipeManager.getInstance();

        btnAddIngredient.setOnClickListener(v -> addIngredientRow());
        btnSave.setOnClickListener(v -> saveRecipe());
    }

    private void addIngredientRow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout row = (LinearLayout) inflater.inflate(R.layout.item_ingredient_input, null);
        ingredientsContainer.addView(row);
    }

    private void saveRecipe() {
        String name = inputName.getText().toString().trim();
        String desc = inputDescription.getText().toString().trim();

        if (name.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Name and description are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < ingredientsContainer.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) ingredientsContainer.getChildAt(i);

            EditText ingName = row.findViewById(R.id.input_ing_name);
            EditText ingQty  = row.findViewById(R.id.input_ing_qty);
            EditText ingUnit = row.findViewById(R.id.input_ing_unit);

            if (!ingName.getText().toString().isEmpty()
                    && !ingQty.getText().toString().isEmpty()
                    && !ingUnit.getText().toString().isEmpty()) {

                ingredients.add(new Ingredient(
                        ingName.getText().toString(),
                        Double.parseDouble(ingQty.getText().toString()),
                        ingUnit.getText().toString()
                ));
            }
        }

        Recipe recipe = new Recipe(name, desc, R.drawable.icon_account);
        for (Ingredient ing : ingredients) {
            recipe.addIngredient(ing);
        }

        recipeManager.addRecipe(recipe);

        Toast.makeText(this, "Recipe added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}