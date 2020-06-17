package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import de.fhdo.pflegetagebuch.services.TaskHandlerService;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GetNextTasksHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("GetNextTasksIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        TaskHandlerService taskHandlerService = new TaskHandlerService();

        StringBuilder taskListBuilder = new StringBuilder();




        return handlerInput.getResponseBuilder()
                .withSpeech()
                .withReprompt()
                .build();
    }
}
