/**
 * BatchStatus.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class BatchStatus {
    private int created;
    private int creationFailed;
    private int postagePurchased;
    private int postagePurchaseFailed;

    public int getCreated() {
        return created;
    }

    public void setCreated(final int created) {
        this.created = created;
    }

    public int getCreationFailed() {
        return creationFailed;
    }

    public void setCreationFailed(final int creationFailed) {
        this.creationFailed = creationFailed;
    }

    public int getPostagePurchased() {
        return postagePurchased;
    }

    public void setPostagePurchased(final int postagePurchased) {
        this.postagePurchased = postagePurchased;
    }

    public int getPostagePurchaseFailed() {
        return postagePurchaseFailed;
    }

    public void setPostagePurchaseFailed(final int postagePurchaseFailed) {
        this.postagePurchaseFailed = postagePurchaseFailed;
    }
}
