package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.InvalidParameterError;
import com.easypost.model.Address;
import com.easypost.model.EndShipper;
import com.easypost.model.Fee;
import com.easypost.model.Form;
import com.easypost.model.Parcel;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import com.easypost.model.ShipmentCollection;
import com.easypost.model.SmartRate;
import com.easypost.model.SmartrateAccuracy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Create a basic shipment.
     *
     * @return Shipment object
     */
    private static Shipment createBasicShipment() throws EasyPostException {
        return vcr.client.shipment.create(Fixtures.basicShipment());
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
        return vcr.client.shipment.create(Fixtures.fullShipment());
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

        Shipment retrievedShipment = vcr.client.shipment.retrieve(shipment.getId());

        assertInstanceOf(Shipment.class, retrievedShipment);
        assertTrue(shipment.equals(retrievedShipment));
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
        params.put("page_size", Fixtures.pageSize());

        ShipmentCollection shipmentCollection = vcr.client.shipment.all(params);

        List<Shipment> shipments = shipmentCollection.getShipments();

        assertTrue(shipments.size() <= Fixtures.pageSize());
        assertNotNull(shipmentCollection.getHasMore());
        assertTrue(shipments.stream().allMatch(shipment -> shipment instanceof Shipment));
    }

    /**
     * Test buying a shipment with lowest rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyWithRate() throws EasyPostException {
        vcr.setUpTest("buy_with_rate");

        Shipment shipment = createBasicShipment();

        Shipment boughtShipment = vcr.client.shipment.buy(shipment.getId(), shipment.lowestRate());

        assertNotNull(boughtShipment.getPostageLabel());
    }

    /**
     * Test buying a shipment with params.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyWithParams() throws EasyPostException {
        vcr.setUpTest("buy_with_params");

        Shipment shipment = createBasicShipment();
        HashMap<String, Object> buyMap = new HashMap<String, Object>();
        buyMap.put("rate", shipment.lowestRate());
        buyMap.put("insurance", 249.99);

        Shipment boughtShipment = vcr.client.shipment.buy(shipment.getId(), buyMap);

        assertNotNull(boughtShipment.getPostageLabel());
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

        Shipment shipmentWithNewRates = vcr.client.shipment.newRates(shipment.getId());

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

        Shipment shipmentWithLabel = vcr.client.shipment.label(shipment.getId(), params);

        assertNotNull(shipmentWithLabel.getPostageLabel().getLabelZplUrl());
    }

    /**
     * Create a one-call-buy shipment.
     *
     * @return Shipment object
     */
    private static Shipment createOneCallBuyShipment() throws EasyPostException {
        return vcr.client.shipment.create(Fixtures.oneCallBuyShipment());
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

        Map<String, Object> shipmentData = Fixtures.oneCallBuyShipment();
        // Set to 0 so USPS doesn't insure this automatically and we can insure the
        // shipment manually.
        shipmentData.put("insurance", 0);

        Map<String, Object> insuranceData = new HashMap<>();
        insuranceData.put("amount", "100");

        Shipment shipment = vcr.client.shipment.create(shipmentData);

        Shipment shipmentWithInsurance = vcr.client.shipment.insure(shipment.getId(), insuranceData);

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

        Shipment refundedShipment = vcr.client.shipment.refund(shipment.getId());

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

        List<SmartRate> smartRates = vcr.client.shipment.smartrates(shipment.getId());
        assertInstanceOf(List.class, smartRates);
        SmartRate smartRate = smartRates.get(0);

        assertEquals(rate.getId(), smartRate.getId());
        assertNotNull(smartRate.getTimeInTransit().getPercentile50());
        assertNotNull(smartRate.getTimeInTransit().getPercentile75());
        assertNotNull(smartRate.getTimeInTransit().getPercentile85());
        assertNotNull(smartRate.getTimeInTransit().getPercentile90());
        assertNotNull(smartRate.getTimeInTransit().getPercentile95());
        assertNotNull(smartRate.getTimeInTransit().getPercentile97());
        assertNotNull(smartRate.getTimeInTransit().getPercentile99());

        assertThrows(InvalidParameterError.class,
                () -> smartRate.getTimeInTransit().getSmartRateAccuracy("percentile_100"));
    }

    /**
     * Test creating a Shipment with empty or null objects and arrays.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateEmptyObjects() throws EasyPostException {
        vcr.setUpTest("create_empty_objects");

        Map<String, Object> shipmentData = Fixtures.basicShipment();

        Map<String, Object> customsInfo = new HashMap<>();

        customsInfo.put("customs_items", new ArrayList<String>());
        shipmentData.put("customs_info", customsInfo);

        shipmentData.put("options", null);
        shipmentData.put("tax_identifiers", null);
        shipmentData.put("reference", "");

        Shipment shipment = vcr.client.shipment.create(shipmentData);

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

        Map<String, Object> shipmentData = Fixtures.basicShipment();

        List<Object> taxIdentifiers = new ArrayList<>();
        taxIdentifiers.add(Fixtures.taxIdentifier());
        shipmentData.put("tax_identifiers", taxIdentifiers);

        Shipment shipmentWithTaxIdentifiers = vcr.client.shipment.create(shipmentData);

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
    public void testCreateWithIds() throws EasyPostException {
        vcr.setUpTest("create_with_ids");

        // VCR will overwrite the first address recording if the parameters are the exact same,
        // which will cause us to lose the response from the first address creation and cause the replay to fail.
        // So wee need to use two different addresses here.
        Address fromAddress = vcr.client.address.create(Fixtures.caAddress1());
        Address toAddress = vcr.client.address.create(Fixtures.caAddress2());
        Parcel parcel = vcr.client.parcel.create(Fixtures.basicParcel());

        Map<String, Object> shipmentData = Fixtures.basicShipment();
        shipmentData.put("from_address", Collections.singletonMap("id",
                fromAddress.getId()));
        shipmentData.put("to_address", Collections.singletonMap("id",
                toAddress.getId()));
        shipmentData.put("parcel", Collections.singletonMap("id", parcel.getId()));

        Shipment shipment = vcr.client.shipment.create(shipmentData);

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
        assertEquals(5.82, lowestRate.getRate(), 0.01);
        assertEquals("USPS", lowestRate.getCarrier());

        // Test lowest rate with service filter (this rate is higher than the lowest but
        // should filter)
        List<String> service = new ArrayList<>(Arrays.asList("Priority"));
        Rate lowestRateService = shipment.lowestRate(null, service);
        assertEquals("Priority", lowestRateService.getService());
        assertEquals(8.15, lowestRateService.getRate(), 0.01);
        assertEquals("USPS", lowestRateService.getCarrier());

        // Test lowest rate with carrier filter (should error due to bad carrier)
        List<String> carrier = new ArrayList<>(Arrays.asList("BAD CARRIER"));
        assertThrows(EasyPostException.class, () -> {
            shipment.lowestRate(carrier, null);
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
        SmartRate lowestSmartRateFilters = vcr.client.shipment.lowestSmartRate(shipment.getId(), 2,
                SmartrateAccuracy.Percentile90);

        // Test lowest smartrate with valid filters
        assertEquals("Priority", lowestSmartRateFilters.getService());
        assertEquals(8.15, lowestSmartRateFilters.getRate(), 0.01);
        assertEquals("USPS", lowestSmartRateFilters.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict
        // delivery days)
        assertThrows(EasyPostException.class, () -> {
            vcr.client.shipment.lowestSmartRate(shipment.getId(), 0, SmartrateAccuracy.Percentile90);
        });

        SmartRate deprecatedLowestSmartRateFilters = vcr.client.shipment.lowestSmartRate(shipment.getId(), 2,
                "percentile_90");

        // Test lowest smartrate with valid filters
        assertEquals("Priority", deprecatedLowestSmartRateFilters.getService());
        assertEquals(8.15, deprecatedLowestSmartRateFilters.getRate(), 0.01);
        assertEquals("USPS", deprecatedLowestSmartRateFilters.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict
        // delivery days)
        assertThrows(EasyPostException.class, () -> {
            vcr.client.shipment.lowestSmartRate(shipment.getId(), 0, SmartrateAccuracy.Percentile90);
        });
    }

    /**
     * Test getting smart rates for a shipment.
     * 
     * @throws EasyPostException
     */
    @Test
    public void testGetSmartRate() throws EasyPostException {
        vcr.setUpTest("get_smartrate_list");

        Shipment shipment = createBasicShipment();

        List<SmartRate> rates = vcr.client.shipment.getSmartrates(shipment.getId());

        assertInstanceOf(List.class, rates);

        for (SmartRate rate : rates) {
            assertInstanceOf(SmartRate.class, rate);
        }
    }

    /**
     * Test retriving lowest smart rate.
     *
     * @throws EasyPostException
     */
    @Test
    public void testGetLowestSmartRate() throws EasyPostException {
        vcr.setUpTest("get_lowest_smartrate");

        Shipment shipment = createBasicShipment();

        List<SmartRate> rates = vcr.client.shipment.getSmartrates(shipment.getId());
        SmartRate lowestSmartrate = vcr.client.shipment.getLowestSmartRate(rates, 3, "percentile_85");

        assertEquals("First", lowestSmartrate.getService());
        assertEquals(5.82, lowestSmartrate.getRate(), 0.01);
        assertEquals("USPS", lowestSmartrate.getCarrier());
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
        List<SmartRate> smartRates = vcr.client.shipment.smartrates(shipment.getId());

        // Test lowest smartrate with valid filters
        SmartRate lowestSmartRate = vcr.client.shipment.findLowestSmartrate(smartRates, 2,
                SmartrateAccuracy.Percentile90);
        assertEquals("Priority", lowestSmartRate.getService());
        assertEquals(8.15, lowestSmartRate.getRate(), 0.01);
        assertEquals("USPS", lowestSmartRate.getCarrier());

        // Test lowest smartrate with invalid filters (should error due to strict
        // delivery days)
        assertThrows(EasyPostException.class, () -> {
            vcr.client.shipment.findLowestSmartrate(smartRates, 0, SmartrateAccuracy.Percentile90);
        });
    }

    /**
     * Test generating a form from a shipment.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGenerateForm() throws EasyPostException {
        vcr.setUpTest("generate_form_with");

        Shipment shipment = createOneCallBuyShipment();
        String formType = "return_packing_slip";

        Shipment shipmentWithForm = vcr.client.shipment.generateForm(shipment.getId(), formType);

        assertTrue(shipmentWithForm.getForms().size() > 0);

        Form form = shipmentWithForm.getForms().get(0);

        assertEquals(formType, form.getFormType());
        assertTrue(form.getFormUrl() != null);
    }

    /**
     * Test generating a form from a shipment with option.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGenerateFormWithOption() throws EasyPostException {
        vcr.setUpTest("generate_form_with_option");

        Shipment shipment = createOneCallBuyShipment();
        String formType = "return_packing_slip";

        Shipment shipmentWithForm = vcr.client.shipment.generateForm(shipment.getId(), formType,
                Fixtures.rmaFormOptions());

        assertTrue(shipmentWithForm.getForms().size() > 0);

        Form form = shipmentWithForm.getForms().get(0);

        assertEquals(formType, form.getFormType());
        assertTrue(form.getFormUrl() != null);
    }

    /**
     * Test creating a shipment with a carbon offset.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateShipmentWithCarbonOffset() throws EasyPostException {
        vcr.setUpTest("create_shipment_with_carbon_offset");

        Shipment shipment = vcr.client.shipment.create(Fixtures.basicShipment(), true);

        assertInstanceOf(Shipment.class, shipment);

        List<Rate> rates = shipment.getRates();
        assertNotNull(rates);

        Rate rate = rates.get(0);
        assertNotNull(rate.getCarbonOffset());
        assertNotNull(rate.getCarbonOffset().getPrice());
    }

    /**
     * Test buying a shipment with a carbon offset.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyShipmentWithCarbonOffset() throws EasyPostException {
        vcr.setUpTest("buy_shipment_with_carbon_offset");

        Shipment shipment = vcr.client.shipment.create(Fixtures.fullShipment());

        Shipment boughtShipment = vcr.client.shipment.buy(shipment.getId(), shipment.lowestRate(), true);

        assertInstanceOf(Shipment.class, shipment);

        List<Fee> fees = boughtShipment.getFees();
        assertNotNull(fees);

        boolean foundCarbonOffset = false;
        for (Fee fee : fees) {
            if (fee.getType().equals("CarbonOffsetFee")) {
                foundCarbonOffset = true;
                break;
            }
        }
        assertTrue(foundCarbonOffset);
    }

    /**
     * Test one-call buying a shipment with a carbon offset.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testOneCallBuyShipmentWithCarbonOffset() throws EasyPostException {
        vcr.setUpTest("one_call_buy_shipment_with_carbon_offset");

        Shipment shipment = vcr.client.shipment.create(Fixtures.oneCallBuyShipment(), true);

        assertInstanceOf(Shipment.class, shipment);

        List<Fee> fees = shipment.getFees();
        assertNotNull(fees);

        boolean foundCarbonOffset = false;
        for (Fee fee : fees) {
            if (fee.getType().equals("CarbonOffsetFee")) {
                foundCarbonOffset = true;
                break;
            }
        }
        assertTrue(foundCarbonOffset);
    }

    /**
     * Test re-rating a shipment with a carbon offset.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRegenerateRatesWithCarbonOffset() throws EasyPostException {
        vcr.setUpTest("regenerate_rates_with_carbon_offset");

        Shipment shipment = vcr.client.shipment.create(Fixtures.oneCallBuyShipment());
        List<Rate> baseRates = shipment.getRates();

        Shipment shipmentWithNewRatesWithCarbon = vcr.client.shipment.newRates(shipment.getId(), true);
        List<Rate> newCarbonRates = shipmentWithNewRatesWithCarbon.getRates();

        Rate baseRate = baseRates.get(0);
        Rate newCarbonRate = newCarbonRates.get(0);

        assertNull(baseRate.getCarbonOffset());
        assertNotNull(newCarbonRate.getCarbonOffset());
    }

    /**
     * Test buying a shipment with an EndShipper ID with lowest rate.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyShipmentWithEndShipperIdWithRate() throws EasyPostException {
        vcr.setUpTest("buy_shipment_with_end_shipper_id_with_rate");

        EndShipper endShipper = vcr.client.endShipper.create(Fixtures.caAddress1());

        Shipment shipment = vcr.client.shipment.create(Fixtures.basicShipment());
        Shipment boughtShipment = vcr.client.shipment.buy(shipment.getId(), shipment.lowestRate(), endShipper.getId());

        assertNotNull(boughtShipment.getPostageLabel());
    }

    /**
     * Test buying a shipment with an EndShipper ID with params.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testBuyShipmentWithEndShipperId() throws EasyPostException {
        vcr.setUpTest("buy_shipment_with_end_shipper_id_with_rate_with_params");

        EndShipper endShipper = vcr.client.endShipper.create(Fixtures.caAddress1());

        Shipment shipment = vcr.client.shipment.create(Fixtures.basicShipment());
        HashMap<String, Object> buyMap = new HashMap<String, Object>();
        buyMap.put("rate", shipment.lowestRate());
        buyMap.put("insurance", 249.99);

        Shipment boughtShipment = vcr.client.shipment.buy(shipment.getId(), buyMap, endShipper.getId());

        assertNotNull(boughtShipment.getPostageLabel());
    }
}
