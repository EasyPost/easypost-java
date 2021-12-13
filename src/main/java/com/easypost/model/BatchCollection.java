/**
 * BatchCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class BatchCollection extends EasyPostResource {
    private List<Batch> batches;
    private Boolean hasMore;

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(final List<Batch> batches) {
        this.batches = batches;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
