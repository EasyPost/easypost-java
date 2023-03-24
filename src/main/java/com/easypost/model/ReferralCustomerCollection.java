package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public final class ReferralCustomerCollection extends PaginatedCollection<ReferralCustomer> {
    private List<ReferralCustomer> referralCustomers;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<ReferralCustomer> referralCustomers, Integer pageSize)
            throws EndOfPaginationError {
        String lastId = referralCustomers.get(referralCustomers.size() - 1).getId();

        Map<String, Object> parameters = new java.util.HashMap<>();
        parameters.put("before_id", lastId);

        if (pageSize != null) {
            parameters.put("page_size", pageSize);
        }

        return parameters;
    }
}
