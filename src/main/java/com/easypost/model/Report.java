package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public final class Report extends EasyPostResource {
    private Date startDate;
    private Date endDate;
    private String status;
    private Boolean includeChildren;
    private String url;
    private Date urlExpiresAt;
}
