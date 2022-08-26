package com.easypost.utils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.easypost.utils.Files.getSourceFileDirectory;
import static com.easypost.utils.Files.readFile;

/*
 * This class needs to exist in the main package (rather than the test package)
 * because this class needs to be referenced in the `HashMapSerializer`.
 */
public abstract class Fixture {

    private static String readFixtureData() {
        Path fixtureDataPath =
                Paths.get(getSourceFileDirectory(), "examples/official/fixtures/client-library-fixtures.json");
        return readFile(fixtureDataPath);
    }

    private static HashMap<String, Object> createFixture(String data) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
        return gson.fromJson(data, type);
    }

    private static HashMap<String, Object> createFixture(String... keyPath) {
        // load fixture data as a JSON string
        String fixtureData = readFixtureData();
        if (fixtureData == null) {
            return null;
        }

        // parse fixture data as a JSON object
        Gson gson = new Gson();
        Type linkedTreeMapType = new TypeToken<LinkedTreeMap<String, Object>>() {}.getType();

        // traverse the fixture data to find the requested fixture
        LinkedTreeMap<String, Object> fixture = gson.fromJson(fixtureData, linkedTreeMapType);
        for (String key : keyPath) {
            if (fixture.containsKey(key)) {
                fixture = (LinkedTreeMap<String, Object>) fixture.get(key);
            } else {
                return null;
            }
        }

        String fixtureJson = gson.toJson(fixture, linkedTreeMapType);

        Type hashMapType = new TypeToken<HashMap<String, Object>>() {}.getType();
        return gson.fromJson(fixtureJson, hashMapType);
    }

    private static int createFixtureInt(String... keyPath) {
        String lastKey = keyPath[keyPath.length - 1];
        List<String> beginningKeys = new ArrayList<>(Arrays.asList(keyPath)).subList(0, keyPath.length - 1);
        Map<String, Object> fixture = createFixture(beginningKeys.toArray(new String[0]));

        if (fixture == null) {
            // not a fan of this default value
            return 0;
        }
        if (fixture.containsKey(lastKey)) {
            return ((Double) fixture.get(lastKey)).intValue();
        }
        return 0;
    }

    private static String createFixtureString(String... keyPath) {
        String lastKey = keyPath[keyPath.length - 1];
        List<String> beginningKeys = new ArrayList<>(Arrays.asList(keyPath)).subList(0, keyPath.length - 1);
        HashMap<String, Object> fixture = createFixture(beginningKeys.toArray(new String[0]));

        if (fixture == null) {
            // not a fan of this default value
            return "";
        }
        if (fixture.containsKey(lastKey)) {
            return (String) fixture.get(lastKey);
        }
        return "";
    }

    private static HashMap<String, Object> createFixtureMap(String... keyPath) {
        return createFixture(keyPath);
    }

    /**
     * Get the default page size for the API.
     *
     * @return The default page size for the API
     */
    public static int pageSize() {
        return createFixtureInt("page_sizes", "five_results");
    }

    /**
     * Get the default USPS carrier account ID.
     *
     * @return The default USPS carrier account ID
     */
    public static String uspsCarrierAccountID() {
        // Fallback to the EasyPost Java Client Library Test User USPS carrier account
        return System.getenv("USPS_CARRIER_ACCOUNT_ID") != null ? System.getenv("USPS_CARRIER_ACCOUNT_ID") :
                "ca_f09befdb2e9c410e95c7622ea912c18c";
    }

    /**
     * Get the USPS carrier name.
     *
     * @return The USPS carrier name
     */
    public static String usps() {
        return createFixtureString("carrier_strings", "usps");
    }

    /**
     * Get the default USPS service level.
     *
     * @return The default USPS service level
     */
    public static String uspsService() {
        return createFixtureString("service_names", "usps", "first_service");
    }

    /**
     * Get the default pickup service level.
     *
     * @return The default pickup service level
     */
    public static String pickupService() {
        return createFixtureString("service_names", "usps", "pickup_service");
    }

    /**
     * Get the default report type.
     *
     * @return The default report type
     */
    public static String reportType() {
        return createFixtureString("report_types", "shipment");
    }

    /**
     * Get the default report date.
     *
     * @return The default report date
     */
    public static String reportDate() {
        return "2022-05-04";
    }

    /**
     * Get the default report prefix.
     *
     * @return The default report prefix
     */
    public static String reportIdPrefix() {
        return "shprep_";
    }

    /**
     * Get the default webhook URL.
     *
     * @return The default webhook URL
     */
    public static String webhookUrl() {
        return createFixtureString("webhook_url");
    }

    /**
     * Get the first default address.
     *
     * @return The first default address
     */
    public static HashMap<String, Object> caAddress1() {
        return createFixtureMap("addresses", "ca_address_1");
    }

    /**
     * Get the second default address.
     *
     * @return The second default address
     */
    public static HashMap<String, Object> caAddress2() {
        return createFixtureMap("addresses", "ca_address_2");
    }

    /**
     * Get the default incorrect address.
     *
     * @return The default incorrect address
     */
    public static HashMap<String, Object> incorrectAddress() {
        return createFixtureMap("addresses", "incorrect");
    }

    /**
     * Get the default parcel.
     *
     * @return The default parcel
     */
    public static HashMap<String, Object> basicParcel() {
        return createFixtureMap("parcels", "basic");
    }

    /**
     * Get the default customs item.
     *
     * @return The default customs item
     */
    public static HashMap<String, Object> basicCustomsItem() {
        return createFixtureMap("customs_items", "basic");
    }

    /**
     * Get the default customs info.
     *
     * @return The default customs info
     */
    public static HashMap<String, Object> basicCustomsInfo() {
        return createFixtureMap("customs_infos", "basic");
    }

    /**
     * Get the default tax identifier.
     *
     * @return The default tax identifier
     */
    public static HashMap<String, Object> taxIdentifier() {
        return createFixtureMap("tax_identifiers", "basic");
    }

    /**
     * Get the default shipment.
     *
     * @return The default shipment
     */
    public static HashMap<String, Object> basicShipment() {
        return createFixtureMap("shipments", "basic_domestic");
    }

    /**
     * Get the default full shipment.
     *
     * @return The default full shipment
     */
    public static HashMap<String, Object> fullShipment() {
        return createFixtureMap("shipments", "full");
    }

    /**
     * Get the default one-call-buy shipment.
     *
     * @return The default one-call-buy shipment
     */
    public static HashMap<String, Object> oneCallBuyShipment() {
        return new HashMap<String, Object>() {{
            put("to_address", caAddress1());
            put("from_address", caAddress2());
            put("parcel", basicParcel());
            put("service", uspsService());
            put("carrier_accounts", new ArrayList<String>() {{
                add(uspsCarrierAccountID());
            }});
            put("carrier", usps());
        }};
    }

    /**
     * Get the default pickup.
     *
     * @return The default pickup
     */
    public static HashMap<String, Object> basicPickup() {
        HashMap<String, Object> fixture = createFixtureMap("pickups", "basic");

        /*
        If you need to re-record cassettes, increment the date below and ensure it is one day in the future,
        USPS only does "next-day" pickups including Saturday but not Sunday or Holidays.
         */
        String pickupDate = "2022-08-30";

        fixture.put("min_datetime", pickupDate);
        fixture.put("max_datetime", pickupDate);

        return fixture;
    }

    /**
     * Get the default carrier account.
     *
     * @return The default carrier account
     */
    public static HashMap<String, Object> basicCarrierAccount() {
        return createFixtureMap("carrier_accounts", "basic");
    }

    /**
     * Get the default insurance.
     *
     * @return The default insurance
     */
    public static HashMap<String, Object> basicInsurance() {
        /*
        This fixture will require you to append a `tracking_code` key with the shipment's tracking code.
         */
        return createFixtureMap("insurances", "basic");
    }

    /**
     * Get the default order.
     *
     * @return The default order
     */
    public static HashMap<String, Object> basicOrder() {
        return createFixtureMap("orders", "basic");
    }

    /**
     * Get the bytes of the default event webhook body.
     *
     * @return The bytes of the default event webhook body
     */
    public static byte[] eventBytes() {
        String relativeFilePath = "examples/official/fixtures/event-body.json";
        String fullFilePath = Paths.get(getSourceFileDirectory(), relativeFilePath).toString();
        byte[] data = null;

        try {
            data = Files.readAllLines(Paths.get(fullFilePath), StandardCharsets.UTF_8).get(0).getBytes();
        } catch (IOException error) {
            error.printStackTrace();
        }

        return data;
    }

    /**
     * Get the default credit card details.
     *
     * @return The default credit card details
     */
    public static HashMap<String, Object> creditCardDetails() {
        /*
        The credit card details below are for a valid proxy card usable for tests only
        and cannot be used for real transactions.
        DO NOT alter these details with real credit card information.
         */
        return createFixtureMap("credit_cards", "test");
    }

    /**
     * Get the default RMA form options.
     *
     * @return The default RMA form options
     */
    public static HashMap<String, Object> rmaFormOptions() {
        return createFixtureMap("form_options", "rma");
    }

    /**
     * Get the default referral user.
     *
     * @return The default referral user
     */
    public static HashMap<String, Object> referralUser() {
        return createFixtureMap("users", "referral");
    }
}
