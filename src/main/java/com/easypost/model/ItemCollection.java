package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class ItemCollection extends EasyPostResource {
  List<Item> items;
  Boolean hasMore;

  public List<Item> getItems() {
    return items;
  }
  public void setItems(List<Item> items) {
    this.items = items;
  }
  public Boolean getHasMore() {
    return hasMore;
  }
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
