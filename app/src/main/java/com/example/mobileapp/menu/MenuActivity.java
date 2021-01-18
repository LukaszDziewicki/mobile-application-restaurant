package com.example.mobileapp.menu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.mobileapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private ListView menuListView;
    private List<Dish> listDishes;
    private MenuAdapter menuAdapter;
    private Button confirmOrderButton;
    private BottomSheetBehavior bottomSheetBehavior;
    int i = R.drawable.logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
        addDishesMenuList();

        //getMenuFromDB();

        setMenuAdapter();
        setConfirmOrderButtonListener();
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


        View bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void setMenuAdapter() {
        menuAdapter = new MenuAdapter(this, listDishes);
        menuListView.setAdapter(menuAdapter);
    }

    private void addDishesMenuList() {
        listDishes.add(new Dish(R.drawable.logo, "Burger 1", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 2", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 3", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 4", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 5", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 6", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 5", "czosnek / cebula / pieczarki", 22.99, "zł"));
        listDishes.add(new Dish(R.drawable.logo, "Burger 6", "czosnek / cebula / pieczarki", 22.99, "zł"));
    }

    private void setConfirmOrderButtonListener() {
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }
}