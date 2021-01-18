package com.example.mobileapp.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Account implements AccountInterface {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private String email;
    private String password;

    public Account(String email, String password, FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore) {
        this.email = email;
        this.password = password;
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
    }



    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }

    public void setFirebaseFirestore(FirebaseFirestore firebaseFirestore) {
        this.firebaseFirestore = firebaseFirestore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
