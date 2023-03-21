package com.easypost.model;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
public abstract class PaginatedCollection extends EasyPostResource {

    private Boolean hasMore;

    /**
     * Get the next page of a paginated collection.
     *
     * @param apiCallFunction The function to call to get the next page.
     * @param currentEntries  The current entries in the collection. Needed to determine the next page parameters.
     * @param <TCollection>   The type of the collection to retrieve.
     * @param <TEntries>      The type of the entries in the collection.
     * @return The next page of the collection.
     * @throws EndOfPaginationError If there are no more pages to retrieve.
     */
    public final <TCollection extends PaginatedCollection, TEntries extends EasyPostResource> TCollection getNextPage(
            Function<Map<String, Object>, TCollection> apiCallFunction, List<TEntries> currentEntries)
            throws EndOfPaginationError {
        int defaultPageSize = 20;

        return getNextPage(apiCallFunction, currentEntries, defaultPageSize);
    }

    /**
     * Get the next page of a paginated collection with a custom page size.
     *
     * @param apiCallFunction The function to call to get the next page.
     * @param currentEntries  The current entries in the collection. Needed to determine the next page parameters.
     * @param pageSize        The page size to use for the next page.
     * @param <TCollection>   The type of the collection to retrieve.
     * @param <TEntries>      The type of the entries in the collection.
     * @return The next page of the collection.
     * @throws EndOfPaginationError If there are no more pages to retrieve.
     */
    public final <TCollection extends PaginatedCollection, TEntries extends EasyPostResource> TCollection getNextPage(
            Function<Map<String, Object>, TCollection> apiCallFunction, List<TEntries> currentEntries, int pageSize)
            throws EndOfPaginationError {
        if (currentEntries == null || currentEntries.size() == 0) {
            throw new EndOfPaginationError();
        }

        if (!this.hasMore) {
            throw new EndOfPaginationError();
        }

        Map<String, Object> parameters = buildNextPageParameters(currentEntries, pageSize);

        return apiCallFunction.apply(parameters);
    }

    /**
     * Build the parameters for retrieving the next page of a paginated collection.
     *
     * @param entries    The current entries in the collection. Needed to determine the next page parameters.
     * @param pageSize   The page size to use for the next page.
     * @param <TEntries> The type of the entries in the collection.
     * @return The parameters for retrieving the next page of a paginated collection.
     */
    protected abstract <TEntries extends EasyPostResource> Map<String, Object> buildNextPageParameters(
            List<TEntries> entries, int pageSize);
}
