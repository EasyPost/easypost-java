package com.easypost.model;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
public abstract class PaginatedCollection extends EasyPostResource {

    private Boolean hasMore;

    public <TCollection extends PaginatedCollection, TEntries extends EasyPostResource> TCollection getNextPage(
            Function<Map<String, Object>, TCollection> apiCallFunction, List<TEntries> currentEntries)
            throws EndOfPaginationError {
        int defaultPageSize = 20;

        return getNextPage(apiCallFunction, currentEntries, defaultPageSize);
    }

    public <TCollection extends PaginatedCollection, TEntries extends EasyPostResource> TCollection getNextPage(Function<Map<String, Object>, TCollection> apiCallFunction, List<TEntries> currentEntries, int pageSize)
            throws EndOfPaginationError {
        if (currentEntries == null || currentEntries.size() == 0)
        {
            throw new EndOfPaginationError();
        }

        if (!this.hasMore)
        {
            throw new EndOfPaginationError();
        }

        Map<String, Object> parameters = buildNextPageParameters(currentEntries, pageSize);

        return apiCallFunction.apply(parameters);
    }

    protected abstract <TEntries extends EasyPostResource> Map<String, Object> buildNextPageParameters(List<TEntries> entries, int pageSize);
}
