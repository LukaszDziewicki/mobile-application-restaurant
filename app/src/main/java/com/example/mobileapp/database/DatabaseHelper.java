package com.example.mobileapp.database;

import com.example.mobileapp.model.User;
import com.example.mobileapp.menu.Dish;
import com.example.mobileapp.model.Waiter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseHelper implements DatabaseInterface {
    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firebaseFirestore;
    private String userId;

    public DatabaseHelper() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.firebaseFirestore = FirebaseFirestore.getInstance();
        this.userId = firebaseAuth.getUid();
    }

    @Override
    public void addDish() {

    }

    @Override
    public void updateDish() {

    }

    @Override
    public List<Dish> getMenu() {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void addWaiter(Waiter waiter) {

    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public User getUserData(String userId) {
        return null;
    }

}
