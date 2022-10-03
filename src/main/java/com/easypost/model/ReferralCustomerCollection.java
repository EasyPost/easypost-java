package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReferralCustomerCollection extends EasyPostResource {
    private List<ReferralCustomer> referralCustomers;
    private boolean hasMore;

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
    public boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more ReferralCustomers to retrieve.
     *
     * @param hasMore Boolean whether there are more ReferralCustomers to retrieve
     */
    public void setHasMore(final boolean hasMore) {
        this.hasMore = hasMore;
    }
}