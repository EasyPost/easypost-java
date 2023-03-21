package com.easypost.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public final class AddressCollection extends PaginatedCollection {
    private List<Address> addresses;

    @Override
    protected <TEntries extends EasyPostResource> Map<String, Object> buildNextPageParameters(List<TEntries> entries,
                                                                                              int pageSize) {
        String lastId = entries.get(entries.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);
        parameters.put("page_size", pageSize);

        return parameters;
    }
}
