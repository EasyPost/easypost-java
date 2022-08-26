package com.easypost.beta;

import com.easypost.Fixture;
import com.easypost.TestUtils;
import com.easypost.exception.EasyPostException;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import com.easypost.model.beta.ReferralCustomer;
import com.easypost.model.beta.ReferralCustomerCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ReferralTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("referral", TestUtils.ApiKey.REFERRAL);
    }

    private static String referralUserKey() {
        return TestUtils.getApiKey(TestUtils.ApiKey.REFERRAL);
    }

    /**
     * Create a referral.
     *
     * @return Referral object
     */
    private static ReferralCustomer createReferral() throws EasyPostException {
        return ReferralCustomer.create(Fixture.referralUser());
    }

    /**
     * Test creating a Referral object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        ReferralCustomer referralUser = createReferral();

        assertInstanceOf(ReferralCustomer.class, referralUser);
        assertTrue(referralUser.getId().startsWith("user_"));
        assertEquals("Test Referral", referralUser.getName());
    }

    /**
     * Test updating a Referral object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        ReferralCustomer referralUser = createReferral();
        boolean response = ReferralCustomer.updateEmail("email@example.com", referralUser.getId());

        assertTrue(response);
    }

    /**
     * Test retrieving all Referral objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        vcr.setUpTest("all");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page_size", Fixture.pageSize());

        ReferralCustomerCollection referralCustomerCollection = ReferralCustomer.all(params);

        List<ReferralCustomer> referralUsers = referralCustomerCollection.getReferralCustomers();

        assertTrue(referralUsers.size() <= Fixture.pageSize());
        assertNotNull(referralCustomerCollection.getHasMore());
        assertTrue(referralUsers.stream().allMatch(referral -> referral instanceof ReferralCustomer));
    }

    /**
     * Test adding a credit card to a Referral user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // failing on replay likely because of urlencoding
    public void testReferralAddCreditCard() throws Exception {
        vcr.setUpTest("referral_add_credit_card");

        Map<String, Object> creditCardDetails = Fixture.creditCardDetails();
        PaymentMethodObject creditCard =
                ReferralCustomer.addCreditCardToUser(referralUserKey(), (String) creditCardDetails.get("number"),
                        Integer.parseInt((String) creditCardDetails.get("expiration_month")),
                        Integer.parseInt((String) creditCardDetails.get("expiration_year")),
                        (String) creditCardDetails.get("cvc"), PaymentMethod.Priority.PRIMARY);

        assertInstanceOf(PaymentMethodObject.class, creditCard);
        assertTrue(creditCard.getId().startsWith("card_"));
        assertEquals(((String) Fixture.creditCardDetails().get("number")).substring(12), creditCard.getLast4());
    }
}
