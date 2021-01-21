package com.example.mobileapp.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mobileapp.Const.Const;
import com.example.mobileapp.R;


import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private ListView menuListView;
    private List<Dish> listDishes;
    private List<Dish> listOrderedDishes;
    private MenuAdapter menuAdapter;
    private Button confirmOrderButton;
    private ProgressBar progressBarMenu;

    private View.OnClickListener onAddDishButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Dish dish = (Dish) view.getTag();
            if (dish.getAmount() > 0) {
                listOrderedDishes.add(dish);
                showToast(dish.getNameDish() + " has been added. Amount : " + dish.getAmount() + "X ");
            } else {
                showToast(" " + dish.getAmount());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
        addDishesMenuList();
        setMenuAdapter();
        menuAdapter.setOnAddDishButtonListener(onAddDishButtonListener);

        setConfirmOrderButton();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    private void init(){
        listDishes = new ArrayList<>();
        menuListView = findViewById(R.id.list_view);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);
        progressBarMenu = findViewById(R.id.progressBarMenu);
        listOrderedDishes = new ArrayList<>();
        progressBarMenu.setVisibility(View.INVISIBLE);

    }

    private void setMenuAdapter() {
        menuAdapter = new MenuAdapter(this, listDishes);
        menuListView.setAdapter(menuAdapter);
    }

    private void addDishesMenuList() {
        listDishes.add(new Dish(R.drawable.logo, "Burger 1", "czosnek / cebula / pieczarki", 22.99, Const.CURRENCY_PLN));
        listDishes.add(new Dish(R.drawable.logo, "Burger 2", "czosnek / cebula / pieczarki", 22.99, Const.CURRENCY_PLN));
        listDishes.add(new Dish(R.drawable.logo, "Burger 3", "czosnek / cebula / pieczarki", 22.99, Const.CURRENCY_PLN));
        listDishes.add(new Dish(R.drawable.logo, "Burger 4", "czosnek / cebula / pieczarki", 22.99, Const.CURRENCY_PLN));
        listDishes.add(new Dish(R.drawable.logo, "Burger 5", "czosnek / cebula / pieczarki", 22.99, Const.CURRENCY_PLN));
        listDishes.add(new Dish(R.drawable.logo, "Burger 6", "czosnek / cebula / pieczarki", 22.99, Const.CURRENCY_PLN));
    }

    private void setConfirmOrderButton() {
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBarMenu.setVisibility(View.VISIBLE);
                //startActivity(new Intent(getApplicationContext(), ConfirmOrderActivity.class));
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}