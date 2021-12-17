package com.easypost.model.enums;

import com.easypost.exception.EasyPostException;
import com.google.gson.annotations.SerializedName;

public enum Carrier implements EasyPostEnum {
    @SerializedName("AmazonMws")
    Amazon_Mws("AmazonMws"),
    @SerializedName("APC")
    APC("APC"),
    @SerializedName("Asendia")
    Asendia("Asendia"),
    @SerializedName("AsendiaUsa")
    Asendia_USA("AsendiaUsa"),
    @SerializedName("AustraliaPost")
    Australia_Post("AustraliaPost"),
    @SerializedName("AxlehireV3")
    AxlehireV3("AxlehireV3"),
    @SerializedName("BetterTrucks")
    Better_Trucks("BetterTrucks"),
    @SerializedName("Bond")
    Bond("Bond"),
    @SerializedName("Cainiao")
    Cainiao("Cainiao"),
    @SerializedName("CanadaPost")
    Canada_Post("CanadaPost"),
    @SerializedName("Canpar")
    Canpar("Canpar"),
    @SerializedName("ColumbusLastMile")
    CDL_Last_Mile_Solutions("ColumbusLastMile"),
    @SerializedName("Chronopost")
    Chronopost("Chronopost"),
    @SerializedName("CloudSort")
    CloudSort("CloudSort"),
    @SerializedName("CourrierExpress")
    Courier_Express("CourierExpress"),
    @SerializedName("CouriersPlease")
    CouriersPlease("CouriersPlease"),
    @SerializedName("DaiPost")
    Dai_Post("DaiPost"),
    @SerializedName("DeutschePost")
    Deutsche_Post("DeutschePost"),
    @SerializedName("DeutschePostUK")
    Deutsche_Post_UK("DeutschePostUK"),
    @SerializedName("DHLEcommerceAsia")
    DHL_eCommerce_Asia("DHLEcommerceAsia"),
    @SerializedName("DhlEcs")
    DHL_eCommerce_Solutions("DhlEcs"),
    @SerializedName("DHLExpress")
    DHL_Express("DHLExpress"),
    @SerializedName("DPD")
    DPD("DPD"),
    @SerializedName("DPDUK")
    DPD_UK("DPDUK"),
    @SerializedName("ePostlobal")
    ePost_Global("ePostGlobal"),
    @SerializedName("Estafeta")
    Estafeta("Estafeta"),
    @SerializedName("Fastway")
    Fastway("Fastway"),
    @SerializedName("FedEx")
    FedEx("FedEx"),
    @SerializedName("FedExCrossBorder")
    FedEx_Cross_Border("FedExCrossBorder"),
    @SerializedName("FedExMailview")
    FedEx_Mailview("FedExMailview"),
    @SerializedName("FedExSameDayCity")
    FedEx_SameDay_City("FedExSameDayCity"),
    @SerializedName("FedExSmartPost")
    FedEx_SmartPost("FedExSmartPost"),
    @SerializedName("FirstMile")
    FirstMile("FirstMile"),
    @SerializedName("Globegistics")
    Globegistics("Globegistics"),
    @SerializedName("GSO")
    GSO("GSO"),
    @SerializedName("Hermes")
    Hermes("Hermes"),
    @SerializedName("InterlinkExpress")
    Interlink_Express("InterlinkExpress"),
    @SerializedName("JPPost")
    JP_Post("JPPost"),
    @SerializedName("KuronekoYamato")
    Kuroneko_Yamato("KuronekoYamato"),
    @SerializedName("LaPoste")
    La_Poste("LaPoste"),
    @SerializedName("Lasership")
    LaserShip("Lasership"),
    @SerializedName("LoomisExpress")
    Loomis_Express("LoomisExpress"),
    @SerializedName("LSO")
    LSO("LSO"),
    @SerializedName("Newgistics")
    Newgistics("Newgistics"),
    @SerializedName("OnTrac")
    OnTrac("OnTrac"),
    @SerializedName("OsmWorldwide")
    Osm_Worldwide("OsmWorldwide"),
    @SerializedName("Parcelforce")
    Parcelforce("Parcelforce"),
    @SerializedName("Passport")
    Passport("Passport"),
    @SerializedName("PcfFinalMile")
    PCF_Final_Mile("PcfFinalMile"),
    @SerializedName("PostNL")
    PostNL("PostNL"),
    @SerializedName("Purolator")
    Purolator("Purolator"),
    @SerializedName("RoyalMail")
    Royal_Mail("RoyalMail"),
    @SerializedName("OmniParcel")
    SEKO_OmniParcel("OmniParcel"),
    @SerializedName("SFExpress")
    SF_Express("SFExpress"),
    @SerializedName("SpeeDee")
    Spee_Dee("SpeeDee"),
    @SerializedName("StarTrack")
    StarTrack("StarTrack"),
    @SerializedName("TForce")
    TForce("TForce"),
    @SerializedName("UDS")
    UDS("UDS"),
    @SerializedName("UPS")
    UPS("UPS"),
    @SerializedName("UPSIparcel")
    UPS_i_parcel("UPSIparcel"),
    @SerializedName("UPSMailInnovations")
    UPS_Mail_Innovations("UPSMailInnovations"),
    @SerializedName("USPS")
    USPS("USPS"),
    @SerializedName("Veho")
    Veho("Veho"),
    @SerializedName("Yanwen")
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
