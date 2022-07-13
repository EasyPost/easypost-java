package com.easypost.model.beta;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReferralCustomerCollection extends EasyPostResource {
    private List<ReferralCustomer> referralCustomers;
    private Boolean hasMore;

    /**
     * Get a list of ReferralCustomers.
     *
     * @return List of ReferralCustomers objects
     */
    public List<ReferralCustomer> getReferralCustomers() {
        return referralCustomers;
    }

    /**
     * Set a list of ReferralCustomers.
     *
     * @param referralCustomers List of ReferralCustomers objects
     */
    public void setReferralCustomers(final List<ReferralCustomer> referralCustomers) {
        this.referralCustomers = referralCustomers;
    }

    /**
     * Get whether there are more ReferralCustomers to retrieve.
     *
     * @return whether there are more ReferralCustomers to retrieve
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more ReferralCustomers to retrieve.
     *
     * @param hasMore Boolean whether there are more ReferralCustomers to retrieve
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
