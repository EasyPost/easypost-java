package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public class BatchCollection extends EasyPostResource {
    List<Batch> batches;
    Boolean hasMore;

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
