package com.easypost.hooks;

import java.util.function.Function;
import java.util.ArrayList;
import java.util.List;

public class RequestHook {
    private List<Function<RequestHookResponses, Object>> eventHandlers = new ArrayList<>();

    /**
     * Add a function to the list of event handlers.
     * @param handler The event handler function to be added.
     */
    public void addEventHandler(Function<RequestHookResponses, Object> handler) {
        eventHandlers.add(handler);
    }

    /**
     * Remove a function to the list of event handlers.
     * @param handler The event handler function to be removed.
     */
    public void removeEventHandler(Function<RequestHookResponses, Object> handler) {
        eventHandlers.remove(handler);
    }
    
    /**
     * Execute all the functions from the event handlers.
     * @param datas The datas from the hooks.
     */
    public void executeEventHandler(RequestHookResponses datas) {
        for (Function<RequestHookResponses, Object> eventHandler : eventHandlers) {
            @SuppressWarnings("unused")
            Object result = eventHandler.apply(datas);
        }
    }
}
