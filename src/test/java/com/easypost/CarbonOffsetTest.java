package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Fee;
import com.easypost.model.Rate;
import com.easypost.model.Shipment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CarbonOffsetTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("carbon_offset", TestUtils.ApiKey.TEST);
    }

    /**
     * Test creating a shipment with a carbon offset.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateShipmentWithCarbonOffset() throws EasyPostException {
        vcr.setUpTest("create_shipment_with_carbon_offset");

        Shipment shipment = Shipment.create(Fixture.basicCarbonOffsetShipment(), true);

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

        Shipment shipment = Shipment.create(Fixture.fullCarbonOffsetShipment());

        Rate rate = shipment.lowestRate();

        Shipment boughtShipment = shipment.buy(rate, true);

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

        Shipment shipment = Shipment.create(Fixture.oneCallBuyCarbonOffsetShipment(), true);

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

        Shipment shipment = Shipment.create(Fixture.oneCallBuyCarbonOffsetShipment());
        List<Rate> baseRates = shipment.getRates();

        Shipment shipmentWithNewRatesWithCarbon = shipment.newRates(true);
        List<Rate> newCarbonRates = shipmentWithNewRatesWithCarbon.getRates();

        Shipment shipmentWithNewRatesWithoutCarbon = shipment.newRates(false);
        List<Rate> newNonCarbonRates = shipmentWithNewRatesWithoutCarbon.getRates();

        Rate baseRate = baseRates.get(0);
        Rate newCarbonRate = newCarbonRates.get(0);
        Rate newNonCarbonRate = newNonCarbonRates.get(0);

        assertNull(baseRate.getCarbonOffset());
        assertNotNull(newCarbonRate.getCarbonOffset());
        assertNull(newNonCarbonRate.getCarbonOffset());
    }
}
