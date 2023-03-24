package com.easypost.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public final class AddressCollection extends PaginatedCollection<Address> {
    private List<Address> addresses;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Address> addresses, Integer pageSize) {
        String lastId = addresses.get(addresses.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
