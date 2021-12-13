/**
 * AddressCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class AddressCollection extends EasyPostResource {
    private List<Batch> addresses;
    private Boolean hasMore;

    public List<Batch> getAddresses() {
        return addresses;
    }

    public void setAddresses(final List<Batch> addresses) {
        this.addresses = addresses;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
