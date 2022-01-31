package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.AddressVerification;
import com.easypost.model.Batch;
import com.easypost.model.CustomsItem;
import com.easypost.model.Fee;
import com.easypost.model.Insurance;
import com.easypost.model.InsuranceCollection;
import com.easypost.model.Order;
import com.easypost.model.Pickup;
import com.easypost.model.PostageLabel;
import com.easypost.model.Rate;
import com.easypost.model.Report;
import com.easypost.model.ReportCollection;
import com.easypost.model.ScanForm;
import com.easypost.model.ScanFormCollection;
import com.easypost.model.Shipment;
import com.easypost.model.TaxIdentifier;
import com.easypost.model.TimeInTransit;
import com.easypost.model.Tracker;
import com.easypost.model.TrackerCollection;
import com.easypost.model.TrackingDetail;
import com.easypost.model.TrackingLocation;
import com.easypost.model.Webhook;
import com.easypost.model.WebhookCollection;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EasyPostTest {

    static Map<String, Object> defaultFromAddress = new HashMap<String, Object>();
    static Map<String, Object> defaultToAddress = new HashMap<String, Object>();
    static Map<String, Object> defaultParcel = new HashMap<String, Object>();
    static Map<String, Object> defaultCustomsItem = new HashMap<String, Object>();
    static Map<String, Object> defaultCustomsInfo = new HashMap<String, Object>();
    static Map<String, Object> canadaToAddress = new HashMap<String, Object>();
    @Rule
    public ExpectedException verifyStrictException = ExpectedException.none();
    @Rule
    public ExpectedException exception = ExpectedException.none();

    static Shipment createDefaultShipmentDomestic() throws EasyPostException {
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        Shipment shipment = Shipment.create(shipmentMap);
        return shipment;
    }

    static Map<String, Object> orderShipment() throws EasyPostException {
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("parcel", defaultParcel);
        return shipmentMap;
    }

    @BeforeClass
    public static void setUp() {
        EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi"; // easypost public test key

        defaultFromAddress.put("name", "EasyPost");
        defaultFromAddress.put("street1", "164 Townsend St");
        defaultFromAddress.put("street2", "Unit 1");
        defaultFromAddress.put("city", "San Francisco");
        defaultFromAddress.put("state", "CA");
        defaultFromAddress.put("zip", "94107");
        defaultFromAddress.put("phone", "415-456-7890");

        defaultToAddress.put("company", "Airport Shipping");
        defaultToAddress.put("street1", "601 Brasilia Avenue");
        defaultToAddress.put("city", "Kansas City");
        defaultToAddress.put("state", "MO");
        defaultToAddress.put("zip", "64153");
        defaultToAddress.put("phone", "314-924-0383");

        defaultParcel.put("length", 10.8);
        defaultParcel.put("width", 8.3);
        defaultParcel.put("height", 6);
        defaultParcel.put("weight", 10);

        defaultCustomsItem.put("description", "Item descrpition");
        defaultCustomsItem.put("origin_country", "US");
        defaultCustomsItem.put("quantity", 1);
        defaultCustomsItem.put("value", 10.50);
        defaultCustomsItem.put("weight", 9.9);
        defaultCustomsItem.put("code", "123");
        defaultCustomsItem.put("currency", "USD");

        List<Map<String, Object>> customsItems = new ArrayList<Map<String, Object>>();
        customsItems.add(defaultCustomsItem);
        defaultCustomsInfo.put("customs_certify", true);
        defaultCustomsInfo.put("customs_signer", "Shipping Manager");
        defaultCustomsInfo.put("contents_type", "merchandise");
        defaultCustomsInfo.put("non_delivery_option", "return");
        defaultCustomsInfo.put("restriction_type", "none");
        defaultCustomsInfo.put("customs_items", customsItems);

        canadaToAddress.put("company", "Canada Receiving");
        canadaToAddress.put("street1", "1 Larkspur Cres");
        canadaToAddress.put("city", "St. Albert");
        canadaToAddress.put("state", "AB");
        canadaToAddress.put("zip", "t8n2m4");
        canadaToAddress.put("country", "CA");
    }

    @Test
    public void testShipmentWithTracker() throws EasyPostException {
        // create and buy shipment
        Shipment shipment = createDefaultShipmentDomestic();
        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));

        assertNotNull(shipment.getTracker());
    }

    @Test
    public void testSmartrate() throws EasyPostException {
        // create and buy shipment
        Shipment shipment = createDefaultShipmentDomestic();

        List<Rate> rates = shipment.getSmartrates();
        assertTrue(rates.size() > 0);

        Rate firstRate = rates.get(0);
        TimeInTransit timeInTransit = firstRate.getTimeInTransit();

        assertNotNull(timeInTransit);
        // TODO: assert on exact values once we have a VCR libary integrated
        assertNotNull(timeInTransit.getPercentile50());
        assertNotNull(timeInTransit.getPercentile75());
        assertNotNull(timeInTransit.getPercentile85());
        assertNotNull(timeInTransit.getPercentile90());
        assertNotNull(timeInTransit.getPercentile95());
        assertNotNull(timeInTransit.getPercentile97());
        assertNotNull(timeInTransit.getPercentile99());
    }

    @Test
    public void testShipmentWithPostageLabelWithOptions() throws EasyPostException {
        // create and buy shipment
        Map<String, Object> optionsMap = new HashMap<String, Object>();
        optionsMap.put("label_format", "ZPL");
        optionsMap.put("label_size", "4x6");

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("options", optionsMap);
        Shipment shipment = Shipment.create(shipmentMap);

        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));
        assertNotNull(shipment.getCreatedAt());
        assertNotNull(shipment.getUpdatedAt());
        assertNotNull(shipment.getFees());

        PostageLabel label = shipment.getPostageLabel();

        assertNotNull(label);
        assertNotNull(label.getId());
        assertNotNull(label.getLabelUrl());
        assertNotNull(label.getCreatedAt());
        assertNotNull(label.getUpdatedAt());
        assertEquals("4x6", label.getLabelSize());
        assertEquals("application/zpl", label.getLabelFileType());

        ArrayList<Fee> fees = shipment.getFees();
        assertEquals(fees.size(), 2);

        Fee fee1 = fees.get(0);
        assertEquals(0.01, fee1.getAmount(), 0.001);
        assertEquals(true, fee1.getCharged());
        assertEquals(false, fee1.getRefunded());
        assertEquals("LabelFee", fee1.getType());

        Fee fee2 = fees.get(1);
        assertEquals(4.83, fee2.getAmount(), 0.001);
        assertEquals(true, fee2.getCharged());
        assertEquals(false, fee2.getRefunded());
        assertEquals("PostageFee", fee2.getType());
    }

    @Test
    public void testShipmentWithPostageLabelInline() throws EasyPostException {
        // create and buy shipment with inline postage label
        Map<String, Object> optionsMap = new HashMap<String, Object>();
        optionsMap.put("postage_label_inline", true);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("options", optionsMap);
        Shipment shipment = Shipment.create(shipmentMap);

        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));

        PostageLabel label = shipment.getPostageLabel();

        assertNotNull(label);
        assertNotNull(label.getId());
        assertNotNull(label.getLabelFile());
        assertNull(label.getLabelUrl());
    }

    @Test
    public void testShipmentWithPaymentOptions() throws EasyPostException {
        // create and buy shipment
        Map<String, Object> optionsMap = new HashMap<String, Object>();
        Map<String, Object> paymentMap = new HashMap<String, Object>();
        paymentMap.put("type", "SENDER");
        optionsMap.put("payment", paymentMap);

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("options", optionsMap);
        Shipment shipment = Shipment.create(shipmentMap);

        assertNotNull(shipment.getOptions());
        assertTrue(shipment.getOptions().containsKey("payment"));
    }

    @Test
    public void testShipmentWithoutPaymentOptions() throws EasyPostException {
        // create and buy shipment
        Map<String, Object> optionsMap = new HashMap<String, Object>();
        optionsMap.put("label_format", "ZPL");

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("options", optionsMap);
        Shipment shipment = Shipment.create(shipmentMap);

        assertNotNull(shipment.getOptions());
        assertTrue(shipment.getOptions().containsKey("payment"));
    }

    @Test
    public void testShipmentWithInsurance() throws EasyPostException {
        // create and buy shipment
        Shipment shipment = createDefaultShipmentDomestic();

        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));

        Map<String, Object> insureMap = new HashMap<String, Object>();
        insureMap.put("amount", 100.00);
        shipment = shipment.insure(insureMap);

        assertNotNull(shipment.getInsurance());
    }

    @Test
    public void testCreateShipmentWithInsurance() throws EasyPostException {
        // create and buy shipment
        Shipment shipment = createDefaultShipmentDomestic();

        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));
    }

    @Test
    public void testTrackerCreateAndRetrieve() throws EasyPostException, ParseException {
        // create test tracker
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tracking_code", "EZ4000000004");
        params.put("carrier", "FedEx");
        Tracker tracker = Tracker.create(params);

        assertNotNull(tracker);
        assertNotNull(tracker.getCreatedAt());
        assertNotNull(tracker.getUpdatedAt());
        assertNotNull(tracker.getFees());
        assertEquals(1, tracker.getFees().size());

        // This Est Delivery Date test confirms that the timestamps are within 1.5 seconds of each other
        // (rather than exactly equal)
        assertTrue("Est delivery dates aren't within expected timeframe",
                Math.abs((new Date()).getTime() - tracker.getEstDeliveryDate().getTime()) < 1500);

        assertEquals("Weights don't match", 17.6, tracker.getWeight(), 0.0001);
        assertEquals("Signed By doesn't match", "John Tester", tracker.getSignedBy());
        assertEquals("Service levels don't match", "FEDEX_GROUND", tracker.getCarrierDetail().getService());
        assertEquals("Containers don't match", "YOUR_PACKAGING", tracker.getCarrierDetail().getContainerType());

        assertNotNull("Destination Location is null", tracker.getCarrierDetail().getDestinationLocation());
        assertNotNull("Initial Delivery Attempt is null", tracker.getCarrierDetail().getInitialDeliveryAttempt());

        assertNotNull("Est delivery Date local is null", tracker.getCarrierDetail().getEstDeliveryDateLocal());
        assertNotNull("Est delivery Time local is null", tracker.getCarrierDetail().getEstDeliveryTimeLocal());
        assertNotNull("Created at is null", tracker.getCreatedAt());
        assertNotNull("Updated at is null", tracker.getUpdateAt());
        assertNotNull("PublicURL is not null", tracker.getPublicUrl());

        Tracker retrieved = Tracker.retrieve(tracker.getId());

        assertNotNull(retrieved);
        assertEquals("Tracker ids are not the same", tracker.getId(), retrieved.getId());
        assertEquals("Tracker statuses are not the same", tracker.getStatus(), retrieved.getStatus());

        assertNotNull(tracker.getCarrier());
        assertNotNull(retrieved.getCarrier());
        assertEquals("Tracker carriers are not the same", tracker.getCarrier(), retrieved.getCarrier());
        assertEquals("Tracker StatusDetail is not populated", "arrived_at_destination", tracker.getStatusDetail());

        TrackingDetail trackingDetail = tracker.getTrackingDetails().get(0);
        TrackingDetail retrievedDetail = tracker.getTrackingDetails().get(0);

        assertEquals("TrackingDetails are not the same", trackingDetail, retrievedDetail);
        assertEquals("TrackingDetail StatusDetail is not populated", "status_update", trackingDetail.getStatusDetail());

        TrackingLocation trackingLocation = trackingDetail.getTrackingLocation();
        TrackingLocation retrievedLocation = retrievedDetail.getTrackingLocation();

        assertEquals("TrackingLocations are not the same", trackingLocation, retrievedLocation);
    }

    @Test
    public void testBatchTrackerCreate() throws EasyPostException {

        String[] trackingCodes = new String[]{"EZ1000000001", "EZ1000000002", "EZ1000000003"};
        HashMap<String, Object> trackingCodeParams = new HashMap<String, Object>();

        for (int i = 0; i < trackingCodes.length; i++) {
            HashMap<String, Object> tracker = new HashMap<String, Object>();
            tracker.put("tracking_code", trackingCodes[i]);
            tracker.put("carrier", "USPS");
            trackingCodeParams.put(String.valueOf(i), tracker);
        }

        Tracker.createList(trackingCodeParams);
    }

    @Test
    public void testTrackerIndex() throws EasyPostException, ParseException {
        // create test tracker
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tracking_code", "EZ2000000002");
        params.put("carrier", "FedEx");
        Tracker tracker = Tracker.create(params);

        assertNotNull(tracker);

        // retrieve tracker by id
        Tracker tracker2 = Tracker.retrieve(tracker.getId());

        assertEquals("Tracker ids are not the same", tracker.getId(), tracker2.getId());

        // retrieve all trackers by tracking_code
        Map<String, Object> index_params = new HashMap<String, Object>();
        index_params.put("tracking_code", "EZ2000000002");
        TrackerCollection trackers = Tracker.all(index_params);

        assertEquals("Incorrect length received", 30, trackers.getTrackers().size());
        assertTrue("'has_more' should be true", trackers.getHasMore());
        assertEquals("Tracker ids in create response and all response are not the same",
                trackers.getTrackers().get(0).getId(), tracker.getId());

        // create another test tracker
        Tracker tracker3 = Tracker.create(params);

        assertNotSame("Tracker ids are not the same", tracker.getId(), tracker3.getId());

        // retrieve all created since 'tracker'
        Map<String, Object> index_params2 = new HashMap<String, Object>();
        index_params2.put("after_id", tracker.getId());
        TrackerCollection trackers2 = Tracker.all(index_params2);

        assertEquals("Incorrect length received", 1, trackers2.getTrackers().size());
        assertFalse("'has_more' should be true", trackers2.getHasMore());
        assertEquals("Tracker ids in create response and all response are not the same",
                trackers2.getTrackers().get(0).getId(), tracker3.getId());
    }

    @Test
    public void testShipmentWithRateError() throws EasyPostException {
        Map<String, Object> parcelMap = new HashMap<String, Object>();
        parcelMap.put("weight", 10);
        parcelMap.put("predefined_package", "Pak");

        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", parcelMap);
        Shipment shipment = Shipment.create(shipmentMap);

        assertNotNull(shipment.getMessages());
    }

    @Test
    public void testRateDeserialization() throws EasyPostException {
        Shipment shipment = createDefaultShipmentDomestic();
        assertNotNull(shipment.getRates().get(0).getCarrierAccountId());
        assertNotNull(shipment.getRates().get(0).getCurrency());
    }

    @Test
    public void testAddressResidentialOption() throws EasyPostException {
        // shipment residential_to_address option
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        Map<String, Object> shipmentOptionsMap = new HashMap<String, Object>();
        shipmentOptionsMap.put("residential_to_address", 1);
        shipmentMap.put("options", shipmentOptionsMap);

        Shipment shipment = Shipment.create(shipmentMap);
        assertTrue(shipment.getToAddress().getResidential());
    }

    @Test
    public void testAddressResidentialAttribute() throws EasyPostException {
        // toAddress.residential flag
        defaultToAddress.put("residential", 1);
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);

        Shipment shipment = Shipment.create(shipmentMap);
        assertTrue(shipment.getToAddress().getResidential());
    }

    @Test
    public void testAddressVerifies() throws EasyPostException {
        Map<String, Object> addressHash = new HashMap<String, Object>();
        addressHash.put("street1", "164 Townsend St");
        addressHash.put("street2", "Unit 1");
        addressHash.put("city", "San Francisco");
        addressHash.put("state", "CA");
        addressHash.put("zip", "19087");

        Address address = Address.create(addressHash);
        Address verified = address.verify();

        assertNull("Verified Address has no error message", verified.getMessage());
        assertEquals("Address did not verify as expected", "94107-1990", verified.getZip());

        Address createdAndVerified = Address.createAndVerify(addressHash);

        assertNull("Verified Address has no error message", createdAndVerified.getMessage());
        assertEquals("Address did not verify as expected", "94107-1990", createdAndVerified.getZip());
    }

    @Test
    public void testAddressCreateWithVerifyPasses() throws EasyPostException {
        Map<String, Object> addressHash = new HashMap<String, Object>();

        List<String> verificationList = new ArrayList<String>();
        verificationList.add("delivery");
        addressHash.put("verify", verificationList);

        addressHash.put("street1", "118 2 St");
        addressHash.put("street2", "4");
        addressHash.put("city", "SF");
        addressHash.put("state", "CA");
        addressHash.put("zip", "94105");
        addressHash.put("country", "US");
        addressHash.put("company", "EasyPost");
        addressHash.put("phone", "415-123-4567");

        Address address = Address.create(addressHash);

        assertNotNull("Id is null", address.getId());
        assertEquals("City did not verify", "SAN FRANCISCO", address.getCity());

        Map<String, AddressVerification> verifications = address.getVerifications();
        assertEquals("Verification did not succeed.", true, verifications.get("delivery").getSuccess());
        assertEquals("Verification had errors.", verifications.get("delivery").getErrors(), Collections.emptyList());
        assertNotNull("Address details is null.", verifications.get("delivery").getAddressDetail());
        assertEquals("Address details did not have TZ.", "America/Los_Angeles",
                verifications.get("delivery").getAddressDetail().getTimeZone());
    }

    @Test
    public void testAddressCreateWithVerifyFails() throws EasyPostException {
        Map<String, Object> addressHash = new HashMap<String, Object>();

        List<String> verificationList = new ArrayList<String>();
        verificationList.add("delivery");
        addressHash.put("verify", verificationList);

        addressHash.put("street1", "UNDELIVERABLE ST");
        addressHash.put("city", "SAN FRANCISCO");
        addressHash.put("state", "CA");
        addressHash.put("zip", "94105");
        addressHash.put("country", "US");
        addressHash.put("company", "EasyPost");
        addressHash.put("phone", "415-123-4567");

        Address address = Address.create(addressHash);

        assertNotNull("Id is null", address.getId());
        assertEquals("City did not verify", "SAN FRANCISCO", address.getCity());

        Map<String, AddressVerification> verifications = address.getVerifications();
        assertEquals("Verification did not succeed.", false, verifications.get("delivery").getSuccess());

        List<com.easypost.model.Error> errors = verifications.get("delivery").getErrors();

        assertTrue("At least two errors are present", errors.size() >= 2);
        assertEquals("Verification had a suggestion.", null, errors.get(0).getSuggestion());
        assertEquals("Verification error did not have a code.", "E.ADDRESS.NOT_FOUND", errors.get(0).getCode());
    }

    @Test
    public void testAddressCreateWithVerifyStrictFails() throws EasyPostException {
        Map<String, Object> addressHash = new HashMap<String, Object>();

        List<String> verificationList = new ArrayList<String>();
        verificationList.add("delivery");
        addressHash.put("verify_strict", verificationList);

        addressHash.put("street1", "UNDELIVERABLE ST");
        addressHash.put("city", "SAN FRANCISCO");
        addressHash.put("state", "CA");
        addressHash.put("zip", "94105");
        addressHash.put("country", "US");
        addressHash.put("company", "EasyPost");
        addressHash.put("phone", "415-123-4567");

        verifyStrictException.expect(com.easypost.exception.EasyPostException.class);

        Address address = Address.create(addressHash);
    }

    @Test
    public void testAddressErrorParses() throws EasyPostException {
        Map<String, Object> addressHash = new HashMap<String, Object>();
        addressHash.put("street1", "118 Milky Way");
        addressHash.put("zip", "19087");

        Address address = Address.create(addressHash);

        exception.expect(com.easypost.exception.EasyPostException.class);
        address.verify();
    }

    @Test
    public void testAddressErrorCatchAndInspect() throws EasyPostException {
        try {
            Map<String, Object> address = new HashMap<String, Object>();
            address.put("street1", "UNDELIVERABLE ST");
            address.put("city", "SAN FRANCISCO");
            address.put("state", "CA");
            address.put("zip", "94105");
            address.put("country", "US");
            address.put("company", "EasyPost");
            address.put("phone", "415-123-4567");

            List<String> verifications = new ArrayList<String>();
            verifications.add("delivery");
            address.put("verify_strict", verifications);

            Address.create(address);
        } catch (EasyPostException e) {
            assertNotNull(e);

            assertThat(e.getMessage(), containsString("An error occurred"));
            assertThat(e.getMessage(), containsString("Response code: 422"));
            assertThat(e.getMessage(), containsString(
                    "\"error\":{\"code\":\"ADDRESS.VERIFY.FAILURE\",\"message\":\"Unable to verify address.\""));
        }
    }

    @Test
    public void testBatchBuy() throws EasyPostException, InterruptedException {
        List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("carrier", "USPS");
        shipmentMap.put("service", "Priority");
        shipments.add(shipmentMap);

        // create batch and wait until ready
        Map<String, Object> batchMap = new HashMap<String, Object>();
        batchMap.put("shipments", shipments);
        Batch batch = Batch.create(batchMap);
        while (true) {
            batch = batch.refresh();
            if ("created".equals(batch.getState())) {
                break;
            }
            Thread.sleep(3000);
        }

        batch.buy();
        while (true) {
            batch = batch.refresh();
            if ("purchased".equals(batch.getState())) {
                break;
            }
            Thread.sleep(3000);
        }

        assertEquals("Batch state is not purchased.", "purchased", batch.getState());
    }

    @Test
    public void testTaxIdentifier() throws EasyPostException, ParseException {
        HashMap<String, Object> shipmentMap = new HashMap<>();

        List<Map<String, Object>> taxIdentifiers = new ArrayList<>();
        Map<String, Object> taxIdentifierMap = new HashMap<>();
        taxIdentifierMap.put("entity", "SENDER");
        taxIdentifierMap.put("issuing_country", "US");
        taxIdentifierMap.put("tax_id", "123456789");
        taxIdentifierMap.put("tax_id_type", "EORI");
        taxIdentifiers.add(taxIdentifierMap);

        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("to_address", defaultToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("tax_identifiers", taxIdentifiers);

        Shipment shipment = Shipment.create(shipmentMap);
        TaxIdentifier taxIdentifierReceived = shipment.getTaxIdentifiers().get(0);
        assertEquals("SENDER", taxIdentifierReceived.getEntity());
        assertEquals("HIDDEN", taxIdentifierReceived.getTaxId());
        assertEquals("EORI", taxIdentifierReceived.getTaxIdType());
        assertEquals("US", taxIdentifierReceived.getIssuingCountry());
        Shipment retrievedShipment = Shipment.retrieve(shipment.getId());
        TaxIdentifier taxIdentifierRetrieved = retrievedShipment.getTaxIdentifiers().get(0);
        assertEquals("SENDER", taxIdentifierRetrieved.getEntity());
        assertEquals("HIDDEN", taxIdentifierRetrieved.getTaxId());
        assertEquals("EORI", taxIdentifierRetrieved.getTaxIdType());
        assertEquals("US", taxIdentifierRetrieved.getIssuingCountry());
    }

    @Test
    public void testBatchCreateScanForm() throws EasyPostException, InterruptedException {
        // create and buy shipments
        Shipment shipment1 = createDefaultShipmentDomestic();
        Shipment shipment2 = createDefaultShipmentDomestic();
        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment1.buy(shipment1.lowestRate(buyCarriers));
        shipment2.buy(shipment2.lowestRate(buyCarriers));

        // create batch and wait until ready
        Batch batch = Batch.create();
        while (true) {
            batch = batch.refresh();
            if ("created".equals(batch.getState())) {
                break;
            }
            Thread.sleep(3000);
        }

        // add shipments to batch
        List<Shipment> shipments = new ArrayList<Shipment>();
        shipments.add(shipment1);
        shipments.add(shipment2);
        batch.addShipments(shipments);

        // create manifest and wait for it to be ready
        batch.createScanForm();
        while (true) {
            batch = batch.refresh();
            if (batch.getScanForm() != null) {
                break;
            }
            Thread.sleep(3000);
        }

        ScanForm scanForm = batch.getScanForm();

        assertNotNull(scanForm);
        assertNotEquals(scanForm.getLabelUrl(), "");
        assertNotEquals(scanForm.getLabelFileType(), "");
        assertNotNull(scanForm.getBatchId());

        shipment1 = Shipment.retrieve(shipment1.getId());
        assertNotNull(shipment1.getBatchId());
        assertEquals(shipment1.getBatchId(), batch.getId());

        shipment2 = Shipment.retrieve(shipment2.getId());
        assertNotNull(shipment2.getBatchId());
        assertEquals(shipment2.getBatchId(), batch.getId());
    }

    @Test
    @Ignore // The UserId is currently locked out
    public void testPickup() throws EasyPostException, InterruptedException {
        Shipment shipment = createDefaultShipmentDomestic();
        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("UPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

        String reference = "internal_id_1234";
        String instructions = "Special pickup instructions";

        Map<String, Object> pickupMap = new HashMap<String, Object>();
        pickupMap.put("address", defaultFromAddress);
        pickupMap.put("shipment", shipment);
        pickupMap.put("reference", reference);
        pickupMap.put("min_datetime", df.format(new Date()));
        pickupMap.put("max_datetime", df.format(new Date()));
        pickupMap.put("is_account_address", true);
        pickupMap.put("instructions", instructions);

        Pickup pickup = Pickup.create(pickupMap);

        assertNotNull(pickup);
        assertEquals(0, pickup.getMessages().size());
        assertEquals(pickup.getReference(), reference);
        assertEquals(pickup.getInstructions(), instructions);
        assertEquals("unknown", pickup.getStatus());
        assertTrue(pickup.getIsAccountAddress());

        assertEquals("UPS", pickup.getPickupRates().get(0).getCarrier());
        assertEquals("Same-day Pickup", pickup.getPickupRates().get(0).getService());

        Map<String, Object> buyMap = new HashMap<String, Object>();
        buyMap.put("carrier", pickup.getPickupRates().get(0).getCarrier());
        buyMap.put("service", pickup.getPickupRates().get(0).getService());
        pickup = pickup.buy(buyMap);

        assertNotNull(pickup);
        assertEquals(0, pickup.getMessages().size());
        assertEquals("scheduled", pickup.getStatus());

        Map<String, Object> cancelMap = new HashMap<String, Object>();
        cancelMap.put("id", pickup.getId());
        pickup = pickup.cancel(cancelMap);

        assertNotNull(pickup);
        assertEquals(0, pickup.getMessages().size());
        assertEquals("canceled", pickup.getStatus());

        Pickup retrieved = Pickup.retrieve(pickup.getId());
        pickup = pickup.refresh();

        assertNotNull(pickup);
        assertNotNull((retrieved));
        assertEquals(0, retrieved.getMessages().size());
        assertEquals(retrieved.getId(), pickup.getId());
        assertEquals(retrieved.getStatus(), pickup.getStatus());
    }

    @Test
    public void testOrder() throws EasyPostException {
        // create and buy multi-shipment order
        Map<String, Object> shipment_1 = orderShipment();
        Map<String, Object> shipment_2 = orderShipment();
        List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
        shipments.add(shipment_1);
        shipments.add(shipment_2);

        Map<String, Object> orderParams = new HashMap<String, Object>();
        orderParams.put("shipments", shipments);
        orderParams.put("from_address", defaultFromAddress);
        orderParams.put("to_address", defaultToAddress);
        Order order = Order.create(orderParams);

        Map<String, Object> buyParams = new HashMap<String, Object>();
        buyParams.put("carrier", "USPS");
        buyParams.put("service", "Priority");

        order.buy(buyParams);

        assertNotNull(order.getShipments().get(0).getTracker());
        assertNotNull(order.getShipments().get(0).getPostageLabel().getLabelUrl());
        assertNotNull(order.getShipments().get(1).getPostageLabel().getLabelUrl());

        assertEquals(order.getShipments().get(0).getOrderId(), order.getId());
    }

    @Test
    public void testOrderNewRates() throws EasyPostException {
        // create and buy multi-shipment order
        Map<String, Object> shipment_1 = orderShipment();
        Map<String, Object> shipment_2 = orderShipment();
        List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
        shipments.add(shipment_1);
        shipments.add(shipment_2);

        Map<String, Object> orderParams = new HashMap<String, Object>();
        orderParams.put("shipments", shipments);
        orderParams.put("from_address", defaultFromAddress);
        orderParams.put("to_address", defaultToAddress);
        Order order = Order.create(orderParams);

        String rateId = order.getShipments().get(0).getRates().get(0).getId();
        assertNotNull("Original Rate ID is null", rateId);

        order.newRates();
        String newRateId = order.getShipments().get(0).getRates().get(0).getId();

        assertNotNull("New Rate ID is null", newRateId);
        assertNotEquals("New Rate ID and Old Rate ID are equal", newRateId, rateId);
    }

    @Test
    public void testCustomsInfo() throws EasyPostException {
        Map<String, Object> shipmentMap = new HashMap<String, Object>();
        shipmentMap.put("to_address", canadaToAddress);
        shipmentMap.put("from_address", defaultFromAddress);
        shipmentMap.put("parcel", defaultParcel);
        shipmentMap.put("customs_info", defaultCustomsInfo);
        Map<String, Object> shipmentOptionsMap = new HashMap<String, Object>();
        shipmentOptionsMap.put("suppress_etd", true);
        shipmentMap.put("options", shipmentOptionsMap);

        Shipment shipment = Shipment.create(shipmentMap);

        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));

        ///System.out.println("Shipment.getForms(): >>"+shipment.getForms()+"<<");
        /// java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        /// Form does not exists. Invalid test
    /*
    assertTrue(
        "Customs Form was not submitted electronically",
        shipment.getForms().get(0).getSubmittedElectronically());
        */
    }

    @Test
    public void testCustomsItem() throws EasyPostException {
        Map<String, Object> customsItemMap = new HashMap<String, Object>();
        customsItemMap.put("description", "T-shirt");
        customsItemMap.put("quantity", 1);
        customsItemMap.put("value", 10);
        customsItemMap.put("weight", 5);
        customsItemMap.put("origin_country", "us");
        customsItemMap.put("hs_tariff_number", "123456");
        customsItemMap.put("code", "123");
        customsItemMap.put("currency", "USD");

        CustomsItem customsItem = CustomsItem.create(customsItemMap);
        CustomsItem customsItem2 = CustomsItem.retrieve(customsItem.getId());

        assertEquals("T-shirt", customsItem2.getDescription());
        assertEquals(1, customsItem2.getQuantity(), 0.1);
        assertEquals(10, customsItem2.getValue(), 0.1);
        assertEquals(5, customsItem2.getWeight(), 0.1);
        assertEquals("US", customsItem2.getOriginCountry());
        assertEquals("123456", customsItem2.getHsTariffNumber());
        assertEquals("USD", customsItem2.getCurrency());
        assertEquals("123", customsItem2.getCode());
    }

    @Test
    public void testInsurance() throws EasyPostException {
        Map<String, Object> insuraceMap = new HashMap<String, Object>();
        insuraceMap.put("to_address", defaultToAddress);
        insuraceMap.put("from_address", defaultFromAddress);
        insuraceMap.put("amount", 101.00);
        insuraceMap.put("carrier", "USPS");
        insuraceMap.put("tracking_code", "EZ2000000002");

        Insurance insurance = Insurance.create(insuraceMap);
        assertNotNull("ID is null", insurance.getId());
        assertNotNull("Tracking Code is null", insurance.getTrackingCode());
        assertNotNull("To Address is null", insurance.getToAddress());
        assertNotNull("From Address is null", insurance.getFromAddress());
        assertNotNull("Tracker is null", insurance.getTracker());

        Insurance insurance2 = Insurance.retrieve(insurance.getId());
        assertNotNull("ID is null", insurance2.getId());
        assertEquals("Create and Retrieve returned different ids", insurance.getId(), insurance2.getId());
        assertEquals("Create and Retrieve returned different tracking codes", insurance.getTrackingCode(),
                insurance2.getTrackingCode());

        Map<String, Object> index_params = new HashMap<String, Object>();
        index_params.put("tracking_code", "EZ2000000002");
        index_params.put("page_size", "5");
        InsuranceCollection insurances = Insurance.all(index_params);

        assertTrue("Wrong page_size returned", insurances.getInsurances().size() == 5);
        assertTrue("Insurances HasMore not set correctly", insurances.getHasMore());
    }

    @Test
    public void testShipmentReport() throws EasyPostException {
        // Define request params
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type", "shipment");

        // Create a shipment report
        Report shipmentReport = Report.create(paramMap);
        assertNotNull("ID is null", shipmentReport.getId());
        assertThat("ID is not a shipment report ID", shipmentReport.getId(), containsString("shprep_"));

        // Retrieve a shipment report
        Report shipmentReport2 = Report.retrieve(shipmentReport.getId());
        assertNotNull("ID is null", shipmentReport2.getId());
        assertThat("ID is not a shipment report ID", shipmentReport2.getId(), containsString("shprep_"));
        assertEquals("Create and Retrieve returned different ids", shipmentReport.getId(), shipmentReport2.getId());

        // Index shipment reports
        paramMap.put("page_size", "4");
        ReportCollection shipmentReports = Report.all(paramMap);
        assertEquals("Page Size not respected", 4, shipmentReports.getReports().size());
        assertEquals("Does not have more", true, shipmentReports.getHasMore());
    }

    @Test
    public void testTrackerReport() throws EasyPostException {
        // Define request params
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type", "tracker");

        // Create a tracker report
        Report trackerReport = Report.create(paramMap);
        assertNotNull("ID is null", trackerReport.getId());
        assertThat("ID is not a tracker report ID", trackerReport.getId(), containsString("trkrep_"));

        // Retrieve a tracker report
        Report trackerReport2 = Report.retrieve(trackerReport.getId());
        assertNotNull("ID is null", trackerReport2.getId());
        assertThat("ID is not a tracker report ID", trackerReport2.getId(), containsString("trkrep_"));
        assertEquals("Create and Retrieve returned different ids", trackerReport.getId(), trackerReport2.getId());

        // Index tracker reports
        paramMap.put("page_size", "4");
        ReportCollection trackerReports = Report.all(paramMap);
        assertEquals("Page Size not respected", 4, trackerReports.getReports().size());
        assertEquals("Does not have more", true, trackerReports.getHasMore());
    }

    @Test
    public void testPaymentLogReport() throws EasyPostException {
        // Define request params
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type", "payment_log");

        // Create a payment_log report
        Report paymentLogReport = Report.create(paramMap);
        assertNotNull("ID is null", paymentLogReport.getId());
        assertThat("ID is not a payment_log report ID", paymentLogReport.getId(), containsString("plrep_"));

        // Retrieve a payment_log report
        Report paymentLogReport2 = Report.retrieve(paymentLogReport.getId());
        assertNotNull("ID is null", paymentLogReport2.getId());
        assertThat("ID is not a payment_log report ID", paymentLogReport2.getId(), containsString("plrep_"));
        assertEquals("Create and Retrieve returned different ids", paymentLogReport.getId(), paymentLogReport2.getId());

        // Index payment_log reports
        paramMap.put("page_size", "4");
        ReportCollection paymentLogReports = Report.all(paramMap);
        assertEquals("Page Size not respected", 4, paymentLogReports.getReports().size());
        assertEquals("Does not have more", true, paymentLogReports.getHasMore());
    }

    @Ignore ("TODO: rework webhook tests. Skipping until then.")
    @Test
    public void testWebhookCRUD() throws EasyPostException {
        // Define request params
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String url = "https://example.com/" + UUID.randomUUID();
        paramMap.put("url", url);

        // Create a webhook
        Webhook webhook = Webhook.create(paramMap);
        assertNotNull("ID is null", webhook.getId());
        assertEquals("mode is not test", "test", webhook.getMode());
        assertEquals("URL is not correctly ser", webhook.getUrl(), url);
        assertNull("disabledAt is not null", webhook.getDisabledAt());
        assertEquals("Class is not Webhook", Webhook.class, webhook.getClass());

        // Retrieve a webhook
        Webhook webhook2 = Webhook.retrieve(webhook.getId());
        assertNotNull("ID is null", webhook2.getId());
        assertEquals("Create and Retrieve returned different ids", webhook.getId(), webhook2.getId());

        // Update a webhook (re-enable it)
        Webhook webhook3 = webhook.update();
        assertNotNull("ID is null", webhook3.getId());
        assertEquals("Create and Update returned different ids", webhook.getId(), webhook3.getId());

        // Index webhooks
        WebhookCollection webhooks = Webhook.all();
        assertEquals("Create and Retrieve returned different ids", webhook.getId(),
                webhooks.getWebhooks().get(webhooks.getWebhooks().size() - 1).getId());

        // Delete webhook
        webhook.delete();
        try {
            Webhook.retrieve(webhook.getId());
            assertFalse("Webhook did not delete", true);
        } catch (EasyPostException e) {
            assertThat(e.getMessage(), containsString("An error occurred"));
            assertThat(e.getMessage(), containsString("Response code: 404"));
            assertThat(e.getMessage(), containsString(
                    "{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"The requested resource could not be found.\",\"errors\":[]}}"));
        }
    }

    @Test
    public void testScanForm() throws EasyPostException {
        // create and buy shipment
        Shipment shipment = createDefaultShipmentDomestic();
        List<String> buyCarriers = new ArrayList<String>();
        buyCarriers.add("USPS");
        shipment = shipment.buy(shipment.lowestRate(buyCarriers));

        // create ScanForm
        List<Shipment> shipments = new ArrayList<Shipment>();
        shipments.add(shipment);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shipments", shipments);
        ScanForm scanForm = ScanForm.create(paramMap);

        assertNotNull("No ID returned", scanForm.getId());
        assertEquals("Shipment's tracking code not on Scan Form", scanForm.getTrackingCodes().get(0),
                shipment.getTrackingCode());

        // retrieve ScanForm
        ScanForm scanForm2 = ScanForm.retrieve(scanForm.getId());
        assertNotNull("No ID returned", scanForm2.getId());
        assertEquals("IDs do not match", scanForm2.getId(), scanForm.getId());

        // index ScanForms
        Map<String, Object> indexMap = new HashMap<String, Object>();
        indexMap.put("page_size", 2);
        ScanFormCollection scanForms = ScanForm.all(indexMap);
        assertEquals("IDs do not match", scanForms.getScanForms().get(0).getId(), scanForm.getId());
    }


    //This test needs to have new set of dates to avoid "report already exists" error
  /*
  @Test
  public void testShipmentReportDates() throws EasyPostException {
    // Define request params
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("type", "shipment");
    paramMap.put("start_date", "2017-10-01");
    paramMap.put("end_date", "2017-10-30");

    // Create a shipment report
    Report shipmentReport = Report.create(paramMap);
    assertNotNull("ID is null", shipmentReport.getId());
    assertThat("ID is not a shipment report ID", shipmentReport.getId(), containsString("shprep_"));

    // Retrieve a shipment report
    Report shipmentReport2 = Report.retrieve(shipmentReport.getId());
    assertNotNull("ID is null", shipmentReport2.getId());
    assertThat(
        "ID is not a shipment report ID", shipmentReport2.getId(), containsString("shprep_"));
    assertEquals(
        "Create and Retrieve returned different ids",
        shipmentReport.getId(),
        shipmentReport2.getId());
    assertEquals("Incorrect ShipmentReport start_date", "Sun Oct 01 00:00:00 PDT 2017", shipmentReport2.getStartDate().toString());
    assertEquals("Incorrect ShipmentReport end_date", "Mon Oct 30 00:00:00 PDT 2017", shipmentReport2.getEndDate().toString());
  }*/


  /*
   // This test requires a FedExSameDayCity account
   @Test
   public void testShipmentMessageInRateError() throws EasyPostException {
     EasyPost.apiKey = "KEY";
       Map<String, Object> address = new HashMap<String, Object>();
       address.put("street1", "UNDELIVERABLE ST");
       address.put("city", "SAN FRANCISCO");
       address.put("state", "CA");
       address.put("zip", "94105");
       address.put("country", "US");
     Map<String, Object> shipmentMap = new HashMap<String, Object>();
     shipmentMap.put("to_address", address);
     shipmentMap.put("from_address", defaultFromAddress);
     shipmentMap.put("parcel", defaultParcel);
     shipmentMap.put("carrier_accounts", "ca_123xyz");   // FedExSameDayCity carrier account id
     Shipment shipment = Shipment.create(shipmentMap);
             System.out.println(shipment.prettyPrint());
     List<ShipmentMessage> messages = shipment.getMessages();
     assertNotNull(messages);
     assertEquals("Shipment message as an object did not parse.",messages.get(0).getMessage().toString(),
                 "{code=E.ADDRESS.NOT_FOUND, field=address, message=Address not found, suggestion=null}");
   }
  */

  /*
  // This test requires a FedEx account
  @Test
  public void testShipmentWithForm() throws EasyPostException {
    Map<String, Object> optionsMap = new HashMap<String, Object>();
    optionsMap.put("cod_amount", 10);

    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    shipmentMap.put("options", optionsMap);
    Shipment shipment = Shipment.create(shipmentMap);

    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("FEDEX");
    List<String> buyServices = new ArrayList<String>();
    buyServices.add("FEDEX_2_DAY");
    shipment.buy(shipment.lowestRate(buyCarriers, buyServices));

    assertNotNull(shipment.getForms().get(0).getFormUrl());
  }
  */

    //  //  This test requires a production api key
    //  @Test
    //  public void testUserMethods() throws EasyPostException {
    //    EasyPost.apiKey = "KEY"; // easypost private production key
    //
    //    User user = User.retrieveMe();
    //
    //    System.out.println(user.getPhoneNumber());
    //    System.out.println(user.getRechargeAmount());
    //    System.out.println(user.getSecondaryRechargeAmount());
    //
    //    user.setSecondaryRechargeAmount("$200.00");
    //
    //    Map<String, Object> userHash = new HashMap<String, Object>();
    //    userHash.put("secondary_recharge_amount", 100000.00);
    //
    //    user.update(userHash);
    //
    //    System.out.println(user.getSecondaryRechargeAmount());
    //
    //    System.out.println(user.getRechargeThreshold());
    //  }

    //  //  This test requires a production api key
    //  @Test
    //  public void testUserCreateAndDelete() throws EasyPostException {
    //    EasyPost.apiKey = "KEY"; // easypost private production key
    //
    //    Map<String, Object> userHash = new HashMap<String, Object>();
    //    userHash.put("name", "Chad DeVader");
    //    userHash.put("password", "4theempire");
    //    userHash.put("password_confirmation", "4theempire");
    //    userHash.put("phone_number", "555-123-4321");
    //
    //
    //    User child = User.create(userHash);
    //
    //    assertEquals("Child's name is incorrect", "Chad DeVader", child.getName());
    //    assertNotNull("Child's email is not present", child.getEmail());
    //    assertNotNull("Child's phone_number is not present", child.getPhoneNumber());
    //    assertNotNull("Child's ID is not present", child.getId());
    //
    //    User fetchedChild = User.retrieve(child.getId());
    //    assertEquals("Child's ID is not the same as FetchedChild's ID", fetchedChild.getId(),
    // child.getId());
    //
    //    fetchedChild.delete();
    //
    //    try {
    //      User newFetchedChild = User.retrieve(child.getId());
    //      assertNull("Child is not deleted", newFetchedChild);
    //    } catch (EasyPostException e) {
    //      assertThat(e.getMessage(), containsString("An error occurred"));
    //      assertThat(e.getMessage(), containsString("Response code: 404"));
    //      assertThat(e.getMessage(),
    // containsString("{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"The requested resource could
    // not be found.\",\"errors\":[]}}"));
    //    }
    //  }

    //  //  This test requires a production api key
    //  @Test
    //  public void testUserCreateAndDeleteFailsOnParent() throws EasyPostException {
    //    EasyPost.apiKey = "KEY"; // easypost private production key
    //
    //    User fetchedSelf = User.retrieveMe();
    //    assertNotNull("Parent's ID is not present", fetchedSelf);
    //
    //    try {
    //      fetchedSelf.delete();
    //      assertFalse("User did not delete", true);
    //    } catch (EasyPostException e) {
    //      assertThat(e.getMessage(), containsString("An error occurred"));
    //      assertThat(e.getMessage(), containsString("Response code: 404"));
    //      assertThat(e.getMessage(),
    // containsString("{\"error\":{\"code\":\"NOT_FOUND\",\"message\":\"The requested resource could
    // not be found.\",\"errors\":[]}}"));
    //    }
    //  }

    //  //  This test requires a production api key
    //  @Test
    //  public void testCarrierAccountMutability() throws EasyPostException {
    //    EasyPost.apiKey = "KEY"; // easypost private production key
    //
    //    Map<String, Object> listHash = new HashMap<String, Object>();
    //    listHash.put("type", "UpsAccount");
    //    List<CarrierAccount> carrierAccounts = CarrierAccount.all(listHash);
    //    assertNotNull(carrierAccounts);
    //
    //    int originalNumCarrierAccounts = carrierAccounts.size();
    //
    //    String description = "A java client library test account";
    //    String reference = "FedexJavaTest-1";
    //    String accountNumber = "B2B2B2";
    //    Map<String, Object> credentials = new HashMap<String, Object>();
    //    credentials.put("account_number", accountNumber);
    //    credentials.put("user_id", "UPSDOTCOM_USERNAME");
    //    credentials.put("password", "UPSDOTCOM_PASSWORD");
    //    credentials.put("access_license_number", "UPS_ACCESS_LICENSE_NUMBER");
    //
    //    Map<String, Object> carrierParams = new HashMap<String, Object>();
    //    carrierParams.put("type", "UpsAccount");
    //    carrierParams.put("description", description);
    //    carrierParams.put("reference", reference);
    //    carrierParams.put("credentials", credentials);
    //
    //    CarrierAccount carrierAccount = CarrierAccount.create(carrierParams);
    //    CarrierAccount retrieved = CarrierAccount.retrieve(carrierAccount.getId());
    //
    //    assertNotNull(retrieved);
    //    assertEquals(carrierAccount.getId(), retrieved.getId());
    //    assertEquals(description, retrieved.getDescription());
    //    assertEquals(reference, retrieved.getReference());
    //    assertEquals(accountNumber, retrieved.getCredentials().get("account_number"));
    //
    //    List<CarrierAccount> updatedCarrierAccounts = CarrierAccount.all(listHash);
    //    assertNotNull(updatedCarrierAccounts);
    //
    //    int updatedNumCarrierAccounts = updatedCarrierAccounts.size();
    //    assertEquals(originalNumCarrierAccounts + 1, updatedNumCarrierAccounts);
    //
    //    String newDescription = "A java client library test account that has been edited";
    //
    //    String newAccountNumber = "C3C3C3C3";
    //    credentials.put("account_number", newAccountNumber);
    //
    //    Map<String, Object> newParams = new HashMap<String, Object>();
    //    newParams.put("description", newDescription);
    //    newParams.put("credentials", credentials);
    //
    //    CarrierAccount mutated = retrieved.update(newParams);
    //
    //    assertNotNull(mutated);
    //    assertEquals(carrierAccount.getId(), mutated.getId());
    //    assertEquals(newDescription, mutated.getDescription());
    //    assertEquals(newAccountNumber, mutated.getCredentials().get("account_number"));
    //
    //    mutated.delete();
    //
    //    List<CarrierAccount> deletedCarrierAccounts = CarrierAccount.all(listHash);
    //    assertNotNull(deletedCarrierAccounts);
    //
    //    int finalNumCarrierAccounts = deletedCarrierAccounts.size();
    //    assertEquals(originalNumCarrierAccounts, finalNumCarrierAccounts);
    //  }
}
