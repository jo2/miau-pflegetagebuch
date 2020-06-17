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
        Task task = new Task();
        
        String taskName = Util.getContentFromSlot("task", helper);
        String healthStatusValue = Util.getContentFromSlot("healtStatus", helper);
        HealthStatus healthStatus = HealthStatus.valueOf(healthStatusValue);
        String supportNeededValue = Util.getContentFromSlot("supportNeeded", helper);
        SupportNeeded supportNeeded = SupportNeeded.valueOf(supportNeededValue);
        task.setName(taskName);
        task.setHealthStatus(healthStatus);
        task.setSupportNeeded(supportNeeded);
        task.setCompletionDate(LocalDateTime.now());
        TaskHandlerService taskHandlerService = new TaskHandlerService();
        taskHandlerService.completeTask(task);

        return handlerInput.getResponseBuilder()
                .withReprompt("")
                .withSpeech("Kann ich sonst noch etwas für Sie erledigen")
                .build();
    }
}
