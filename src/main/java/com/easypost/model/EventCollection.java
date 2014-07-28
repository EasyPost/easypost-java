package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

public class EventCollection extends EasyPostResource {
  List<Event> events;
  Boolean hasMore;

  public List<Event> getEvents() {
    return events;
  }
  public void setEvents(List<Event> events) {
    this.events = events;
  }
  public Boolean getHasMore() {
    return hasMore;
  }
  public void setHasMore(Boolean hasMore) {
    this.hasMore = hasMore;
  }
}
