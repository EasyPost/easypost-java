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

    /**
     * Get a list of batches.
     *
     * @return List of Batch objects.
     */
    public List<Batch> getBatches() {
        return batches;
    }

    /**
     * Set a list of batches.
     *
     * @param batches List of Batch objects.
     */
    public void setBatches(final List<Batch> batches) {
        this.batches = batches;
    }

    /**
     * Get whether there are more batches to retrieve.
     *
     * @return true if there are more batches to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more batches to retrieve.
     *
     * @param hasMore true if there are more batches to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
