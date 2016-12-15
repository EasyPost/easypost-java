package com.easypost.model;

import java.util.List;
import com.easypost.net.EasyPostResource;

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
