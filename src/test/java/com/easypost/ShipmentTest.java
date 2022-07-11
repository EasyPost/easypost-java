package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Address;
import com.easypost.model.Form;
import com.easypost.model.Parcel;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.easypost.model.ShipmentCollection;
import com.easypost.model.Smartrate;
import com.easypost.model.SmartrateAccuracy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ShipmentTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("shipment", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Shipment shipment = createFullShipment();

        assertInstanceOf(Shipment.class, shipment);
        assertTrue(shipment.getId().startsWith("shp_"));
        assertNotNull(shipment.getRates());
        assertEquals("PNG", shipment.getOptions().get("label_format"));
        assertEquals("123", shipment.getOptions().get("invoice_number"));
        assertEquals("123", shipment.getReference());
    }

    /**
     * Create a full shipment.
     *
     * @return Shipment object
     */
    private static Shipment createFullShipment() throws EasyPostException {
        return Shipment.create(Fixture.fullShipment());
    }

    /**
     * Test retrieving a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        Shipment shipment = createFullShipment();

        Shipment retrievedShipment = Shipment.retrieve(shipment.getId());

        assertInstanceOf(Shipment.class, retrievedShipment);
        assertThat(shipment).usingRecursiveComparison().isEqualTo(retrievedShipment);
    }

    /**
     * Test retrieving all shipments.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page_size", Fixture.pageSize());

        ShipmentCollection shipmentCollection = Shipment.all(params);

        List<Shipment> shipments = shipmentCollection.getShipments();

        assertTrue(shipments.size() <= Fixture.pageSize());
        assertNotNull(shipmentCollection.getHasMore());
        assertTrue(shipments.stream().allMatch(shipment -> shipment instanceof Shipment));
    }

    /**
     * Test buying a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuy() throws EasyPostException {
        vcr.setUpTest("buy");

        Shipment shipment = createBasicShipment();

        Shipment boughtShipment = shipment.buy(shipment.lowestRate());

        assertNotNull(boughtShipment.getPostageLabel());
    }

    /**
     * Create a basic shipment.
     *
     * @return Shipment object
     */
    private static Shipment createBasicShipment() throws EasyPostException {
        return Shipment.create(Fixture.basicShipment());
    }

    /**
     * Test generating a new rate for a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRegenerateRates() throws EasyPostException {
        vcr.setUpTest("regenerate_rates");

        Shipment shipment = createFullShipment();

        Shipment shipmentWithNewRates = shipment.newRates();

        List<Rate> rates = shipmentWithNewRates.getRates();

        assertNotNull(rates);
        assertInstanceOf(List.class, rates);
        for (Rate rate : rates) {
            assertInstanceOf(Rate.class, rate);
        }
    }

    /**
     * Test converting a label for a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testConvertLabel() throws EasyPostException {
        vcr.setUpTest("convert_label");

        Shipment shipment = createOneCallBuyShipment();

        Map<String, Object> params = new HashMap<>();
        params.put("file_format", "ZPL");

        Shipment shipmentWithLabel = shipment.label(params);

        assertNotNull(shipmentWithLabel.getPostageLabel().getLabelZplUrl());
    }

    /**
     * Create a one-call-buy shipment.
     *
     * @return Shipment object
     */
    private static Shipment createOneCallBuyShipment() throws EasyPostException {
        return Shipment.create(Fixture.oneCallBuyShipment());
    }

    /**
     * Test insuring a Shipment.
     * <p>
     * If the shipment was purchased with a USPS rate, it must have had its
     * insurance set to `0` when bought
     * so that USPS doesn't automatically insure it so we could manually insure it
     * here.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testInsure() throws EasyPostException {
        vcr.setUpTest("insure");

        Map<String, Object> shipmentData = Fixture.oneCallBuyShipment();
        // Set to 0 so USPS doesn't insure this automatically and we can insure the
        // shipment manually.
        shipmentData.put("insurance", 0);

        Map<String, Object> insuranceData = new HashMap<>();
        insuranceData.put("amount", "100");

        Shipment shipment = Shipment.create(shipmentData);

        // TODO: We shouldn't require the end-user to wrap this parameter in a dictionary.
        Shipment shipmentWithInsurance = shipment.insure(insuranceData);

        assertEquals("100.00", shipmentWithInsurance.getInsurance());
    }

    /**
     * Test refunding a Shipment.
     * <p>
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
        vcr.setUpTest("refund");

        Shipment shipment = createOneCallBuyShipment();

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
        vcr.setUpTest("smart_rate");

        Shipment shipment = createBasicShipment();

        assertNotNull(shipment.getRates());
        Rate rate = shipment.getRates().get(0);

        List<Smartrate> smartRates = shipment.smartrates();
        assertInstanceOf(List.class, smartRates);
        Smartrate smartRate = smartRates.get(0);

        assertEquals(rate.getId(), smartRate.getId());
        assertNotNull(smartRate.getTimeInTransit().getPercentile50());
        assertNotNull(smartRate.getTimeInTransit().getPercentile75());
        assertNotNull(smartRate.getTimeInTransit().getPercentile85());
        assertNotNull(smartRate.getTimeInTransit().getPercentile90());
        assertNotNull(smartRate.getTimeInTransit().getPercentile95());
        assertNotNull(smartRate.getTimeInTransit().getPercentile97());
        assertNotNull(smartRate.getTimeInTransit().getPercentile99());
    }

    /**
     * Test creating a Shipment with empty or null objects and arrays.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateEmptyObjects() throws EasyPostException {
        vcr.setUpTest("create_empty_objects");

        Map<String, Object> shipmentData = Fixture.basicShipment();

        Map<String, Object> customsInfo = new HashMap<>();

        customsInfo.put("customs_items", new ArrayList<String>());
        shipmentData.put("customs_info", customsInfo);

        shipmentData.put("options", null);
        shipmentData.put("tax_identifiers", null);
        shipmentData.put("reference", "");

        Shipment shipment = Shipment.create(shipmentData);

        assertInstanceOf(Shipment.class, shipment);
        assertTrue(shipment.getId().startsWith("shp_"));

        assertNotNull(shipment.getOptions()); // The EasyPost API populates some default values here
        assertEquals(0, shipment.getCustomsInfo().getCustomsItems().size());
        assertNull(shipment.getReference());
        assertNull(shipment.getTaxIdentifiers());
    }

    /**
     * Test creating a Shipment with `tax_identifiers`.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateTaxIdentifiers() throws EasyPostException {
        vcr.setUpTest("create_tax_identifiers");

        Map<String, Object> shipmentData = Fixture.basicShipment();

        List<Object> taxIdentifiers = new ArrayList<>();
        taxIdentifiers.add(Fixture.taxIdentifier());
        shipmentData.put("tax_identifiers", taxIdentifiers);

        Shipment shipmentWithTaxIdentifiers = Shipment.create(shipmentData);

        assertInstanceOf(Shipment.class, shipmentWithTaxIdentifiers);
        assertTrue(shipmentWithTaxIdentifiers.getId().startsWith("shp_"));
        assertEquals("IOSS", shipmentWithTaxIdentifiers.getTaxIdentifiers().get(0).getTaxIdType());
    }

    /**
     * Test creating a shipment when only IDs are used.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled
    // TODO: test is for some reason failing to pull a proper recording when playing back. Only test doing this
    public void testCreateWithIds() throws EasyPostException {
        vcr.setUpTest("create_with_ids");

        Address fromAddress = Address.create(Fixture.basicAddress());
        Address toAddress = Address.create(Fixture.basicAddress());
        Parcel parcel = Parcel.create(Fixture.basicParcel());

        Map<String, Object> shipmentData = Fixture.basicShipment();
        shipmentData.put("from_address", Collections.singletonMap("id", fromAddress.getId()));
        shipmentData.put("to_address", Collections.singletonMap("id", toAddress.getId()));
        shipmentData.put("parcel", Collections.singletonMap("id", parcel.getId()));

        Shipment shipment = Shipment.create(shipmentData);

        assertInstanceOf(Shipment.class, shipment);
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
        vcr.setUpTest("lowest_rate");

        Shipment shipment = createFullShipment();

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
    public void testInstanceLowestSmartRate() throws EasyPostException {
        vcr.setUpTest("lowest_smartrate");

        Shipment shipment = createBasicShipment();
        Smartrate lowestSmartRateFilters = shipment.lowestSmartRate(1, SmartrateAccuracy.Percentile90);

        // Test lowest smartrate with valid filters
        assertEquals("First", lowestSmartRateFilters.getService());
        assertEquals(5.49, lowestSmartRateFilters.getRate(), 0.01);
        assertEquals("USPS", lowestSmartRateFilters.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict delivery days)
        assertThrows(EasyPostException.class, () -> {
            shipment.lowestSmartRate(0, SmartrateAccuracy.Percentile90);
        });
    }

    /**
     * Test getting the lowest smartrate from a list of smartrates.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testStaticLowestSmartRates() throws EasyPostException {
        vcr.setUpTest("lowest_smartrate_list");

        Shipment shipment = createBasicShipment();
        List<Smartrate> smartrates = shipment.smartrates();

        // Test lowest smartrate with valid filters
        Smartrate lowestSmartRate = Shipment.findLowestSmartrate(smartrates, 1, SmartrateAccuracy.Percentile90);
        assertEquals("First", lowestSmartRate.getService());
        assertEquals(5.49, lowestSmartRate.getRate(), 0.01);
        assertEquals("USPS", lowestSmartRate.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict delivery days)
        assertThrows(EasyPostException.class, () -> {
            Shipment.findLowestSmartrate(smartrates, 0, SmartrateAccuracy.Percentile90);
        });
    }

    /**
     * Test generating a form from a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGenerateForm() throws EasyPostException {
        vcr.setUpTest("generate_form");

        Shipment shipment = createOneCallBuyShipment();
        String formType = "return_packing_slip";

        shipment.generateForm(formType, Fixture.rmaFormOptions());

        assertTrue(shipment.getForms().size() > 0);

        Form form = shipment.getForms().get(0);
        
        assertEquals(formType, form.getFormType());
        assertTrue(form.getFormUrl() != null);
    }
}
