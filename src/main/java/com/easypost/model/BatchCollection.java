package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class BatchCollection extends PaginatedCollection<Batch> {
    private List<Batch> batches;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Batch> batches, Integer pageSize)
            throws EndOfPaginationError {
        throw new EndOfPaginationError();
    }
}
