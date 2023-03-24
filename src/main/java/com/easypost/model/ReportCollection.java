package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;
import lombok.Setter;

@Getter
public final class ReportCollection extends PaginatedCollection<Report> {
    private List<Report> reports;

    @Setter
    private String type;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Report> reports, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = reports.get(reports.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);
        parameters.put("type", type);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
