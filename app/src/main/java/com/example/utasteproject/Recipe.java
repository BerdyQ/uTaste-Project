package com.example.utasteproject;

import java.time.LocalDateTime;
import java.util.*;

public class Recipe {
    private String name;
    private String description;
    private ArrayList<Ingredient> ingredients;
    private int imageResId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Constructor
    public Recipe(String name, String description, int imageResId){
        this.name = name;
        this.description = description;
        this.ingredients = new ArrayList<>();
        this.imageResId = imageResId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    //Convenience Method
    public void changed(){
        this.updatedAt = LocalDateTime.now();
    }
    //Getters and Setters
    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public ArrayList<Ingredient> getIngredients(){return this.ingredients;}
    public int getImageResId(){return this.imageResId;}
    public LocalDateTime getCreatedAt(){return this.createdAt;}
    public LocalDateTime getUpdatedAt(){return this.updatedAt;}
    public void setName(String name){
        this.name = name;
        changed();
    }
    public void setDescription(String description){
        this.description = description;
        changed();
    }
    public void setIngredients(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
        changed();
    }
    public void setImageResId(int imageResId){
        this.imageResId = imageResId;
        changed();
    }
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        changed();
    }
    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        changed();
    }

}
