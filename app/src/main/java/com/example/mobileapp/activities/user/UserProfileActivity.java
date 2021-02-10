package com.example.mobileapp.activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mobileapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class UserProfileActivity extends AppCompatActivity {
    private Button backButton, saveButton;
    private TextView email;
    private EditText street, house, flat, postalCode, city;
    private ProgressBar progressBar;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        init();
        getUserProfileInfo();
        setBackButtonListener();
        setSaveButtonListener();
    }

    private void init() {
        backButton = findViewById(R.id.backButton);
        saveButton = findViewById(R.id.saveButton);
        progressBar = findViewById(R.id.progressBar);

        email = findViewById(R.id.emailTextView);
        street = findViewById(R.id.streetText);
        house = findViewById(R.id.houseText);
        flat = findViewById(R.id.flatText);
        postalCode = findViewById(R.id.postalCodeText);
        city = findViewById(R.id.cityText);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userId = firebaseAuth.getUid();
    }

    private void setBackButtonListener() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setSaveButtonListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Task<Void> task = firebaseFirestore.collection("Users").document(userId)
                        .update("Street", street.getText().toString().trim(),
                                "House", house.getText().toString().trim(),
                                "Flat", flat.getText().toString().trim(),
                                "PostalCode", postalCode.getText().toString().trim(),
                                "City", city.getText().toString().trim())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UserProfileActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });

    }

    private void getUserProfileInfo(){
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                email.setText((documentSnapshot.getString("UserEmail")).trim());
                street.setText((documentSnapshot.getString("Street")).trim());
                house.setText((documentSnapshot.getString("House")).trim());
                flat.setText((documentSnapshot.getString("Flat")).trim());
                postalCode.setText((documentSnapshot.getString("PostalCode")).trim());
                city.setText((documentSnapshot.getString("City")).trim());
            }
        });
    }



}