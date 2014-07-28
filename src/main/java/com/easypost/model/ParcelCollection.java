package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ParcelCollection extends EasyPostResource {
  List<Parcel> parcels;
  Boolean hasMore;

  public List<Parcel> getParcels() {
    return parcels;
  }
  public void setParcels(List<Parcel> parcels) {
    this.parcels = parcels;
  }
  public Boolean getHasMore() {
    return hasMore;
  }
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
