package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class ScanFormCollection extends PaginatedCollection<ScanForm> {
    private List<ScanForm> scanForms;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<ScanForm> scanForms, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = scanForms.get(scanForms.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
