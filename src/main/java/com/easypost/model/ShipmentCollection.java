package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class ShipmentCollection extends PaginatedCollection<Shipment> {
    private List<Shipment> shipments;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Shipment> shipments, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = shipments.get(shipments.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
