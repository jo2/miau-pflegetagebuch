package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import de.fhdo.pflegetagebuch.util.Util;

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
        String helpArea = helper.getSlotValue("HelpArea").orElse("general");

        String help = "";
        switch (helpArea) {
            case "general":
            case "allgemeines":
                help = "Dieser Skill ermöglicht dir die Dokumentation von Pflegeaufgaben. Du kannst Pflegeaufgaben erstellen, anzeigen und als erledigt markieren. Für " +
                        "Hilfe zum Erstellen von Pflegeaufgaben frage: hilf mir mit Aufgaben erstellen, für Hilfe zum Abschließen von Pflegeaufgaben " +
                        "frage: hilf mir mit Aufgaben abschließen, für Hilfe zum Anzeigen von Pflegeaufgaben frage: Hilf mir mit Aufgaben anzeigen. ";
                break;
            case "showTasks":
            case "Aufgaben anzeigen":
                help = "Diese Funktion zeigt dir die nächsten drei Aufgaben, die du zu erledigen hast, an. Frage beispielsweise: was ist noch zu tun. ";
                break;
            case "finishTasks":
            case "Aufgaben abschließen":
                help = "Diese Funktion lässt dich eine Aufgabe abschließen. Dazu wird der Name der Aufgabe, der Gesundheitsstatus des Pflegebedürftigen und der Grad " +
                        "der gegebenen Hilfe benötigt. Bei Essensaufgaben wird zudem die Art des Essens und die Menge protokolliert. Sage beispielsweise: trage im " +
                        "Pflegetagebuch ein waschen wurde abgeschlossen. Anschließend startet ein Dialog, der dich durch die Protokollierung führt. Um ein Essen zu " +
                        "protokollieren, sage beispielsweise: trage im Pflegetagebuch ein abendessen wurde eingenommen. ";
                break;
            case "createTasks":
            case "Aufgaben erstellen":
                help = "Diese Funktion lässt dich neue Aufgaben anlegen. Neben dem Namen der Aufgabe kannst du auch eine Priorität und ein Fälligkeitsdatum angeben. " +
                        "Um eine neue Aufgabe anzulegen, sage zum Beispiel: Erstelle die Aufgabe waschen. Anschließend startet ein Dialog, der dich durch die Erstellung " +
                        "der Aufgabe führt. ";
                break;
            default:
                help = "Hierbei kann ich dir leider nicht helfen. ";
                break;
        }

        return handlerInput.getResponseBuilder()
                .withReprompt(help + " Kann ich sonst noch etwas für Sie erledigen?")
                .withSpeech(help + " Kann ich sonst noch etwas für Sie erledigen?")
                .build();
    }
}
