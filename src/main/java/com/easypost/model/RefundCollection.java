package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class RefundCollection extends EasyPostResource {
  List<Refund> refunds;
  Boolean hasMore;

  public List<Refund> getRefunds() {
    return refunds;
  }
  public void setRefunds(List<Refund> refunds) {
    this.refunds = refunds;
  }
  public Boolean getHasMore() {
    return hasMore;
  }
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
