package com.example.mobileapp.activities.admin;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mobileapp.activities.Activity;
import com.example.mobileapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends Activity {
    private BottomNavigationView navigationView;
    private TextView textView;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_admin);
         init();


         textView.setText(R.string.house);
         navigationView.setOnNavigationItemSelectedListener(nav);
    }

    private void init(){
        navigationView = findViewById(R.id.bottom_nav);
        textView = findViewById(R.id.textview);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
       @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.home:
                    textView.setText(R.string.home);
                    addDish();
                    break;
 	            case R.id.favorite:
                    textView.setText(R.string.favorite);
                    break;
                case R.id.notification:
                    textView.setText(R.string.notification);
                    break;
            }
            return true;
       }
     };

    private void addDish() {

    }
}