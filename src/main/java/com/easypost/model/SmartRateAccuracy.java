package com.easypost.model;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidParameterError;

import lombok.Getter;

@Getter
public enum SmartRateAccuracy {
    Percentile50("percentile_50"),
    Percentile75("percentile_75"),
    Percentile85("percentile_85"),
    Percentile90("percentile_90"),
    Percentile95("percentile_95"),
    Percentile97("percentile_97"),
    Percentile99("percentile_99");

    private final String keyName;

    /**
     * Constructor.
     *
     * @param keyName the internal key name
     */
    SmartRateAccuracy(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Get the enum value for a given internal key name.
     *
     * @param keyName the internal key name
     * @return the enum value
     * @throws EasyPostException if the key name is not found
     */
    public static SmartRateAccuracy getByKeyName(String keyName) throws EasyPostException {
        for (SmartRateAccuracy smartrateAccuracy : values()) {
            if (smartrateAccuracy.getKeyName().equals(keyName)) {
                return smartrateAccuracy;
            }
        }
        throw new InvalidParameterError("smartrateAccuracy");
    }
}
