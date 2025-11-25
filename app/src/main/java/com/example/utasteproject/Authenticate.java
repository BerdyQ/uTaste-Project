package com.example.utasteproject;

public interface Authenticate {
    boolean authenticate(String username, String pwd);
    void changePassword(String newPwd);
}