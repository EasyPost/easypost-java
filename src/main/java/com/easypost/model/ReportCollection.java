package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public class ReportCollection extends EasyPostResource {
    List<Report> reports;
    Boolean hasMore;

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
