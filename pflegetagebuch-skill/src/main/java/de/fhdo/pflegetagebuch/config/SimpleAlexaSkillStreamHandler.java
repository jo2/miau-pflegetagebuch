package de.fhdo.pflegetagebuch.config;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import de.fhdo.pflegetagebuch.handlers.*;

public class SimpleAlexaSkillStreamHandler extends SkillStreamHandler {

    public SimpleAlexaSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandler(new CompleteMealHandler())
                .addRequestHandler(new CompleteTaskHandler())
                .addRequestHandler(new CreateTaskHandler())
                .addRequestHandler(new GetNextTasksHandler())
                .addRequestHandler(new CustomHelpHandler())
                .addRequestHandler(new CustomLaunchRequestHandler())
                .addRequestHandler(new LastActionHandler())
                .build());
        System.out.println("Config loaded");
    }
}
