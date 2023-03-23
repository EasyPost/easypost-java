package com.easypost.model;

import java.util.List;
import java.util.Map;

import com.easypost.exception.General.EndOfPaginationError;
import lombok.Getter;

@Getter
public class EndShipperCollection extends PaginatedCollection<EndShipper>{
    private List<EndShipper> endShippers;

    @Override
    protected Map<String, Object> buildNextPageParameters(List<EndShipper> endShippers, Integer pageSize)
            throws EndOfPaginationError {
        throw new EndOfPaginationError();
    }
}
