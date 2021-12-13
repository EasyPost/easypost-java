/**
 * ParcelCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ParcelCollection extends EasyPostResource {
    private List<Parcel> parcels;
    private Boolean hasMore;

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(final List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
