package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class InsuranceCollection extends EasyPostResource {
  List<Insurance> insurances;
  Boolean hasMore;

  public List<Insurance> getInsurances() {
    return insurances;
  }
  public void setInsurances(List<Insurance> insurances) {
    this.insurances = insurances;
  }
  public Boolean getHasMore() {
    return hasMore;
  }
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
