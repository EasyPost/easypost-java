package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class PickupCollection extends PaginatedCollection<Pickup> {
    private List<Pickup> pickups;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Pickup> pickups, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = pickups.get(pickups.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
