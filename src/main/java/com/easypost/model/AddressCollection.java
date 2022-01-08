/**
 * AddressCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class AddressCollection extends EasyPostResource {
    private List<Batch> addresses;
    private Boolean hasMore;

    /**
     * Get a list of addresses.
     *
     * @return List of Batch objects
     */
    public List<Batch> getAddresses() {
        return addresses;
    }

    /**
     * Set a list of addresses.
     *
     * @param addresses List of Batch objects
     */
    public void setAddresses(final List<Batch> addresses) {
        this.addresses = addresses;
    }

    /**
     * Get whether there are more addresses to retrieve.
     *
     * @return Boolean whether there are more addresses to retrieve
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more addresses to retrieve.
     *
     * @param hasMore Boolean whether there are more addresses to retrieve
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
