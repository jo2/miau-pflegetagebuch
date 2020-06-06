package de.fhdo.pflegetagebuch.config;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import de.fhdo.pflegetagebuch.handlers.BeleidigeMichHandler;
import de.fhdo.pflegetagebuch.handlers.CustomLaunchRequestHandler;

public class SimpleAlexaSkillStreamHandler extends SkillStreamHandler {

    public SimpleAlexaSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandler(new BeleidigeMichHandler())
                .addRequestHandler(new CustomLaunchRequestHandler())
                .build());

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("Test-Table");
        String id = "1";
        GetItemSpec spec = new GetItemSpec().withPrimaryKey("ID", id);
        try {
            System.out.println("Attempting to read the item...");
            table.scan().forEach(it -> System.out.println("GetItem succeeded: " + it.toString()));

        }
        catch (Exception e) {
            System.err.println("Unable to read item: " + id);
            System.err.println(e.getMessage());
        }


    }
}
