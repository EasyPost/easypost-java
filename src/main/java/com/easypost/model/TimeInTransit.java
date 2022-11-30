package com.easypost.model;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidParameterError;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public final class TimeInTransit {

    @SerializedName("percentile_50")
    private Integer percentile50;
    @SerializedName("percentile_75")
    private Integer percentile75;
    @SerializedName("percentile_85")
    private Integer percentile85;
    @SerializedName("percentile_90")
    private Integer percentile90;
    @SerializedName("percentile_95")
    private Integer percentile95;
    @SerializedName("percentile_97")
    private Integer percentile97;
    @SerializedName("percentile_99")
    private Integer percentile99;

    /**
     * Get the delivery accuracy of a specific percentile of this TimeInTransit.
     *
     * @param percentile the percentile to find the corresponding accuracy for
     * @return the delivery accuracy of the specified percentile
     * @throws EasyPostException when the percentile is not valid
     * @deprecated Use {@link #getBySmartrateAccuracy(SmartrateAccuracy)} instead.
     * Deprecated: v5.5.0 - v7.0.0
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
                throw new InvalidParameterError(String.format(Constants.ErrorMessages.INVALID_PARAMETER, "percentile"));
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
