package com.example.utasteproject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {
    private static UserManager instance;
    private HashMap<String,User> users = new HashMap<>();
    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public boolean addUser(User user){
        if(user == null){return false;}
        else if(users.containsKey(user.getEmail())){return false;}
        users.put(user.getEmail(),user);
            return true;
    }
    public User authenticateUser(String email, String password){
        User user = users.get(email);
        if(user != null && user.authenticate(email,password)){
            return user;
        }
        return null;
    }
    public User getUserByEmail(String email){
        return users.get(email);
    }
    public boolean removeUser(String email){
        if(!users.containsKey(email)){
            return false;
        }
        users.remove(email);
        return true;
    }
    public ArrayList<User> listUsers(){
        return new ArrayList<>(users.values());
    }
}
