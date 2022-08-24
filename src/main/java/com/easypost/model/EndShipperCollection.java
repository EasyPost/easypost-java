package com.easypost.model;

import java.util.List;

public class EndShipperCollection {
    private List<EndShipper> endShippers;
    private Boolean hasMore;

    /**
     * Get a list of EndShippers.
     *
     * @return List of EndShipper objects
     */
    public List<EndShipper> getEndShippers() {
        return endShippers;
    }

    /**
     * Set a list of EndShippers.
     *
     * @param addresses List of EndShipper objects
     */
        public void setEndShippers(final List<EndShipper> addresses) {
        this.endShippers = addresses;
    }

    /**
     * Get whether there are more EndShippers to retrieve.
     *
     * @return whether there are more EndShippers to retrieve
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more EndShippers to retrieve.
     *
     * @param hasMore Boolean whether there are more EndShippers to retrieve
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
