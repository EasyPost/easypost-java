package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TrackerCollection extends PaginatedCollection<Tracker> {
    private List<Tracker> trackers;

    @Setter
    private String trackingCode;

    @Setter
    private String carrier;

    @Override
    protected final Map<String, Object> buildNextPageParameters(List<Tracker> trackers, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = trackers.get(trackers.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        // We only want to include these parameters if they are set (versus defaulting to false; anti-pattern)
        if (trackingCode != null) {
            parameters.put("tracking_code", trackingCode);
        }
        if (carrier != null) {
            parameters.put("carrier", carrier);
        }

        return parameters;
    }
}
