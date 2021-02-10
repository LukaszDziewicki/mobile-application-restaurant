package com.example.mobileapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.mobileapp.activities.user.UserActivity;
import com.example.mobileapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private EditText emailRegistration, passwordRegistration, streetRegistration, houseRegistration, flatRegistration, postalCodeRegistration, cityRegistration;
    private Button registerButton, backToLoginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        setbackToLoginButtonListener();

        setRegisterButtonListenerValidateAndCreateUser();

    }

    private void init(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        emailRegistration = findViewById(R.id.emailRegistration);
        passwordRegistration = findViewById(R.id.passwordRegistration);
        streetRegistration = findViewById(R.id.streetRegistration);
        houseRegistration = findViewById(R.id.houseRegistration);
        flatRegistration = findViewById(R.id.flatRegistration);
        postalCodeRegistration = findViewById(R.id.postalCodeRegistration);
        cityRegistration = findViewById(R.id.cityRegistration);

        registerButton = findViewById(R.id.registerButton);
        backToLoginButton = findViewById(R.id.backToLoginButton);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setRegisterButtonListenerValidateAndCreateUser(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = emailRegistration.getText().toString().trim();
                String password = passwordRegistration.getText().toString().trim();
                String city = cityRegistration.getText().toString().trim();
                String house = houseRegistration.getText().toString().trim();
                String flat = flatRegistration.getText().toString().trim();
                String street = streetRegistration.getText().toString().trim();
                String postalCode = postalCodeRegistration.getText().toString().trim();


                if(checkRegisterValidation(email, password)){
                    progressBar.setVisibility(View.VISIBLE);
                    createFirebaseUserWithEmailAndPassword(email, password);
                }


            }
        });
    }

    private void setbackToLoginButtonListener(){
        backToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    private boolean checkRegisterValidation(String email, String password) {

        if (TextUtils.isEmpty(email)) {
            emailRegistration.setError("Email is Required.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordRegistration.setError("Password is Required");
            return false;
        }

        if (password.length() < 6) {
            passwordRegistration.setError("password must be >= 6 characters");
            return false;
        }

        return true;
    }

    private void createFirebaseUserWithEmailAndPassword(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = firebaseFirestore.collection("Users").document(user.getUid());
                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("UserEmail", emailRegistration.getText().toString());
                userInfo.put("Street", streetRegistration.getText().toString());
                userInfo.put("House", houseRegistration.getText().toString());
                userInfo.put("Flat", flatRegistration.getText().toString());
                userInfo.put("PostalCode", postalCodeRegistration.getText().toString());
                userInfo.put("City", cityRegistration.getText().toString());
                userInfo.put("isUser", "1");

                documentReference.set(userInfo);

                startActivity(new Intent(getApplicationContext(), UserActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this, "Failed to create account, email in use" , Toast.LENGTH_SHORT).show();
            }
        });
    }







}