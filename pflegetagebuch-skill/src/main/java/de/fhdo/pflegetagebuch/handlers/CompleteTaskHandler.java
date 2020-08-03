package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import de.fhdo.pflegetagebuch.domain.HealthStatus;
import de.fhdo.pflegetagebuch.domain.SupportNeeded;
import de.fhdo.pflegetagebuch.domain.Task;
import de.fhdo.pflegetagebuch.services.TaskHandlerService;
import de.fhdo.pflegetagebuch.util.Util;

import java.time.LocalDateTime;
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
        String taskName = helper.getSlotValue("task").get();

        TaskHandlerService taskHandlerService = new TaskHandlerService();
        Task task = taskHandlerService.getTaskByName(taskName);
        if (task == null) {
            task = new Task();
        }

        String healthStatusValue = Util.getContentFromSlot("healthStatus", helper);
        HealthStatus healthStatus = HealthStatus.valueOf(healthStatusValue);
        String supportNeededValue = Util.getContentFromSlot("supportNeeded", helper);
        SupportNeeded supportNeeded = SupportNeeded.valueOf(supportNeededValue);
        task.setName(taskName);
        task.setHealthStatus(healthStatus);
        task.setSupportNeeded(supportNeeded);
        task.setCompletionDate(LocalDateTime.now());

        taskHandlerService.completeTask(task);

        Util.saveLastActionInSession("Du hast zuletzt eine Aufgabe als erledigt eingespeichert.", handlerInput.getAttributesManager());

        return handlerInput.getResponseBuilder()
                .withReprompt("")
                .withSpeech("Kann ich sonst noch etwas für dich erledigen")
                .build();
    }
}
