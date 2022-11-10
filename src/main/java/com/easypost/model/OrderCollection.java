package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class OrderCollection extends EasyPostResource {
    private List<Order> orders;
    private Boolean hasMore;
}
