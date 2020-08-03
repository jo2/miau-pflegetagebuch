package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import de.fhdo.pflegetagebuch.domain.Priority;
import de.fhdo.pflegetagebuch.domain.Task;
import de.fhdo.pflegetagebuch.services.TaskHandlerService;
import de.fhdo.pflegetagebuch.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CreateTaskHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("CreateTaskIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        RequestHelper helper = RequestHelper.forHandlerInput(handlerInput);
        String taskName = helper.getSlotValue("task").get();
        TaskHandlerService taskHandlerService = new TaskHandlerService();
        Task task = taskHandlerService.getTaskByName(taskName);
        if (task != null) {
            return handlerInput.getResponseBuilder()
                    .withReprompt("")
                    .withSpeech("Diese Aufgabe ist bereits für den " + task.getDueDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " vorgesehen")
                    .build();
        } else {
            task = new Task();
        }

        String priorityValue = Util.getContentFromSlot("priority", helper);
        Priority priority = Priority.valueOf(priorityValue);
        String dueDateValue = helper.getSlotValue("dueDate").get();
        DateTimeFormatter isoDate = DateTimeFormatter.ISO_DATE;
        LocalDateTime dueDate = LocalDate.parse(dueDateValue, isoDate).atStartOfDay();

        task.setName(taskName);
        task.setPriority(priority);
        task.setDueDate(dueDate);

        Util.saveLastActionInSession("Du hast zuletzt eine Aufgabe hinzugefügt.", handlerInput.getAttributesManager());

        taskHandlerService.completeTask(task);
        return handlerInput.getResponseBuilder()
                .withReprompt("")
                .withSpeech("Kann ich sonst noch etwas für dich erledigen")
                .build();
    }
}
