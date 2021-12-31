/**
 * TimeInTransit.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class TimeInTransit {
    private Integer percentile50;
    private Integer percentile75;
    private Integer percentile85;
    private Integer percentile90;
    private Integer percentile95;
    private Integer percentile97;
    private Integer percentile99;

    /**
     * Get the 50th percentile of this TimeInTransit.
     *
     * @return the 50th percentile of this TimeInTransit
     */
    public Integer getPercentile50() {
        return percentile50;
    }

    /**
     * Set the 50th percentile of this TimeInTransit.
     *
     * @param percentile50 the 50th percentile of this TimeInTransit
     */
    public void setPercentile50(final Integer percentile50) {
        this.percentile50 = percentile50;
    }

    /**
     * Get the 75th percentile of this TimeInTransit.
     *
     * @return the 75th percentile of this TimeInTransit
     */
    public Integer getPercentile75() {
        return percentile75;
    }

    /**
     * Get the 75th percentile of this TimeInTransit.
     *
     * @param percentile75 the 75th percentile of this TimeInTransit
     */
    public void setPercentile75(final Integer percentile75) {
        this.percentile75 = percentile75;
    }

    /**
     * Get the 85th percentile of this TimeInTransit.
     *
     * @return the 85th percentile of this TimeInTransit
     */
    public Integer getPercentile85() {
        return percentile85;
    }

    /**
     * Get the 85th percentile of this TimeInTransit.
     *
     * @param percentile85 the 85th percentile of this TimeInTransit
     */
    public void setPercentile85(final Integer percentile85) {
        this.percentile85 = percentile85;
    }

    /**
     * Get the 90th percentile of this TimeInTransit.
     *
     * @return the 90th percentile of this TimeInTransit
     */
    public Integer getPercentile90() {
        return percentile90;
    }

    /**
     * Set the 90th percentile of this TimeInTransit.
     *
     * @param percentile90 the 90th percentile of this TimeInTransit
     */
    public void setPercentile90(final Integer percentile90) {
        this.percentile90 = percentile90;
    }

    /**
     * Get the 95th percentile of this TimeInTransit.
     *
     * @return the 95th percentile of this TimeInTransit
     */
    public Integer getPercentile95() {
        return percentile95;
    }

    /**
     * Set the 95th percentile of this TimeInTransit.
     *
     * @param percentile95 the 95th percentile of this TimeInTransit
     */
    public void setPercentile95(final Integer percentile95) {
        this.percentile95 = percentile95;
    }

    /**
     * Get the 97th percentile of this TimeInTransit.
     *
     * @return the 97th percentile of this TimeInTransit
     */
    public Integer getPercentile97() {
        return percentile97;
    }

    /**
     * Set the 97th percentile of this TimeInTransit.
     *
     * @param percentile97 the 97th percentile of this TimeInTransit
     */
    public void setPercentile97(final Integer percentile97) {
        this.percentile97 = percentile97;
    }

    /**
     * Get the 99th percentile of this TimeInTransit.
     *
     * @return the 99th percentile of this TimeInTransit
     */
    public Integer getPercentile99() {
        return percentile99;
    }

    /**
     * Set the 99th percentile of this TimeInTransit.
     *
     * @param percentile99 the 99th percentile of this TimeInTransit
     */
    public void setPercentile99(final Integer percentile99) {
        this.percentile99 = percentile99;
    }
}
