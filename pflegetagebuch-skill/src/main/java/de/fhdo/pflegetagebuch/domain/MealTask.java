package de.fhdo.pflegetagebuch.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.LocalDate;

@DynamoDBTable(tableName = "Task")
public class MealTask extends Task {
    private String meal;
    private double amountEaten;
    private MealType mealType;
    private LocalDate mealDate;

    @DynamoDBAttribute(attributeName = "meal")
    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    @DynamoDBAttribute(attributeName = "amountEaten")
    public double getAmountEaten() {
        return amountEaten;
    }

    public void setAmountEaten(double amountEaten) {
        this.amountEaten = amountEaten;
    }

    @DynamoDBAttribute(attributeName = "mealType")
    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @DynamoDBAttribute(attributeName = "mealDate")
    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    @Override
    public String toString() {
        return "MealTask{" +
                "id=" + id +
                ", name=" + name +
                ", completionDate=" + completionDate +
                ", supportNeeded=" + supportNeeded +
                ", healthStatus=" + healthStatus +
                ", meal=" + meal +
                ", amountEaten=" + amountEaten +
                ", mealType=" + mealType +
                ", mealDate=" + mealDate +
                '}';
    }
}
