package de.fhdo.pflegetagebuch.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
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

        Slot slot = helper.getSlot("mealType").get();
        System.out.println(slot.getValue());
        System.out.println(slot.getName());

        slot.getResolutions().getResolutionsPerAuthority().forEach(resolution -> {
            System.out.println(resolution.getValues());
        });

        System.out.println(helper.getSlotValue("mealType"));
        System.out.println(helper.getSlotValue("mealDate"));
        System.out.println(helper.getSlotValue("mealTime"));
        System.out.println(helper.getSlotValue("supportNeeded"));
        System.out.println(helper.getSlotValue("healthStatus"));
        System.out.println(helper.getSlotValue("dish"));
        System.out.println(helper.getSlotValue("amountEaten"));

        MealTask mealTask = new MealTask();
        // TODO name: name = (mealType | mealTime) mealDate
        StringBuilder nameBuilder = new StringBuilder();
        if (helper.getSlotValue("mealType").isPresent()) {
            nameBuilder.append(helper.getSlotValue("mealType").get());
            mealTask.setMealType(MealType.valueOf(helper.getSlotValue("mealType").get()));
        } else if (helper.getSlotValue("mealTime").isPresent()) {
            nameBuilder.append(helper.getSlotValue("mealTime").get());
        }
        nameBuilder.append(" ");
        if (helper.getSlotValue("mealDate").isPresent()) {
            nameBuilder.append(helper.getSlotValue("mealDate").get());
        } else {
            nameBuilder.append(LocalDate.now());
            mealTask.setMealDate(LocalDate.now());
        }

        mealTask.setName(nameBuilder.toString());
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
                .withSpeech("Der Task " + mealTask.getName() + " wurde gespeichert.")
                .withReprompt("Der Task " + mealTask.getName() + " wurde gespeichert.")
                .build();
    }
}
