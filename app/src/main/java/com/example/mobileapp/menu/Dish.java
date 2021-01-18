package com.example.mobileapp.menu;

import android.widget.Button;

import com.example.mobileapp.Const.Const;


public class Dish {
    private int image;
    private String nameDish;
    private String descriptionDish;
    private double costDish;
    private String currency;
    private int amount;
    private Button increaseAmount, decreaseAmount;



    public Dish(int image, String nameDish, String descriptionDish, double costDish, String currency) {
        this.image = image;
        this.nameDish = nameDish;
        this.descriptionDish = descriptionDish;
        this.costDish = costDish;
        this.currency = currency;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getDescriptionOfDish() {
        return descriptionDish;
    }

    public void setDescriptionOfDish(String descriptionOfDish) {
        this.descriptionDish = descriptionOfDish;
    }

    public double getCostDish() {
        return costDish;
    }

    public void setCostDish(double costDish) {
        this.costDish = costDish;
    }

    public String getCurrency() {
        return currency;
    }

    public Button getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(Button increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Button getDecreaseButton() {
        return decreaseAmount;
    }

    public void setDecreaseButton(Button decreaseButton) {
        this.decreaseAmount = decreaseButton;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void increaseAmount(){
        if(amount < Const.MAX_LIMIT_OF_ONE_DISH){
            amount += 1;
        }else{
            amount = 0;
        }
    }

    public void decreaseAmount(){
        if(amount > 0){
            amount-=1;
        }
    }
}