package com.example.mobileapp.model;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;
    private String email;
    private String street;
    private String house;
    private String flat;
    private String postalCode;
    private String city;


    public User(String email, FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore, EditText street, EditText house, EditText flat, EditText postalCode, EditText city) {
        firebaseAuth = FirebaseAuth.getInstance();
        this.id = firebaseAuth.getUid();
        this.street = street.getText().toString().trim();
        this.house = house.getText().toString().trim();
        this.flat = flat.getText().toString().trim();
        this.postalCode = postalCode.getText().toString().trim();
        this.city = city.getText().toString().trim();

    }

}
