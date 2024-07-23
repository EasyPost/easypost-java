package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class ClaimHistoryEntry {
    private String status;
    private String statusDetail;
    private Date statusTimestamp;
}
