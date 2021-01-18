package com.example.mobileapp.model;

import android.widget.EditText;

public class User {
    private String email;
    private String password;
    private String street;
    private String house;
    private String flat;
    private String postalCode;
    private String city;

    public User(EditText email, EditText password, EditText street, EditText house, EditText flat, EditText postalCode, EditText city) {
        this.email = email.getText().toString().trim();
        this.password = password.getText().toString().trim();
        this.street = street.getText().toString().trim();
        this.house = house.getText().toString().trim();
        this.flat = flat.getText().toString().trim();
        this.postalCode = postalCode.getText().toString().trim();
        this.city = city.getText().toString().trim();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
