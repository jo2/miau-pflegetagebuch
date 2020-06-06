package de.fhdo.pflegetagebuch.domain;

import java.time.LocalDate;

public class MealTask extends Task {
    private String meal;
    private double amountEaten;
    private MealType mealType;
    private LocalDate mealDate;

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getAmountEaten() {
        return amountEaten;
    }

    public void setAmountEaten(double amountEaten) {
        this.amountEaten = amountEaten;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }
}
