package de.fhdo.pflegetagebuch.util;

import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.request.RequestHelper;

public class Util {
    public static String getContentFromSlot(String slotName, RequestHelper helper) {
        Slot slot = helper.getSlot(slotName).get();
        for (Resolution resolution : slot.getResolutions().getResolutionsPerAuthority()) {
            if (!resolution.getValues().get(0).getValue().getName().equals(slotName)) {
                return resolution.getValues().get(0).getValue().getName().toUpperCase();
            }
        }
        return null;
    }
}
