package de.fhdo.pflegetagebuch.config;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import de.fhdo.pflegetagebuch.handlers.BeleidigeMichHandler;
import de.fhdo.pflegetagebuch.handlers.CustomLaunchRequestHandler;

public class SimpleAlexaSkillStreamHandler extends SkillStreamHandler {

    public SimpleAlexaSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandler(new BeleidigeMichHandler())
                .addRequestHandler(new CustomLaunchRequestHandler())
                .build());
    }
}
