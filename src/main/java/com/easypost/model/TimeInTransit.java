package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public final class TimeInTransit {

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

    /**
     * Get the delivery accuracy of a specific percentile of this TimeInTransit.
     *
     * @param percentile the percentile to find the corresponding accuracy for
     * @return the delivery accuracy of the specified percentile
     * @throws EasyPostException when the percentile is not valid
     * @deprecated Use {@link #getBySmartrateAccuracy(SmartrateAccuracy)} instead.
     */
    @Deprecated
    public int getSmartRateAccuracy(final String percentile) throws EasyPostException {
        switch (percentile) {
            case "percentile_50":
                return this.percentile50;
            case "percentile_75":
                return this.percentile75;
            case "percentile_85":
                return this.percentile85;
            case "percentile_90":
                return this.percentile90;
            case "percentile_95":
                return this.percentile95;
            case "percentile_97":
                return this.percentile97;
            case "percentile_99":
                return this.percentile99;
            default:
                throw new EasyPostException("Invalid percentile value");
        }
    }

    /**
     * Get the delivery accuracy of a specific percentile of this TimeInTransit.
     *
     * @param accuracy the SmartrateAccuracy to find the corresponding accuracy for
     * @return the delivery accuracy of the specified percentile
     * @throws EasyPostException when the percentile is not valid
     */
    public int getBySmartrateAccuracy(SmartrateAccuracy accuracy) throws EasyPostException {
        switch (accuracy) {
            case Percentile50:
                return this.percentile50;
            case Percentile75:
                return this.percentile75;
            case Percentile85:
                return this.percentile85;
            case Percentile90:
                return this.percentile90;
            case Percentile95:
                return this.percentile95;
            case Percentile97:
                return this.percentile97;
            case Percentile99:
                return this.percentile99;
            default:
                throw new EasyPostException("Invalid SmartrateAccuracy enum value.");
        }
    }

}
