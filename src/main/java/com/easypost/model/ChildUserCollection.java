package com.easypost.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public final class ChildUserCollection extends PaginatedCollection<ChildUser> {
    private List<ChildUser> children;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<ChildUser> children, Integer pageSize) {
        String lastId = children.get(children.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
