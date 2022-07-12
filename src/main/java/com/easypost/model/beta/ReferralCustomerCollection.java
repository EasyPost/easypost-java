package com.easypost.model.beta;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReferralCustomerCollection extends EasyPostResource {
    private List<ReferralCustomers> referralCustomers;
    private Boolean hasMore;

    /**
     * Get a list of referrals.
     *
     * @return List of Batch objects
     */
    public List<ReferralCustomers> getReferralCustomers() {
        return referralCustomers;
    }

    /**
     * Set a list of referrals.
     *
     * @param referralCustomers List of Batch objects
     */
    public void setReferralCustomers(final List<ReferralCustomers> referralCustomers) {
        this.referralCustomers = referralCustomers;
    }

    /**
     * Get whether there are more referrals to retrieve.
     *
     * @return whether there are more referrals to retrieve
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more referrals to retrieve.
     *
     * @param hasMore Boolean whether there are more referrals to retrieve
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
