package com.easypost;

import com.easypost.exception.API.ExternalApiError;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.PaymentMethod;
import com.easypost.model.PaymentMethodObject;
import com.easypost.model.ReferralCustomer;
import com.easypost.model.ReferralCustomerCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class ReferralTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("referral", TestUtils.ApiKey.PARTNER);
    }

    /**
     * Return the referral user key from the system environment.
     *
     * @return Referral user key.
     */
    private static String referralUserKey() {
        return TestUtils.getApiKey(TestUtils.ApiKey.REFERRAL);
    }

    /**
     * Create a referral.
     *
     * @return Referral object
     */
    private static ReferralCustomer createReferral() throws EasyPostException {
        return vcr.client.referralCustomer.create(Fixtures.referralUser());
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

        assertDoesNotThrow(() -> vcr.client.referralCustomer.updateEmail("email@example.com", referralUser.getId()));
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
        params.put("page_size", Fixtures.pageSize());

        ReferralCustomerCollection referralCustomerCollection = vcr.client.referralCustomer.all(params);

        List<ReferralCustomer> referralUsers = referralCustomerCollection.getReferralCustomers();

        assertTrue(referralUsers.size() <= Fixtures.pageSize());
        assertNotNull(referralCustomerCollection.getHasMore());
        assertTrue(referralUsers.stream().allMatch(referral -> referral instanceof ReferralCustomer));
    }

    /**
     * Test retrieving the next page.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("get_next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        ReferralCustomerCollection collection = vcr.client.referralCustomer.all(params);

        try {
            ReferralCustomerCollection nextPage =
                    vcr.client.referralCustomer.getNextPage(collection, Fixtures.pageSize());

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getReferralCustomers().get(0).getId();
            String firstIdOfSecondPage = nextPage.getReferralCustomers().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }

    /**
     * Test adding a credit card to a Referral user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testReferralAddCreditCard() throws Exception {
        vcr.setUpTest("referral_add_credit_card");

        Map<String, Object> creditCardDetails = Fixtures.creditCardDetails();
        PaymentMethodObject creditCard = vcr.client.referralCustomer.addCreditCardToUser(referralUserKey(),
                (String) creditCardDetails.get("number"),
                Integer.parseInt((String) creditCardDetails.get("expiration_month")),
                Integer.parseInt((String) creditCardDetails.get("expiration_year")),
                (String) creditCardDetails.get("cvc"), PaymentMethod.Priority.PRIMARY);

        assertInstanceOf(PaymentMethodObject.class, creditCard);
        assertTrue(creditCard.getId().startsWith("card_"));
        assertEquals(((String) Fixtures.creditCardDetails().get("number")).substring(12), creditCard.getLast4());
    }

    /**
     * Test creating bad Stripe token with invalid input parameters.
     *
     * @throws Exception when the request fails.
     */
    @Test
    public void testCreateBadStripeToken() throws Exception {
        vcr.setUpTest("create_bad_stripe_token");

        assertThrows(ExternalApiError.class,
                () -> vcr.client.referralCustomer.addCreditCardToUser(referralUserKey(), "1234", 1234, 1234, "1234",
                        PaymentMethod.Priority.PRIMARY));
    }
}
