package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class RefundCollection extends PaginatedCollection<Refund> {
    private List<Refund> refunds;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Refund> refunds, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = refunds.get(refunds.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
