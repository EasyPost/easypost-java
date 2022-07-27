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
     * Test buying a shipment with a carbon offset.
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
        assertInstanceOf(Shipment.class, shipment);

        List<Rate> rates = shipment.getRates();
        assertNotNull(rates);

        Shipment shipmentWithNewRates = shipment.newRates(true);

        List<Rate> newRates = shipmentWithNewRates.getRates();
        assertNotNull(newRates);

        Rate oldRate = rates.get(0);
        Rate newRate = newRates.get(0);

        assertNull(oldRate.getCarbonOffset());
        assertNotNull(newRate.getCarbonOffset());
    }
}
