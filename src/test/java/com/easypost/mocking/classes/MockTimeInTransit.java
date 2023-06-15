package com.easypost.mocking.classes;

import com.google.gson.annotations.SerializedName;

public class MockTimeInTransit {
    @SerializedName ("percentile_50")
    private Integer percentile50;
    @SerializedName ("percentile_75")
    private Integer percentile75;
    @SerializedName ("percentile_85")
    private Integer percentile85;
    @SerializedName ("percentile_90")
    private Integer percentile90;
    @SerializedName ("percentile_95")
    private Integer percentile95;
    @SerializedName ("percentile_97")
    private Integer percentile97;
    @SerializedName ("percentile_99")
    private Integer percentile99;

    /**
     * Construct a new MockTimeInTransit to serialize into JSON.
     *
     * @param percentile50 The percentile50 to use.
     * @param percentile75 The percentile75 to use.
     * @param percentile85 The percentile85 to use.
     * @param percentile90 The percentile90 to use.
     * @param percentile95 The percentile95 to use.
     * @param percentile97 The percentile97 to use.
     * @param percentile99 The percentile99 to use.
     */
    public MockTimeInTransit(Integer percentile50, Integer percentile75, Integer percentile85, Integer percentile90,
                             Integer percentile95, Integer percentile97, Integer percentile99) {
        this.percentile50 = percentile50;
        this.percentile75 = percentile75;
        this.percentile85 = percentile85;
        this.percentile90 = percentile90;
        this.percentile95 = percentile95;
        this.percentile97 = percentile97;
        this.percentile99 = percentile99;
    }
}
