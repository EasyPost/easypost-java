package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class BatchCollection extends EasyPostResource {
    private List<Batch> batches;
    private Boolean hasMore;
}
