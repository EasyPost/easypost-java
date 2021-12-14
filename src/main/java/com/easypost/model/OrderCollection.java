/**
 * OrderCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class OrderCollection extends EasyPostResource {
    private List<Order> orders;
    private Boolean hasMore;

    /**
     * Get a list of orders.
     *
     * @return List of Order objects.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Set a list of orders.
     *
     * @param orders List of Order objects.
     */
    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Get whether there are more orders to retrieve.
     *
     * @return true if there are more orders to retrieve, false otherwise.
     */
    public Boolean getHasMore() {
        return hasMore;
    }

    /**
     * Set whether there are more orders to retrieve.
     *
     * @param hasMore true if there are more orders to retrieve, false otherwise.
     */
    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
