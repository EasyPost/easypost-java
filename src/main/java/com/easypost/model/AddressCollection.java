package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

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
