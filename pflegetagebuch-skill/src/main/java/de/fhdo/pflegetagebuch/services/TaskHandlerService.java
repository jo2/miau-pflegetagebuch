package de.fhdo.pflegetagebuch.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import de.fhdo.pflegetagebuch.domain.Task;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskHandlerService {

    private DynamoDBMapper getCLient() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        DynamoDBMapper dynamoDB = new DynamoDBMapper(client);
        return dynamoDB;
    }

    public void completeTask(Task task) {
        getCLient().save(task);
    }

    public List<Task> getProtocolForDay(LocalDate date) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":val1", new AttributeValue().withS(date.toString()));

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withFilterExpression("completionDate = :val1")
                .withExpressionAttributeValues(valueMap);

        List<Task> protocol = getCLient().scan(Task.class, query);
        protocol.forEach(System.out::println);
        return protocol;
    }
}
