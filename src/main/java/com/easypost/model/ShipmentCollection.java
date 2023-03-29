package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ShipmentCollection extends PaginatedCollection<Shipment> {
    private List<Shipment> shipments;

    @Setter
    private Boolean purchased;

    @Setter
    private Boolean includeChildren;

    @Override
    protected final Map<String, Object> buildNextPageParameters(List<Shipment> shipments, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = shipments.get(shipments.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        // We only want to include these parameters if they are set (versus defaulting to false; anti-pattern)
        if (purchased != null) {
            parameters.put("purchased", purchased);
        }
        if (includeChildren != null) {
            parameters.put("include_children", includeChildren);
        }

        return parameters;
    }
}
