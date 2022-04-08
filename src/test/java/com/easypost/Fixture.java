package com.easypost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Fixture {
    static final int PAGE_SIZE_NUMBER = 5;

    private Fixture() {
    // Prevent instantiation
    }

    /**
     * We keep the page size of retrieving `all` records small.
     *
     * @return Number of page size.
     */
    public static int pageSize() {
        return PAGE_SIZE_NUMBER;
    }

    /**
     * USPS Carrier Account ID for Java Test User.
     *
     * @return USPS Carrier Account ID.
     */
    public static String uspsCarrierAccountID() {
        return "ca_f09befdb2e9c410e95c7622ea912c18c";
    }

    /**
     * Child User ID for Java Test User.
     *
     * @return Child User ID.
     */
    public static String childUserID() {
        return "user_feab33e9b65e4ba3b41cc1f1f2b27bea";
    }

    /**
     * We use USPS as carrier in our test coverage.
     *
     * @return USPS carrier.
     */
    public static String usps() {
        return "USPS";
    }

    /**
     * We use USPS `First` service in our test coverage.
     *
     * @return USPS `First` service.
     */
    public static String uspsService() {
        return "First";
    }

    /**
     * Report start date for ReportTest.
     *
     * @return start date for report.
     */
    public static String reportStartDate() {
        return "2022-02-16";
    }

    /**
     * Report end date for ReportTest.
     *
     * @return end date for report.
     */
    public static String reportEndDate() {
        return "2022-02-18";
    }

    /**
     * Basic address that is reusable in all test coverage.
     *
     * @return A map that have the address info filled.
     */
    public static Map<String, Object> basicAddress() {
        Map<String, Object> address = new HashMap<>();

        address.put("name", "Jack Sparrow");
        address.put("company", "EasyPost");
        address.put("street1", "388 Townsend St");
        address.put("street2", "Apt 20");
        address.put("city", "San Francisco");
        address.put("state", "CA");
        address.put("zip", "94107");
        address.put("phone", "5555555555");

        return address;
    }

    /**
     * Address that is incorrect and had addtional key to verify the address.
     *
     * @return A map that have the address info filled and verify key and value.
     */
    public static Map<String, Object> incorrectAddressToVerify() {
        Map<String, Object> address = new HashMap<>();
        List<String> verificationList = new ArrayList<>();

        verificationList.add("delivery");
        address.put("verify", verificationList);
        address.put("street1", "417 montgomery street");
        address.put("street2", "FL 5");
        address.put("city", "San Francisco");
        address.put("state", "CA");
        address.put("zip", "94104");
        address.put("country", "US");
        address.put("company", "EasyPost");
        address.put("phone", "415-123-4567");

        return address;
    }

    /**
     * Pickup address that is reusable in all test coverage.
     *
     * @return A map that have the pickup address info filled.
     */
    public static Map<String, Object> pickupAddress() {
        Map<String, Object> pickupAddress = new HashMap<>();

        pickupAddress.put("name", "Dr. Steve Brule");
        pickupAddress.put("street1", "179 N Harbor Dr");
        pickupAddress.put("city", "Redondo Beach");
        pickupAddress.put("state", "CA");
        pickupAddress.put("country", "US");
        pickupAddress.put("zip", "90277");
        pickupAddress.put("phone", "3331114444");

        return pickupAddress;
    }

    /**
     * Basic parcel that is reusable in all test coverage.
     *
     * @return A map that have the parcel info filled.
     */
    public static Map<String, Object> basicParcel() {
        Map<String, Object> basicParcel = new HashMap<>();

        basicParcel.put("length", "10");
        basicParcel.put("width", "8");
        basicParcel.put("height", "4");
        basicParcel.put("weight", "15.4");

        return basicParcel;
    }

    /**
     * Basic customs item that is reusable in all test coverage.
     *
     * @return A map that have the customs item info filled.
     */
    public static Map<String, Object> basicCustomsItem() {
        Map<String, Object> customsItem = new HashMap<>();

        customsItem.put("description", "Sweet shirts");
        customsItem.put("quantity", "2");
        customsItem.put("weight", "11");
        customsItem.put("value", "23");
        customsItem.put("hs_tariff_number", "654321");
        customsItem.put("origin_country", "US");

        return customsItem;
    }

    /**
     * Basic customs Info that is reusable in all test coverage.
     *
     * @return A map that have the customs Info info filled.
     */
    public static Map<String, Object> basicCustomsInfo() {
        Map<String, Object> customsInfo = new HashMap<>();

        customsInfo.put("eel_pfc", "NOEEI 30.37(a)");
        customsInfo.put("customs_certify", true);
        customsInfo.put("customs_signer", "Steve Brule");
        customsInfo.put("contents_type", "merchandise");
        customsInfo.put("contents_explanation", "");
        customsInfo.put("restriction_type", "none");
        customsInfo.put("non_delivery_option", "return");
        customsInfo.put("custom_items", basicCustomsItem());

        return customsInfo;
    }

    /**
     * Basic tax identifier that is reusable in all test coverage.
     *
     * @return A map that have the tax identifier info filled.
     */
    public static Map<String, Object> taxIdentifier() {
        Map<String, Object> taxIdentifier = new HashMap<>();

        taxIdentifier.put("entity", "SENDER");
        taxIdentifier.put("tax_id_type", "IOSS");
        taxIdentifier.put("tax_id", "12345");
        taxIdentifier.put("issuing_country", "GB");

        return taxIdentifier;
    }

    /**
     * Basic shipment that is reusable in all test coverage.
     *
     * @return A map that have the shipment info filled.
     */
    public static Map<String, Object> basicShipment() {
        Map<String, Object> basicShipment = new HashMap<>();

        basicShipment.put("to_address", basicAddress());
        basicShipment.put("from_address", basicAddress());
        basicShipment.put("parcel", basicParcel());

        return basicShipment;
    }

    /**
     * Shipment that has all detail and is reusable in all test coverage.
     *
     * @return A map that have the shipment info filled in detail.
     */
    public static Map<String, Object> fullShipment() {
        Map<String, Object> fullShipment = new HashMap<>();
        Map<String, Object> options = new HashMap<>();

        options.put("label_format", "PNG"); // Must be PNG so we can convert to ZPL later.
        options.put("invoice_number", "123");
        fullShipment.put("to_address", basicAddress());
        fullShipment.put("from_address", basicAddress());
        fullShipment.put("parcel", basicParcel());
        fullShipment.put("customs_info", basicCustomsInfo());
        fullShipment.put("reference", "123");
        fullShipment.put("options", options);

        return fullShipment;
    }

    /**
     * Shipment with one call buy that is reusable in all test coverage.
     *
     * @return A map that have the shipment info filled for one call buy.
     */
    public static Map<String, Object> oneCallBuyShipment() {
        Map<String, Object> oneCallBuyShipment = new HashMap<>();
        List<Object> carrierAccounts = new ArrayList<>();

        carrierAccounts.add(uspsCarrierAccountID());
        oneCallBuyShipment.put("to_address", basicAddress());
        oneCallBuyShipment.put("from_address", basicAddress());
        oneCallBuyShipment.put("parcel", basicParcel());
        oneCallBuyShipment.put("service", uspsService());
        oneCallBuyShipment.put("carrier_accounts", carrierAccounts);
        oneCallBuyShipment.put("carrier", usps());

        return oneCallBuyShipment;
    }

    /**
     * Basic pickup that is reusable in all test coverage.
     *
     * @return A map that have the pickup info filled.
     */
    public static Map<String, Object> basicPickup() {
        Map<String, Object> basicPickup = new HashMap<>();

        basicPickup.put("address", basicAddress());
        basicPickup.put("min_datetime", "2022-02-23");
        basicPickup.put("max_datetime", "2022-02-24");
        basicPickup.put("instructions", "Pickup at front door");

        return basicPickup;
    }
}
