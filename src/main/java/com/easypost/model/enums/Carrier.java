package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;

public enum Carrier implements EasyPostEnum {
    Amazon_Mws("AmazonMws"),
    APC("APC"),
    Asendia("Asendia"),
    Asendia_USA("AsendiaUsa"),
    Australia_Post("AustraliaPost"),
    AxlehireV3("AxlehireV3"),
    Better_Trucks("BetterTrucks"),
    Bond("Bond"),
    Cainiao("Cainiao"),
    Canada_Post("CanadaPost"),
    Canpar("Canpar"),
    CDL_Last_Mile_Solutions("ColumbusLastMile"),
    Chronopost("Chronopost"),
    CloudSort("CloudSort"),
    Courier_Express("CourierExpress"),
    CouriersPlease("CouriersPlease"),
    Dai_Post("DaiPost"),
    Deutsche_Post("DeutschePost"),
    Deutsche_Post_UK("DeutschePostUK"),
    DHL_eCommerce_Asia("DHLEcommerceAsia"),
    DHL_eCommerce_Solutions("DhlEcs"),
    DHL_Express("DHLExpress"),
    DPD("DPD"),
    DPD_UK("DPDUK"),
    ePost_Global("ePostGlobal"),
    Estafeta("Estafeta"),
    Fastway("Fastway"),
    FedEx("FedEx"),
    FedEx_Cross_Border("FedExCrossBorder"),
    FedEx_Mailview("FedExMailview"),
    FedEx_SameDay_City("FedExSameDayCity"),
    FedEx_SmartPost("FedExSmartPost"),
    FirstMile("FirstMile"),
    Globegistics("Globegistics"),
    GSO("GSO"),
    Hermes("Hermes"),
    Interlink_Express("InterlinkExpress"),
    JP_Post("JPPost"),
    Kuroneko_Yamato("KuronekoYamato"),
    La_Poste("LaPoste"),
    LaserShip("Lasership"),
    Loomis_Express("LoomisExpress"),
    LSO("LSO"),
    Newgistics("Newgistics"),
    OnTrac("OnTrac"),
    Osm_Worldwide("OsmWorldwide"),
    Parcelforce("Parcelforce"),
    Passport("Passport"),
    PCF_Final_Mile("PcfFinalMile"),
    PostNL("PostNL"),
    Purolator("Purolator"),
    Royal_Mail("RoyalMail"),
    SEKO_OmniParcel("OmniParcel"),
    SF_Express("SFExpress"),
    Spee_Dee("SpeeDee"),
    StarTrack("StarTrack"),
    TForce("TForce"),
    UDS("UDS"),
    UPS("UPS"),
    UPS_i_parcel("UPSIparcel"),
    UPS_Mail_Innovations("UPSMailInnovations"),
    USPS("USPS"),
    Veho("Veho"),
    Yanwen("Yanwen");

    private String value;

    Carrier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Carrier getEnum(String value) throws EasyPostException {
        return (Carrier) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
