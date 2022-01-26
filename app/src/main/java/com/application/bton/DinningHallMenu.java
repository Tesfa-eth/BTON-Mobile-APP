package com.application.bton;

public class DinningHallMenu {
    private String day;
    private String mealOfTheDay;
    private String mealType;
    private String meal;

    public DinningHallMenu() {
        day = null;
        mealType = null;
        meal = null;
    }

    public String getDay() {
        return day;
    }

    public String getMealOfTheDay() {
        return mealOfTheDay;
    }

    public String getMealType() {
        return mealType;
    }

    public String getMeal() {
        return meal;
    }
}
