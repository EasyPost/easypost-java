/**
 * TimeInTransit.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class TimeInTransit {
    private Integer percentile_50;
    private Integer percentile_75;
    private Integer percentile_85;
    private Integer percentile_90;
    private Integer percentile_95;
    private Integer percentile_97;
    private Integer percentile_99;

    /**
     * Get the 50th percentile of this TimeInTransit.
     *
     * @return the 50th percentile of this TimeInTransit
     */
    public Integer getPercentile_50() {
        return percentile_50;
    }

    /**
     * Set the 50th percentile of this TimeInTransit.
     *
     * @param percentile_50 the 50th percentile of this TimeInTransit
     */
    public void setPercentile_50(final Integer percentile_50) {
        this.percentile_50 = percentile_50;
    }

    /**
     * Get the 75th percentile of this TimeInTransit.
     *
     * @return the 75th percentile of this TimeInTransit
     */
    public Integer getPercentile_75() {
        return percentile_75;
    }

    /**
     * Get the 75th percentile of this TimeInTransit.
     *
     * @param percentile_75 the 75th percentile of this TimeInTransit
     */
    public void setPercentile_75(final Integer percentile_75) {
        this.percentile_75 = percentile_75;
    }

    /**
     * Get the 85th percentile of this TimeInTransit.
     *
     * @return the 85th percentile of this TimeInTransit
     */
    public Integer getPercentile_85() {
        return percentile_85;
    }

    /**
     * Get the 85th percentile of this TimeInTransit.
     *
     * @param percentile_85 the 85th percentile of this TimeInTransit
     */
    public void setPercentile_85(final Integer percentile_85) {
        this.percentile_85 = percentile_85;
    }

    /**
     * Get the 90th percentile of this TimeInTransit.
     *
     * @return the 90th percentile of this TimeInTransit
     */
    public Integer getPercentile_90() {
        return percentile_90;
    }

    /**
     * Set the 90th percentile of this TimeInTransit.
     *
     * @param percentile_90 the 90th percentile of this TimeInTransit
     */
    public void setPercentile_90(final Integer percentile_90) {
        this.percentile_90 = percentile_90;
    }

    /**
     * Get the 95th percentile of this TimeInTransit.
     *
     * @return the 95th percentile of this TimeInTransit
     */
    public Integer getPercentile_95() {
        return percentile_95;
    }

    /**
     * Set the 95th percentile of this TimeInTransit.
     *
     * @param percentile_95 the 95th percentile of this TimeInTransit
     */
    public void setPercentile_95(final Integer percentile_95) {
        this.percentile_95 = percentile_95;
    }

    /**
     * Get the 97th percentile of this TimeInTransit.
     *
     * @return the 97th percentile of this TimeInTransit
     */
    public Integer getPercentile_97() {
        return percentile_97;
    }

    /**
     * Set the 97th percentile of this TimeInTransit.
     *
     * @param percentile_97 the 97th percentile of this TimeInTransit
     */
    public void setPercentile_97(final Integer percentile_97) {
        this.percentile_97 = percentile_97;
    }

    /**
     * Get the 99th percentile of this TimeInTransit.
     *
     * @return the 99th percentile of this TimeInTransit
     */
    public Integer getPercentile_99() {
        return percentile_99;
    }

    /**
     * Set the 99th percentile of this TimeInTransit.
     *
     * @param percentile_99 the 99th percentile of this TimeInTransit
     */
    public void setPercentile_99(final Integer percentile_99) {
        this.percentile_99 = percentile_99;
    }
}
