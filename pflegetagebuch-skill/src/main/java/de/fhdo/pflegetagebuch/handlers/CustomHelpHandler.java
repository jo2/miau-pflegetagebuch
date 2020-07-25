package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CustomHelpHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("CustomHelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        RequestHelper helper = RequestHelper.forHandlerInput(handlerInput);
        String helpArea = helper.getSlotValue("helpArea").orElse("none");

        String help = "";
        switch (helpArea) {
            case "general":
                help = "Dieser Skill ermöglicht dir die Dokumentation von Pflegeaufgaben";
                break;
            case "showTasks":
                help = "Diese Funktion zeigt dir die nächsten drei Aufgaben, die du zu erledigen hast an";
                break;
            case "finishTasks":
                help = "Diese Funktion lässt dich eine Aufgabe abschließen. Dazu wird der Name der Aufgabe, der Gesundheitsstatus des Pflegebedürftigen und der Grad der gegebenen Hilfe benötigt";
                help += "Bei Essensaufgaben wird zudem die Art des Essens und die Menge protokolliert.";
                break;
            case "createTasks":
                help = "Diese Funktion lässt dich neue Aufgaben anlegen. Neben dem Namen der Aufgabe kannst du auch eine Priorität und ein Fälligkeitsdatum angeben.";
                break;
            default:
        }

        return handlerInput.getResponseBuilder()
                .withReprompt("Kann ich sonst noch etwas für Sie erledigen")
                .withSpeech(help + "Kann ich sonst noch etwas für Sie erledigen")
                .build();
    }
}
