package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import de.fhdo.pflegetagebuch.domain.HealthStatus;
import de.fhdo.pflegetagebuch.domain.MealTask;
import de.fhdo.pflegetagebuch.domain.MealType;
import de.fhdo.pflegetagebuch.domain.SupportNeeded;
import de.fhdo.pflegetagebuch.services.TaskHandlerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class CompleteMealHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("CompleteMealIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        RequestHelper helper = RequestHelper.forHandlerInput(handlerInput);

        MealTask mealTask = new MealTask();
        // TODO name: name = (mealType | mealTime) mealDate
        mealTask.setName("test");
        mealTask.setAmountEaten(Double.parseDouble(helper.getSlotValue("amountEaten").get()));
        mealTask.setMeal(helper.getSlotValue("dish").get());
        mealTask.setMealDate(LocalDate.now());
        mealTask.setMealType(MealType.BREAKFAST);
        mealTask.setCompletionDate(LocalDateTime.now());
        mealTask.setHealthStatus(HealthStatus.valueOf(helper.getSlotValue("healthStatus").get()));
        mealTask.setSupportNeeded(SupportNeeded.valueOf(helper.getSlotValue("supportNeeded").get()));
        TaskHandlerService taskHandlerService = new TaskHandlerService();
        taskHandlerService.completeTask(mealTask);

        return handlerInput.getResponseBuilder()
                .withReprompt("Der Task " + mealTask.getName() + " wurde gespeichert.")
                .build();
    }
}
