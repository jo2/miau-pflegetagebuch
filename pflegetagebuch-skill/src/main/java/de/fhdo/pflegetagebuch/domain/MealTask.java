package de.fhdo.pflegetagebuch.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@DynamoDBTable(tableName = "Tasks")
public class MealTask extends Task {
    private String meal;
    private double amountEaten;
    private MealType mealType;
    private LocalDateTime mealDate;

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
    @DynamoDBTypeConvertedEnum
    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    @DynamoDBAttribute(attributeName = "mealDate")
    public LocalDateTime getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDateTime mealDate) {
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
