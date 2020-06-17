package de.fhdo.pflegetagebuch.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import de.fhdo.pflegetagebuch.services.TaskHandlerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GetProtocolApiHandler implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        JSONObject responseJson = new JSONObject();
        TaskHandlerService taskHandlerService = new TaskHandlerService();

        JSONArray protocol = new JSONArray();
        taskHandlerService.getProtocolForDay(LocalDate.now()).forEach(task -> protocol.add(task.getName()));

        JSONObject responseBody = new JSONObject();
        responseBody.put("protocol", protocol);

        JSONObject headerJson = new JSONObject();
        headerJson.put("x-custom-header", "my custom header value");

        responseJson.put("statusCode", 200);
        responseJson.put("headers", headerJson);
        responseJson.put("body", responseBody.toString());

        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);
        writer.write(responseJson.toString());
        writer.close();
    }
}
