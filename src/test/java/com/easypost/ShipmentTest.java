package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.Parcel;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.easypost.model.ShipmentCollection;
import com.easypost.model.Smartrate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShipmentTest {
    private static Shipment globalShipment;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        globalShipment = Shipment.create(Fixture.fullShipment());
    }

    /**
     * Test creating a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.fullShipment());

        assertTrue(shipment instanceof Shipment);
        assertTrue(shipment.getId().startsWith("shp_"));
        assertEquals("PNG", shipment.getOptions().get("label_format"));
        assertEquals("123", shipment.getOptions().get("invoice_number"));
        assertEquals("123", shipment.getReference());
        assertNotNull(shipment.getRates());
    }

    /**
     * Test retrieving a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Shipment retrievedShipment = Shipment.retrieve(globalShipment.getId());

        assertTrue(retrievedShipment instanceof Shipment);
        assertThat(globalShipment).usingRecursiveComparison().isEqualTo(retrievedShipment);
    }

    /**
     * Test retrieving all shipments.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("page_size", Fixture.pageSize());

        ShipmentCollection shipments = Shipment.all(params);

        List<Shipment> shipmentsList = shipments.getShipments();

        assertTrue(shipmentsList.size() <= Fixture.pageSize());
        assertNotNull(shipments.getHasMore());
        assertTrue(shipmentsList.stream().allMatch(shipment -> shipment instanceof Shipment));
    }

    /**
     * Test buying a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.basicShipment());

        Shipment boughtShipment = shipment.buy(shipment.lowestRate());

        assertNotNull(boughtShipment.getPostageLabel());
    }

    /**
     * Test generating a new rate for a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRegenerateRates() throws EasyPostException {
        Shipment shipmentWithNewRates = globalShipment.newRates();

        List<Rate> rates = shipmentWithNewRates.getRates();

        assertTrue(rates instanceof List);
        for (Rate rate : rates) {
            assertTrue(rate instanceof Rate);
        }
    }

    /**
     * Test converting a label for a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testConvertLabel() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();

        params.put("file_format", "ZPL");

        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        Shipment shipmentWithLabel = shipment.label(params);

        assertNotNull(shipmentWithLabel.getPostageLabel().getLabelZplUrl());
    }

    /**
     * Test insuring a Shipment.
     *
     * If the shipment was purchased with a USPS rate, it must have had its
     * insurance set to `0` when bought
     * so that USPS doesn't automatically insure it so we could manually insure it
     * here.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testInsure() throws EasyPostException {
        Map<String, Object> shipmentData = Fixture.oneCallBuyShipment();
        Map<String, Object> insuranceData = new HashMap<>();

        insuranceData.put("amount", "100");
        shipmentData.put("insurance", 0);
        // Set to 0 so USPS doesn't insure this automatically and we can insure the
        // shipment manually.

        Shipment shipment = Shipment.create(shipmentData);

        Shipment shipmentWithInsurance = shipment.insure(insuranceData);

        assertEquals("100.00", shipmentWithInsurance.getInsurance());
    }

    /**
     * Test refunding a Shipment.
     *
     * Refunding a test shipment must happen within seconds of the shipment being
     * created as test shipments naturally
     * follow a flow of created -> delivered to cycle through tracking events in
     * test mode - as such anything older
     * than a few seconds in test mode may not be refundable.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRefund() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());

        Shipment refundedShipment = shipment.refund();

        assertEquals("submitted", refundedShipment.getRefundStatus());
    }

    /**
     * Test getting Smartrates from a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testSmartRate() throws EasyPostException {
        List<Smartrate> smartRates = globalShipment.getSmartrates();

        assertTrue(smartRates instanceof List);
        assertEquals(globalShipment.getRates().get(0).getId(), smartRates.get(0).getId());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile50());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile75());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile85());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile90());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile95());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile97());
        assertNotNull(smartRates.get(0).getTimeInTransit().getPercentile99());
    }

    /**
     * Test creating a Shipment with empty or null objects and arrays.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateEmptyObjects() throws EasyPostException {
        Map<String, Object> shipmentData = Fixture.basicShipment();

        shipmentData.put("options", null);
        shipmentData.put("tax_identifiers", null);
        shipmentData.put("reference", "");
        shipmentData.put("customs_info", null);

        Shipment shipment = Shipment.create(shipmentData);

        assertTrue(shipment instanceof Shipment);
        assertTrue(shipment.getId().startsWith("shp_"));
        assertNull(shipment.getReference());
        assertNull(shipment.getCustomsInfo());
        assertNull(shipment.getTaxIdentifiers());
        assertNotNull(shipment.getOptions()); // The EasyPost API populates some default values here
    }

    /**
     * Test creating a Shipment with `tax_identifiers`.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateTaxIdentifiers() throws EasyPostException {
        Map<String, Object> shipmentData = Fixture.basicShipment();
        List<Object> taxIdentifiers = new ArrayList<>();

        taxIdentifiers.add(Fixture.taxIdentifier());
        shipmentData.put("tax_identifiers", taxIdentifiers);

        Shipment shipmentWithTaxIdentifiers = Shipment.create(shipmentData);

        assertTrue(shipmentWithTaxIdentifiers instanceof Shipment);
        assertTrue(shipmentWithTaxIdentifiers.getId().startsWith("shp_"));
        assertEquals("IOSS", shipmentWithTaxIdentifiers.getTaxIdentifiers().get(0).getTaxIdType());
    }

    /**
     * Test creating a shipment when only IDs are used.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateWithIds() throws EasyPostException {
        Address fromAddress = Address.create(Fixture.basicAddress());
        Address toAddress = Address.create(Fixture.basicAddress());
        Parcel parcel = Parcel.create(Fixture.basicParcel());

        Map<String, Object> shipmentData = Fixture.basicShipment();
        shipmentData.put("from_address", Collections.singletonMap("id", fromAddress.getId()));
        shipmentData.put("to_address", Collections.singletonMap("id", toAddress.getId()));
        shipmentData.put("parcel", Collections.singletonMap("id", parcel.getId()));

        Shipment shipment = Shipment.create(shipmentData);

        assertTrue(shipment instanceof Shipment);
        assertTrue(shipment.getId().startsWith("shp_"));
        assertTrue(shipment.getToAddress().getId().startsWith("adr_"));
        assertTrue(shipment.getFromAddress().getId().startsWith("adr_"));
        assertTrue(shipment.getParcel().getId().startsWith("prcl_"));
        assertEquals("388 Townsend St", shipment.getFromAddress().getStreet1());
    }

    /**
     * Test getting the lowest rate of a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testLowestRate() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.fullShipment());

        // Test lowest rate with no filters
        Rate lowestRate = shipment.lowestRate();
        assertEquals("First", lowestRate.getService());
        assertEquals(5.49, lowestRate.getRate(), 0.01);
        assertEquals("USPS", lowestRate.getCarrier());

        // Test lowest rate with service filter (this rate is higher than the lowest but should filter)
        List<String> service = new ArrayList<>(Arrays.asList("Priority"));
        Rate lowestRateService = shipment.lowestRate(null, service);
        assertEquals("Priority", lowestRateService.getService());
        assertEquals(7.37, lowestRateService.getRate(), 0.01);
        assertEquals("USPS", lowestRateService.getCarrier());

        // Test lowest rate with carrier filter (should error due to bad carrier)
        List<String> carrier = new ArrayList<>(Arrays.asList("BAD CARRIER"));
        assertThrows(EasyPostException.class, () -> {
            shipment.lowestRate(null, carrier);
        });
    }

    /**
     * Test getting the lowest Smartrate of a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testLowestSmartRate() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.basicShipment());
        Smartrate lowestSmartRateFilters = shipment.lowestSmartRate(1, "percentile_90");
        
        // Test lowest smartrate with valid filters
        assertEquals("Priority", lowestSmartRateFilters.getService());
        assertEquals(7.37, lowestSmartRateFilters.getRate(), 0.01);
        assertEquals("USPS", lowestSmartRateFilters.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict delivery days)
        assertThrows(EasyPostException.class, () -> {
            shipment.lowestSmartRate(0, "percentile_90");
        });

        // Test lowest smartrate with invalid filters (should error due to invalid delivery accuracy)
        assertThrows(EasyPostException.class, () -> {
            shipment.lowestSmartRate(1, "BAD ACCURACY");
        });
    }

    /**
     * Test getting the lowest smartrate from a list of smartrates.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetLowestSmartRates() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.basicShipment());

        List<Smartrate> smartrates = shipment.getSmartrates();

        // Test lowest smartrate with valid filters
        Smartrate lowest_Smartrate_filters = Shipment.getLowestSmartRate(smartrates, 1, "percentile_90");
        assertEquals("Priority", lowest_Smartrate_filters.getService());
        assertEquals(7.37, lowest_Smartrate_filters.getRate(), 0.01);
        assertEquals("USPS", lowest_Smartrate_filters.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict delivery days)
        assertThrows(EasyPostException.class, () -> {
            Shipment.getLowestSmartRate(smartrates, 0, "percentile_90");
        });

        // Test lowest smartrate with invalid filters (should error due to bad delivery accuracy)
        assertThrows(EasyPostException.class, () -> {
            Shipment.getLowestSmartRate(smartrates, 1, "BAD ACCURACY");
        });
    }
}
