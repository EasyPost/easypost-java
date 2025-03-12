package com.easypost.model;

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
                throw new InvalidParameterError("accuracy");
        }
    }

}
