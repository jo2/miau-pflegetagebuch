package de.fhdo.pflegetagebuch.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import de.fhdo.pflegetagebuch.domain.Task;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskHandlerService {

    private DynamoDBMapper getClient() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        DynamoDBMapper dynamoDB = new DynamoDBMapper(client);
        return dynamoDB;
    }

    public void completeTask(Task task) {
        getClient().save(task);
    }

    public List<Task> getProtocolForDay(LocalDate date) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":val1", new AttributeValue().withS(date.atStartOfDay().toString()));
        valueMap.put(":val2", new AttributeValue().withS(date.plusDays(1).atStartOfDay().toString()));

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withFilterExpression("completionDate BETWEEN :val1 AND :val2")
                .withExpressionAttributeValues(valueMap);

        List<Task> protocol = getClient().scan(Task.class, query);
        protocol.forEach(System.out::println);
        return protocol;
    }

    public List<Task> getNextTasks() {
        DynamoDBScanExpression query = new DynamoDBScanExpression();
        List<Task> todos = getClient().scan(Task.class, query);
        todos.sort((task, t1) -> {
            if (task.getDueDate() != null && t1.getDueDate() != null) {
                return task.getDueDate().compareTo(t1.getDueDate());
            } else {
                return task.getName().compareToIgnoreCase(t1.getName());
            }
        });
        todos = todos.subList(0, 5);
        return todos;
    }

    public Task getTaskByName(String taskName) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":val1", new AttributeValue().withS(taskName));

        Map<String, String> expression = new HashMap<>();
        expression.put("#n", "name");

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withFilterExpression("#n = :val1")
                .withExpressionAttributeValues(valueMap)
                .withExpressionAttributeNames(expression)
                .withLimit(1);

        List<Task> tasks = getClient().scan(Task.class, query);
        return tasks.isEmpty() ? null : tasks.get(0);

    }
}
