package com.example.utasteproject;

import java.time.LocalDateTime;

abstract class User implements Authenticate{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public LocalDateTime getCreatedAt(){return this.createdAt;}
    public LocalDateTime getUpdatedAt(){return this.updatedAt;}
    public void setFirstName(String firstName){
        this.firstName = firstName;
        this.updatedAt = LocalDateTime.now();
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
        this.updatedAt = LocalDateTime.now();
    }
    public void setPassword(String password){
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }
    protected void  setEmail(String email){
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean authenticate(String email, String password){
        if(email.equals(this.email) && password.equals(this.password)){
            return true;
        }
        return false;
    }
    public void changePassword(String newPwd) {
        this.password = newPwd;
    }

}