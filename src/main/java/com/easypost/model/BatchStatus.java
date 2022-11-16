package com.easypost.model;

import lombok.Getter;

@Getter
public final class BatchStatus {
    private int created;
    private int creationFailed;
    private int postagePurchased;
    private int postagePurchaseFailed;
}
