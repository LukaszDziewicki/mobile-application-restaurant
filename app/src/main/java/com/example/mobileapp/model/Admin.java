package com.example.mobileapp.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Admin extends Account{
    public Admin(String email, String password, FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        super(email, password, firebaseAuth, firebaseFirestore);
    }
}
