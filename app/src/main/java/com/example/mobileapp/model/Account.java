package com.example.mobileapp.model;

import com.example.mobileapp.database.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firebaseFirestore;
    private DatabaseHelper databaseHelper;

    private String email;
    private String password;

    public Account(FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
    }

}
