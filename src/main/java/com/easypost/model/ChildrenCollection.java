package com.easypost.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public final class ChildrenCollection extends PaginatedCollection<Children> {
    private List<Children> children;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<Children> children, Integer pageSize) {
        String lastId = children.get(children.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
