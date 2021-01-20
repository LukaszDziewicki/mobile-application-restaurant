package com.example.mobileapp.database;

import com.example.mobileapp.menu.Dish;
import com.example.mobileapp.model.User;
import com.example.mobileapp.model.Waiter;

import java.util.List;

public interface DatabaseInterface {

    void addDish();

    void updateDish();

    List<Dish> getMenu();

    void addUser(User user);

    void updateUser(User user);

    void addWaiter(Waiter waiter);

    String getUserEmail(String userId);

    User getUserData(String userId);



}
