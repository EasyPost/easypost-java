package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class CustomerPortalAccountLink {
    private String object;
    private String link;
    private Date createdAt;
    private Date expiresAt;
}
