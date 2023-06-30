package com.easypost.hooks;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventHook {
    private List<Function<HashMap<String, Object>, Object>> eventHandlers = new ArrayList<>();

    /**
     * Add a function to the list of event handlers.
     * @param handler The event handler function to be added.
     */
    public void addEventHandler(Function<HashMap<String, Object>, Object> handler) {
        eventHandlers.add(handler);
    }

    /**
     * Remove a function to the list of event handlers.
     * @param handler The event handler function to be removed.
     */
    public void removeEventHandler(Function<HashMap<String, Object>, Object> handler) {
        eventHandlers.remove(handler);
    }
    
    /**
     * Execute all the functions from the event handlers.
     * @param datas The datas from the hooks.
     */
    public void executeEventHandler(HashMap<String, Object> datas) {
        for (Function<HashMap<String, Object>, Object> eventHandler : eventHandlers) {
            Object result = eventHandler.apply(datas);
        }
    }
}
