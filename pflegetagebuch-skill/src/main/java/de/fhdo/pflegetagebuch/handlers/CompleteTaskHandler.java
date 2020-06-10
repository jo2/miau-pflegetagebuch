package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.RequestHelper;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CompleteTaskHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("CompleteTaskIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        RequestHelper helper = RequestHelper.forHandlerInput(handlerInput);
        Slot slot = helper.getSlot("mealType").get();
        return handlerInput.getResponseBuilder()
                .withSpeech("Du Sohn deiner Mutter")
                .build();
    }
}
