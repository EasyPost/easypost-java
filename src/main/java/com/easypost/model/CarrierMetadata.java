package com.easypost.model;

import java.util.List;

import lombok.Getter;

@Getter
public class CarrierMetadata extends EasyPostResource {
    private List<Carrier> carriers;
    
    @Getter
    public static class Carrier {
        private String name;
        private String humanReadable;
        private List<PredefinedPackage> predefinedPackages;
        private List<ServiceLevels> serviceLevels;
        private List<ShipmentOption> shipmentOptions;
        private List<SupportedFeatures> supportedFeatures;
    }

    @Getter
    public static class PredefinedPackage {
        private String carrier;
        private String description;
        private List<String> dimensions;
        private String humanReadable;
        private String maxWeight;
        private String name;
    }

    @Getter
    public static class ServiceLevels {
        private String carrier;
        private String description;
        private List<String> dimensions;
        private String humanReadable;
        private String maxWeight;
        private String name;
    }

    @Getter
    public static class ShipmentOption {
        private String carrier;
        private boolean deprecated;
        private String description;
        private String humanReadable;
        private String name;
        private String type;
    }

    @Getter
    public static class SupportedFeatures {
        private String carrier;
        private String description;
        private String name;
        private String supported;
    }
}
