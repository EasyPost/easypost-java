package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.CreditCard;
import com.easypost.model.CreditCardPriority;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CreditCardTest {
    private static TestUtils.VCR vcr;

    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("credit_card", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test funding a credit card.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // Skipping due to the lack of an available real credit card in tests
    public void testFund() throws EasyPostException {
        vcr.setUpTest("fund");
        assertTrue(CreditCard.fund("2000", CreditCardPriority.PRIMARY));
    }

    /**
     * Test deleting a credit card.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // Skipping due to the lack of an available real credit card in tests
    public void testDelete() throws EasyPostException {
        vcr.setUpTest("delete");
        CreditCard.delete("card_123");
    }
}
