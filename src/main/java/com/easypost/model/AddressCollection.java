package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class AddressCollection extends EasyPostResource {
    private List<Address> addresses;
    private Boolean hasMore;

    /**
     * Get a list of addresses.
     *
     * @return List of Address objects
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * Set a list of addresses.
     *
     * @param addresses List of Address objects
     */
    public void setAddresses(final List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * Get whether there are more addresses to retrieve.
     *
     * @return whether there are more addresses to retrieve
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
