package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class ReportCollection extends EasyPostResource {
    private List<Report> reports;
    private Boolean hasMore;
}
