package com.easypost.model;

import java.util.List;

import lombok.Getter;

@Getter
public final class TrackerCollection extends EasyPostResource {
    private List<Tracker> trackers;
    private Boolean hasMore;
}
