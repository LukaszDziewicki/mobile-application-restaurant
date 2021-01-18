package com.example.mobileapp.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobileapp.activities.Activity;
import com.example.mobileapp.Const.Const;
import com.example.mobileapp.menu.MenuActivity;
import com.example.mobileapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;


public class UserActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private NavigationView navigationView;
    private TextView emailTextViewNav;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        setToolbar();
        setEmailTextViewNav();


    }



    private void init(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);

        navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        emailTextViewNav = (TextView) headerView.findViewById(R.id.emailTextViewNav);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getUid();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navi_account:
                Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                break;
            case R.id.navi_menu:
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                break;
            case R.id.navi_orders:
                Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navi_contact:
                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();

                Intent callIntent = new Intent(Intent.ACTION_DIAL, Const.PHONE_NUMBER);
                startActivity(callIntent);
                break;
           case R.id.navi_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                logout(navigationView);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setEmailTextViewNav() {
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                emailTextViewNav.setText((documentSnapshot.getString("UserEmail")).trim());
        }
    });
    }
}

