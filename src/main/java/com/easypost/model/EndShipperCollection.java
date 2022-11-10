package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class EndShipperCollection {
    private List<EndShipper> endShippers;
    private Boolean hasMore;
}
