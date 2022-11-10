package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class RefundCollection extends EasyPostResource {
    private List<Refund> refunds;
    private Boolean hasMore;
}
