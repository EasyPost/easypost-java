/**
 * ReportCollection.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

import com.easypost.net.EasyPostResource;

import java.util.List;

public final class ReportCollection extends EasyPostResource {
    private List<Report> reports;
    private Boolean hasMore;

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(final List<Report> reports) {
        this.reports = reports;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(final Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
