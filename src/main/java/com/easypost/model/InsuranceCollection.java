/**
 * InsuranceCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class InsuranceCollection extends EasyPostResource {
    private List<Insurance> insurances;
    private Boolean hasMore;

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(final List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
