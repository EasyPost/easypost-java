package com.easypost.model.beta;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReferralCustomerCollection extends EasyPostResource {
    private List<Referral> referrals;
    private Boolean hasMore;

    /**
     * Get a list of referrals.
     *
     * @return List of Batch objects
     */
    public List<Referral> getReferral() {
        return referrals;
    }

    /**
     * Set a list of referrals.
     *
     * @param referrals List of Batch objects
     */
    public void setReferral(final List<Referral> referrals) {
        this.referrals = referrals;
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
