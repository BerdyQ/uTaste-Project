package com.example.utasteproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("DEMO", "===== UTaste Deliverable 2 Demo Start =====");

        // ------------------------------
        // 1. CREATE MANAGERS
        // ------------------------------
        UserManager userManager = new UserManager();
        RecipeManager recipeManager = new RecipeManager();

        // ------------------------------
        // 2. ADD USERS
        // ------------------------------
        Administrator admin = new Administrator("John", "Doe", "admin@gmail.com", "1234");
        Chef chef = new Chef("Alice", "Smith", "chef@gmail.com", "chef123");
        Waiter waiter = new Waiter("Bob", "Jones", "waiter@gmail.com", "wait123");

        userManager.addUser(admin);
        userManager.addUser(chef);
        userManager.addUser(waiter);

        Log.d("DEMO", "Users added: administrator, chef, waiter");

        // ------------------------------
        // 3. AUTHENTICATE USER
        // ------------------------------
        User logged = userManager.authenticateUser("admin@gmail.com", "1234");

        if (logged != null) {
            Log.d("DEMO", "Authentication success for admin.");
        } else {
            Log.d("DEMO", "Authentication failed.");
        }

        // ------------------------------
        // 4. ADD RECIPE
        // ------------------------------
        Recipe burger = new Recipe("Burger", "Juicy beef burger", 0);

        // Add some ingredients (using getters)
        burger.addIngredient(new Ingredient("Beef Patty", 200, "g"));
        burger.addIngredient(new Ingredient("Cheese", 1, "slice"));
        burger.addIngredient(new Ingredient("Bun", 1, "unit"));

        recipeManager.addRecipe(burger);

        Log.d("DEMO", "Recipe added: " + burger.getName());

        // ------------------------------
        // 5. LIST RECIPES
        // ------------------------------
        Log.d("DEMO", "Listing all recipes:");

        for (Recipe r : recipeManager.listRecipes()) {
            Log.d("DEMO", "Recipe: " + r.getName() + " - " + r.getDescription());

            for (Ingredient ing : r.getIngredients()) {
                Log.d("DEMO", "    Ingredient: "
                        + ing.getName() + " "
                        + ing.getQuantity() + " "
                        + ing.getUnit());
            }
        }

        // ------------------------------
        // 6. UPDATE RECIPE
        // ------------------------------
        Recipe updatedBurger = new Recipe("Burger", "Updated: extra cheese", 0);
        updatedBurger.addIngredient(new Ingredient("Beef Patty", 250, "g"));
        updatedBurger.addIngredient(new Ingredient("Double Cheese", 2, "slices"));
        updatedBurger.addIngredient(new Ingredient("Bun", 1, "unit"));

        boolean updated = recipeManager.updateRecipe(updatedBurger);

        Log.d("DEMO", "Update Burger recipe: " + updated);

        // ------------------------------
        // 7. REMOVE RECIPE
        // ------------------------------
        recipeManager.removeRecipe("Burger");
        Log.d("DEMO", "Burger removed. Recipe count: " + recipeManager.listRecipes().size());

        Log.d("DEMO", "===== UTaste Deliverable 2 Demo End =====");
    }
}