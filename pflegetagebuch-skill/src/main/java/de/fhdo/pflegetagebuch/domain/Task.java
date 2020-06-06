package de.fhdo.pflegetagebuch.domain;

import java.time.LocalDateTime;

public class Task {
    protected Long id;
    protected String name;
    protected LocalDateTime completionDate;
    protected SupportNeeded supportNeeded;
    protected HealthStatus healthStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public SupportNeeded getSupportNeeded() {
        return supportNeeded;
    }

    public void setSupportNeeded(SupportNeeded supportNeeded) {
        this.supportNeeded = supportNeeded;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }
}
