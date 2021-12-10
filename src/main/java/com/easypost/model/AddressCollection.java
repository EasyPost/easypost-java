package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public class AddressCollection extends EasyPostResource {
    List<Batch> addresses;
    Boolean hasMore;

    public List<Batch> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Batch> addresses) {
        this.addresses = addresses;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
