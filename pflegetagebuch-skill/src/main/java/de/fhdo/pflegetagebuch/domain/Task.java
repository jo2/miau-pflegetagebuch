package de.fhdo.pflegetagebuch.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName = "Tasks")
public class Task {
    protected UUID id;
    protected String name;
    protected LocalDateTime completionDate;
    protected SupportNeeded supportNeeded;
    protected HealthStatus healthStatus;
    protected LocalDateTime dueDate;
    protected Priority priority;

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
    @DynamoDBAttribute(attributeName = "completionDate")
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "supportNeeded")
    public SupportNeeded getSupportNeeded() {
        return supportNeeded;
    }

    public void setSupportNeeded(SupportNeeded supportNeeded) {
        this.supportNeeded = supportNeeded;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "healthStatus")
    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "dueDate")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "priority")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        object.put("name", this.name);
        object.put("completionDate", this.completionDate);
        object.put("supportNeeded", this.supportNeeded);
        object.put("healthStatus", this.healthStatus);
        object.put("dueDate", this.dueDate);
        object.put("priority", this.priority);
        return object;
    }
}
