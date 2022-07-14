package com.easypost.model.beta;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReferralCustomerCollection extends EasyPostResource {
    private List<ReferralCustomer> referralCustomers;
<<<<<<< HEAD
    private Boolean hasMore;
=======
    private boolean hasMore;
>>>>>>> 347cdeb6f677412daf600c500abdc5967882a4a3

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
<<<<<<< HEAD
    public Boolean getHasMore() {
=======
    public boolean getHasMore() {
>>>>>>> 347cdeb6f677412daf600c500abdc5967882a4a3
        return hasMore;
    }

    /**
     * Set whether there are more ReferralCustomers to retrieve.
     *
     * @param hasMore Boolean whether there are more ReferralCustomers to retrieve
     */
<<<<<<< HEAD
    public void setHasMore(final Boolean hasMore) {
=======
    public void setHasMore(final boolean hasMore) {
>>>>>>> 347cdeb6f677412daf600c500abdc5967882a4a3
        this.hasMore = hasMore;
    }
}
