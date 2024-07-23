package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class ClaimCollection extends PaginatedCollection<Claim> {
    private List<Claim> claims;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Claim> claims, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = claims.get(claims.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
