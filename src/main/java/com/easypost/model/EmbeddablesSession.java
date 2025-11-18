package com.easypost.model;

import java.util.Date;
import lombok.Getter;

@Getter
public class EmbeddablesSession {
    private String object;
    private String sessionId;
    private Date createdAt;
    private Date expiresAt;
}
