package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class InsuranceCollection extends PaginatedCollection<Insurance> {
    private List<Insurance> insurances;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Insurance> insurances, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = insurances.get(insurances.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
