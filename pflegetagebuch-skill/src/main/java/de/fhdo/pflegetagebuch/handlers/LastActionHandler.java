package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import de.fhdo.pflegetagebuch.util.Util;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class LastActionHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("LastActionIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        AttributesManager attributesManager = handlerInput.getAttributesManager();
        Map<String,Object> attributes = attributesManager.getSessionAttributes();
        String lastAction = (String) attributes.getOrDefault("lastAction", "Keine Aktion");
        return handlerInput.getResponseBuilder()
                .withSpeech("Deine letzte Aktion war: " + lastAction)
                .withReprompt("Kann ich dir sonst noch weiterhelfen?")
                .build();
    }
}
