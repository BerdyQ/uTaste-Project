package com.example.utasteproject;

public class Ingredient {
    private String name;
    private double quantity;
    private String unit;
    //Constructor
    public Ingredient(String name, double quantity, String unit){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
    //Getters and Setters
    public String getName(){return this.name;}
    public double getQuantity(){return this.quantity;}
    public String getUnit(){return this.unit;}
    public void setName(String name){this.name = name;}
    public void setQuantity(double quantity){this.quantity = quantity;}
    public void setUnit(String unit){this.unit = unit;}

}
