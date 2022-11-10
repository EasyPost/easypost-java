package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class EventCollection extends EasyPostResource {
    private List<Event> events;
    private Boolean hasMore;
}
