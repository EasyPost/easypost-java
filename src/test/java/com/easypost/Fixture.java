package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Fixture {
    public static final int PAGE_SIZE = 5;

    /**
     * A basic carrier account data structure.
     *
     * @return a map with the carrier account data filled in.
     */
    public static Map<String, Object> basicCarrierAccount() {
        Map<String, Object> carrierAccount = new HashMap<>();
        Map<String, Object> credentials = new HashMap<>();

        credentials.put("account_number", "A1A1A1");
        credentials.put("user_id", "USERID");
        credentials.put("password", "PASSWORD");
        credentials.put("access_license_number", "ALN");
        carrierAccount.put("type", "UpsAccount");
        carrierAccount.put("credentials", credentials);

        return carrierAccount;
    }

    /**
     * A basic insurance data structure
     * This fixture will require you to add a `tracking_code` key with the tracking
     * code of a shipment.
     *
     * @return a map with the insurance data filled in.
     */
    public static Map<String, Object> basicInsurance() throws EasyPostException {
        Shipment shipment = Shipment.create(oneCallBuyShipment());

        Map<String, Object> insurance = new HashMap<>();

        insurance.put("to_address", Fixture.basicAddress());
        insurance.put("from_address", Fixture.basicAddress());
        insurance.put("carrier", Fixture.usps());
        insurance.put("amount", "100");
        insurance.put("tracking_code", shipment.getTrackingCode());

        return insurance;
    }

    /**
     * Shipment with one call buy that is reusable in all test coverage.
     *
     * @return A map that has the shipment info filled for one call buy.
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
     * Basic address that is reusable in all test coverage.
     *
     * @return A map that has the address info filled.
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
     * We use USPS as carrier in our test coverage.
     *
     * @return USPS carrier.
     */
    public static String usps() {
        return "USPS";
    }

    /**
     * USPS Carrier Account ID.
     *
     * @return USPS Carrier Account ID.
     */
    public static String uspsCarrierAccountID() {
        // Fallback to the EasyPost Java Client Library Test User USPS carrier account
        return System.getenv("USPS_CARRIER_ACCOUNT_ID") != null ? System.getenv("USPS_CARRIER_ACCOUNT_ID")
                : "ca_f09befdb2e9c410e95c7622ea912c18c";
    }

    /**
     * Basic parcel that is reusable in all test coverage.
     *
     * @return A map that has the parcel info filled.
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
     * We use USPS `First` service in our test coverage.
     *
     * @return USPS `First` service.
     */
    public static String uspsService() {
        return "First";
    }

    /**
     * A basic order data structure.
     *
     * @return a map with the insurance data filled in.
     */
    public static Map<String, Object> basicOrder() {
        Map<String, Object> order = new HashMap<>();
        List<Object> shipments = new ArrayList<>();

        shipments.add(Fixture.basicShipment());

        order.put("to_address", Fixture.basicAddress());
        order.put("from_address", Fixture.basicAddress());
        order.put("shipments", shipments);

        return order;
    }

    /**
     * Basic shipment that is reusable in all test coverage.
     *
     * @return A map that has the shipment info filled.
     */
    public static Map<String, Object> basicShipment() {
        Map<String, Object> basicShipment = new HashMap<>();

        basicShipment.put("to_address", basicAddress());
        basicShipment.put("from_address", basicAddress());
        basicShipment.put("parcel", basicParcel());

        return basicShipment;
    }

    /**
     * This fixture will require you to add a `shipment` key with a Shipment object
     * from a test.
     * If you need to re-record cassettes, increment the date below and ensure it is
     * one day in the future,
     * USPS only does "next-day" pickups including Saturday but not Sunday or
     * Holidays.
     *
     * @return A map that has the pickup.
     */
    public static Map<String, Object> basicPickup() {
        Map<String, Object> basicPickup = new HashMap<>();

        String pickupDate = "2022-07-28";

        basicPickup.put("address", basicAddress());
        basicPickup.put("min_datetime", pickupDate);
        basicPickup.put("max_datetime", pickupDate);
        basicPickup.put("instructions", "Pickup at front door");

        return basicPickup;
    }

    /**
     * Basic credit card that is reusable in all test coverage.
     *
     * @return A map that has the credit card info filled.
     */
    public static Map<String, String> creditCardDetails() {
        // The credit card details below are for a valid proxy card usable
        // for tests only and cannot be used for real transactions.
        // DO NOT alter these details with real credit card information.
        Map<String, String> creditCardDetails = new HashMap<String, String>();

        creditCardDetails.put("number", "4536410136126170");
        creditCardDetails.put("expiration_month", "05");
        creditCardDetails.put("expiration_year", "2028");
        creditCardDetails.put("cvc", "778");

        return creditCardDetails;
    }

    /**
     * Basic EndShipper address that is reusable in all test coverage.
     *
     * @return A map that has the EndShipper address info filled.
     */
    public static Map<String, Object> endShipperAddress() {
        Map<String, Object> address = new HashMap<>();

        address.put("name", "Jack Sparrow");
        address.put("company", "EasyPost");
        address.put("street1", "388 Townsend St");
        address.put("street2", "Apt 20");
        address.put("city", "San Francisco");
        address.put("state", "CA");
        address.put("zip", "94107");
        address.put("phone", "5555555555");
        address.put("country", "US");
        address.put("email", "test@example.com");

        return address;
    }

    /**
     * Shipment that has all detail and is reusable in all test coverage.
     *
     * @return A map that has the shipment info filled in detail.
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
     * Basic customs Info that is reusable in all test coverage.
     *
     * @return A map that has the customs Info info filled.
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
     * Basic customs item that is reusable in all test coverage.
     *
     * @return A map that has the customs item info filled.
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
     * Address that is incorrect.
     *
     * @return A map that has the address info incorrectly filled
     */
    public static Map<String, Object> incorrectAddressToVerify() {
        Map<String, Object> address = new HashMap<>();

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
     * We keep the page size of retrieving `all` records small.
     *
     * @return Number of page size.
     */
    public static int pageSize() {
        return PAGE_SIZE;
    }

    /**
     * Pickup address that is reusable in all test coverage.
     *
     * @return A map that has the pickup address info filled.
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
     * The pickup service to use.
     *
     * @return USPS `NextDay` service.
     */
    public static String pickupService() {
        return "NextDay";
    }

    /**
     * Basic Referral user that is reusable in all test coverage.
     *
     * @return A map that has the Referral detail info filled.
     */
    public static Map<String, Object> referralUser() {
        Map<String, Object> referralUser = new HashMap<>();

        referralUser.put("name", "test test");
        referralUser.put("email", "test@test.com");
        referralUser.put("phone", "8888888888");

        return referralUser;
    }

    /**
     * Report start date for ReportTest.
     *
     * @return start date for report.
     */
    public static String reportDate() {
        return "2022-04-12";
    }

    /**
     * Report type.
     *
     * @return report type.
     */
    public static String reportType() {
        return "shipment";
    }

    /**
     * Basic tax identifier that is reusable in all test coverage.
     *
     * @return A map that has the tax identifier info filled.
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
     * Webhook URL.
     *
     * @return webhook URL.
     */
    public static String webhookUrl() {
        return "http://example.com";
    }

    /**
     * RMA form options.
     *
     * @return RMA form options.
     */
    public static Map<String, Object> rmaFormOptions() {
        final int units = 8;
        Map<String, Object> params = new HashMap<String, Object>() {
            {
                put("barcode", "RMA12345678900");
                put("line_items", new HashMap<String, Object>() {
                    {
                        put("units", units);
                        put("product", new HashMap<String, Object>() {
                            {
                                put("title", "Square Reader");
                                put("barcode", "855658003251");
                            }
                        });
                    }
                });
            }
        };

        return params;
    }

    /**
     * Basic shipment with different to-from addresses that is reusable in all test coverage.
     *
     * @return A map that has the shipment info filled.
     */
    public static Map<String, Object> basicCarbonOffsetShipment() {
        Map<String, Object> basicShipment = new HashMap<>();

        basicShipment.put("to_address", pickupAddress());
        basicShipment.put("from_address", basicAddress());
        basicShipment.put("parcel", basicParcel());

        return basicShipment;
    }

    /**
     * Shipment that has all detail and different to-from addresses that is reusable in all test coverage.
     *
     * @return A map that has the shipment info filled in detail.
     */
    public static Map<String, Object> fullCarbonOffsetShipment() {
        Map<String, Object> fullShipment = new HashMap<>();
        Map<String, Object> options = new HashMap<>();

        options.put("label_format", "PNG"); // Must be PNG so we can convert to ZPL later.
        options.put("invoice_number", "123");
        fullShipment.put("to_address", pickupAddress());
        fullShipment.put("from_address", basicAddress());
        fullShipment.put("parcel", basicParcel());
        fullShipment.put("customs_info", basicCustomsInfo());
        fullShipment.put("reference", "123");
        fullShipment.put("options", options);

        return fullShipment;
    }

    /**
     * Shipment with one call buy and different to-from addresses that is reusable in all test coverage.
     *
     * @return A map that has the shipment info filled for one call buy.
     */
    public static Map<String, Object> oneCallBuyCarbonOffsetShipment() {
        Map<String, Object> oneCallBuyShipment = new HashMap<>();
        List<Object> carrierAccounts = new ArrayList<>();

        carrierAccounts.add(uspsCarrierAccountID());
        oneCallBuyShipment.put("to_address", pickupAddress());
        oneCallBuyShipment.put("from_address", basicAddress());
        oneCallBuyShipment.put("parcel", basicParcel());
        oneCallBuyShipment.put("service", uspsService());
        oneCallBuyShipment.put("carrier_accounts", carrierAccounts);
        oneCallBuyShipment.put("carrier", usps());

        return oneCallBuyShipment;
    }
}
