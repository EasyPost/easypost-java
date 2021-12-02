package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

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
