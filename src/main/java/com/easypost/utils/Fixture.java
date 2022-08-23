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
 * This class needs to exist in the main package (rather than the test package) because this class needs to be referenced in the `HashMapSerializer`.
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

    public static int pageSize() {
        return createFixtureInt("page_sizes", "five_results");
    }

    public static String uspsCarrierAccountID() {
        // Fallback to the EasyPost Java Client Library Test User USPS carrier account
        return System.getenv("USPS_CARRIER_ACCOUNT_ID") != null ? System.getenv("USPS_CARRIER_ACCOUNT_ID") :
                "ca_f09befdb2e9c410e95c7622ea912c18c";
    }

    public static String usps() {
        return createFixtureString("carrier_strings", "usps");
    }

    public static String uspsService() {
        return createFixtureString("service_names", "usps", "first_service");
    }

    public static String pickupService() {
        return createFixtureString("service_names", "usps", "pickup_service");
    }

    public static String pickupDate() {
        /*
        If you need to re-record cassettes, increment the date below and ensure it is one day in the future,
        USPS only does "next-day" pickups including Saturday but not Sunday or Holidays.
         */
        return "2022-08-30";
    }

    public static String reportType() {
        return createFixtureString("report_types", "shipment");
    }

    public static String reportDate() {
        return "2022-05-04";
    }

    public static String reportIdPrefix() {
        return "shprep_";
    }

    public static String webhookUrl() {
        return createFixtureString("webhook_url");
    }

    public static HashMap<String, Object> caAddress1() {
        return createFixtureMap("addresses", "ca_address_1");
    }

    public static HashMap<String, Object> caAddress2() {
        return createFixtureMap("addresses", "ca_address_2");
    }

    public static HashMap<String, Object> incorrectAddress() {
        return createFixtureMap("addresses", "incorrect");
    }

    public static HashMap<String, Object> basicParcel() {
        return createFixtureMap("parcels", "basic");
    }

    public static HashMap<String, Object> basicCustomsItem() {
        return createFixtureMap("customs_items", "basic");
    }

    public static HashMap<String, Object> basicCustomsInfo() {
        return createFixtureMap("customs_infos", "basic");
    }

    public static HashMap<String, Object> taxIdentifier() {
        return createFixtureMap("tax_identifiers", "basic");
    }

    public static HashMap<String, Object> basicShipment() {
        return createFixtureMap("shipments", "basic_domestic");
    }

    public static HashMap<String, Object> fullShipment() {
        return createFixtureMap("shipments", "full");
    }

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

    public static HashMap<String, Object> basicPickup() {
        HashMap<String, Object> fixture = createFixtureMap("pickups", "basic");

        fixture.put("min_datetime", pickupDate());
        fixture.put("max_datetime", pickupDate());

        return fixture;
    }

    public static HashMap<String, Object> basicCarrierAccount() {
        return createFixtureMap("carrier_accounts", "basic");
    }

    public static HashMap<String, Object> basicInsurance() {
        /*
        This fixture will require you to append a `tracking_code` key with the shipment's tracking code.
         */
        return createFixtureMap("insurances", "basic");
    }

    public static HashMap<String, Object> basicOrder() {
        return createFixtureMap("orders", "basic");
    }

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

    public static HashMap<String, Object> creditCardDetails() {
        /*
        The credit card details below are for a valid proxy card usable for tests only and cannot be used for real transactions.
        DO NOT alter these details with real credit card information.
         */
        return createFixtureMap("credit_cards", "test");
    }

    public static HashMap<String, Object> rmaFormOptions() {
        return createFixtureMap("form_options", "rma");
    }

    public static HashMap<String, Object> referralUser() {
        return createFixtureMap("users", "referral");
    }
}
