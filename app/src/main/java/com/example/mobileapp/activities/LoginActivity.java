package com.example.mobileapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mobileapp.activities.admin.AdminActivity;
import com.example.mobileapp.activities.user.UserActivity;
import com.example.mobileapp.activities.waiter.WaiterActivity;
import com.example.mobileapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private EditText emailTb, passwordTb;
    private Button loginButton;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTb.getText().toString().trim();
                String password = passwordTb.getText().toString().trim();

                if(checkLoginValidation(email, password)){
                    progressBar.setVisibility(View.VISIBLE);
                    checkAccessAndSignInWithEmailAndPassword(email, password);
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(firebaseAuth.getInstance().getCurrentUser() != null){
            checkUserAccessLevel(firebaseAuth.getCurrentUser().getUid());
        }
    }

    private void init(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        emailTb = findViewById(R.id.emailTb);
        passwordTb = findViewById(R.id.passwordTb);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);
    }

    private boolean checkLoginValidation(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            emailTb.setError("Email is Required.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordTb.setError("Password is Required");
            return false;
        }

        if (password.length() < 6) {
            passwordTb.setError("password must be >= 6 characters");
            return false;
        }
        return true;
    }

    private void checkAccessAndSignInWithEmailAndPassword(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                checkUserAccessLevel(authResult.getUser().getUid());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Error, Invalid email or password", Toast.LENGTH_SHORT).show();
                clearLoginEditText();

            }
        });
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess" + documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin") != null){
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    Toast.makeText(LoginActivity.this, "Admin Account", Toast.LENGTH_SHORT).show();
                    finish();
                }else if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(getApplicationContext(), UserActivity.class));
                    Toast.makeText(LoginActivity.this, "User Account", Toast.LENGTH_SHORT).show();
                    finish();
                }else if(documentSnapshot.getString("isWaiter") != null){
                    startActivity(new Intent(getApplicationContext(), WaiterActivity.class));
                    Toast.makeText(LoginActivity.this, "Waiter Account", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }

    private void clearLoginEditText() {
        emailTb.getText().clear();
        passwordTb.getText().clear();
    }

    public void register(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}