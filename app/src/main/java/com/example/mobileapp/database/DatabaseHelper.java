package com.example.mobileapp.database;

import android.view.View;
import android.widget.Toast;

import com.example.mobileapp.activities.user.UserProfileActivity;
import com.example.mobileapp.model.User;
import com.example.mobileapp.menu.Dish;
import com.example.mobileapp.model.Waiter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class DatabaseHelper implements DatabaseInterface{

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
    public String getUserEmail(String userId) {
        return null;
    }

    @Override
    public User getUserData(String userId) {
        return null;
    }
}
