package com.example.utasteproject;

public class Administrator extends User{
    public Administrator(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
    public void adminSetEmail(User user, String newEmail){
        user.setEmail(newEmail);
    }


}