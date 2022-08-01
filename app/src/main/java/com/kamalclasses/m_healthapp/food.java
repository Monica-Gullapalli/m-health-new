package com.kamalclasses.m_healthapp;

public class food {
    private String foodname;
    private String foodcalories;

    public food(String foodname, String foodcalories){
        this.foodname = foodname;
        this.foodcalories = foodcalories;
    }

    public food(){

    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodcalories() {
        return foodcalories;
    }

    public void setFoodcalories(String foodcalories) {
        this.foodcalories = foodcalories;
    }

    @Override
    public String toString(){
        return "Food Name: " + foodname + "\nFood Calories: " + foodcalories;
    }
}
