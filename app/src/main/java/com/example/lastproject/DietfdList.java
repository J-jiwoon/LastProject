 package com.example.lastproject;

public class DietfdList {

    String foodname;
    String foodkcal;

    public DietfdList(String foodname, String foodkcal) {
        this.foodname = foodname;
        this.foodkcal = foodkcal;
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

}