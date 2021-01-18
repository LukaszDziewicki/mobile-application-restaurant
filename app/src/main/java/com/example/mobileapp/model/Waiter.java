package com.example.mobileapp.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Waiter extends Account{
    public Waiter(String email, String password, FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        super(email, password, firebaseAuth, firebaseFirestore);
    }
}
