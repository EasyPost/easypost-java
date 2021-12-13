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

    public Integer getPercentile_50() {
        return percentile_50;
    }

    public void setPercentile_50(final Integer percentile_50) {
        this.percentile_50 = percentile_50;
    }

    public Integer getPercentile_75() {
        return percentile_75;
    }

    public void setPercentile_75(final Integer percentile_75) {
        this.percentile_75 = percentile_75;
    }

    public Integer getPercentile_85() {
        return percentile_85;
    }

    public void setPercentile_85(final Integer percentile_85) {
        this.percentile_85 = percentile_85;
    }

    public Integer getPercentile_90() {
        return percentile_90;
    }

    public void setPercentile_90(final Integer percentile_90) {
        this.percentile_90 = percentile_90;
    }

    public Integer getPercentile_95() {
        return percentile_95;
    }

    public void setPercentile_95(final Integer percentile_95) {
        this.percentile_95 = percentile_95;
    }

    public Integer getPercentile_97() {
        return percentile_97;
    }

    public void setPercentile_97(final Integer percentile_97) {
        this.percentile_97 = percentile_97;
    }

    public Integer getPercentile_99() {
        return percentile_99;
    }

    public void setPercentile_99(final Integer percentile_99) {
        this.percentile_99 = percentile_99;
    }
}
