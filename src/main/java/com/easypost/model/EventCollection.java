package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class EventCollection extends PaginatedCollection<Event> {
    private List<Event> events;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Event> events, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = events.get(events.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
