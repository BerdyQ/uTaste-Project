package com.example.utasteproject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeManager {
    private HashMap<String, Recipe> recipes = new HashMap<>();
    public boolean addRecipe(Recipe recipe){
        if(recipe == null){return false;}
        else if(recipes.containsKey(recipe.getName())){return false;}
        recipes.put(recipe.getName(),recipe);
        return true;
    }
    public Recipe getRecipeByName(String name){
        return recipes.get(name);
    }
    public boolean removeRecipe(String name){
        if(!recipes.containsKey(name)){
            return false;
        }
        recipes.remove(name);
        return true;
    }
    public ArrayList<Recipe> listRecipes(){
        return new ArrayList<>(recipes.values());
    }
    public boolean updateRecipe(Recipe recipe){
        if(recipe == null){return false;}
        else if(!recipes.containsKey(recipe.getName())){
            return false;
        }
        recipes.put(recipe.getName(),recipe);
        return true;
    }
}
