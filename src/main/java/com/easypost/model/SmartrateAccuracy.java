package com.easypost.model;

public enum SmartrateAccuracy {
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
    SmartrateAccuracy(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Get the internal key name for this enum value.
     *
     * @return the internal key name
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * Get the enum value for a given internal key name.
     *
     * @param keyName the internal key name
     * @return the enum value
     * @throws IllegalArgumentException if the key name is not found
     */
    public static SmartrateAccuracy getByKeyName(String keyName) throws IllegalArgumentException {
        for (SmartrateAccuracy smartrateAccuracy : values()) {
            if (smartrateAccuracy.getKeyName().equals(keyName)) {
                return smartrateAccuracy;
            }
        }
        throw new IllegalArgumentException("Invalid SmartrateAccuracy key name.");
    }
}
