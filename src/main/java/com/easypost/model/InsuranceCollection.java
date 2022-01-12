/**
 * InsuranceCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class InsuranceCollection extends EasyPostResource {
    private List<Insurance> insurances;
    private Boolean hasMore;

    /**
     * Get the list of insurances.
     *
     * @return List of Insurance objects.
     */
    public List<Insurance> getInsurances() {
        return insurances;
    }

    /**
     * Set the list of insurances.
     *
     * @param insurances List of Insurance objects.
     */
    public void setInsurances(final List<Insurance> insurances) {
        this.insurances = insurances;
    }

    /**
     * Get whether there are more insurances.
     *
     * @return true if there are more insurances.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more insurances.
     *
     * @param hasMore true if there are more insurances.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
