package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ScanFormCollection extends EasyPostResource {
  List<ScanForm> scanForms;
  Boolean hasMore;

  public List<ScanForm> getScanForms() {
    return scanForms;
  }

  public void setScanForms(List<ScanForm> scanForms) {
    this.scanForms = scanForms;
  }

  public Boolean getHasMore() {
    return hasMore;
  }

  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
