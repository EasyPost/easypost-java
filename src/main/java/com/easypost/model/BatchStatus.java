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

    /**
     * Get the number of batches created.
     *
     * @return the number of batches created.
     */
    public int getCreated() {
        return created;
    }

    /**
     * Set the number of batches created.
     *
     * @param created the number of batches created.
     */
    public void setCreated(final int created) {
        this.created = created;
    }

    /**
     * Get the number of batches that failed to be created.
     *
     * @return the number of batches that failed to be created.
     */
    public int getCreationFailed() {
        return creationFailed;
    }

    /**
     * Set the number of batches that failed to be created.
     *
     * @param creationFailed the number of batches that failed to be created.
     */
    public void setCreationFailed(final int creationFailed) {
        this.creationFailed = creationFailed;
    }

    /**
     * Get the number of postage purchases.
     *
     * @return the number of postage purchases.
     */
    public int getPostagePurchased() {
        return postagePurchased;
    }

    /**
     * Set the number of postage purchases.
     *
     * @param postagePurchased the number of postage purchases.
     */
    public void setPostagePurchased(final int postagePurchased) {
        this.postagePurchased = postagePurchased;
    }

    /**
     * Get the number of postage purchases that failed.
     *
     * @return the number of postage purchases that failed.
     */
    public int getPostagePurchaseFailed() {
        return postagePurchaseFailed;
    }

    /**
     * Set the number of postage purchases that failed.
     *
     * @param postagePurchaseFailed the number of postage purchases that failed.
     */
    public void setPostagePurchaseFailed(final int postagePurchaseFailed) {
        this.postagePurchaseFailed = postagePurchaseFailed;
    }
}
