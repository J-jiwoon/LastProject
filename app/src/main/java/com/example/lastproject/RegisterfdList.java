 package com.example.lastproject;

public class RegisterfdList {

    String foodname;
    String foodkcal;
    String carbo;
    String protein;
    String fat;

    public RegisterfdList(String foodname, String foodkcal, String carbo, String protein, String fat) {
        this.foodname = foodname;
        this.foodkcal = foodkcal;
        this.carbo = carbo;
        this.protein = protein;
        this.fat = fat;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodkcal() {
        return foodkcal;
    }

    public void setFoodkcal(String foodkcal) {
        this.foodkcal = foodkcal;
    }

    public String getCarbo() {
        return carbo;
    }

    public void setCarbo(String carbo) {
        this.carbo = carbo;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

}