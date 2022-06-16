package com.easypost.beta;

import com.easypost.Fixture;
import com.easypost.TestUtils;
import com.easypost.exception.EasyPostException;
import com.easypost.model.beta.CreditCard;
import com.easypost.model.beta.Referral;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ReferralTest {
    private static final String REFERRAL_USER_PROD_API_KEY =
            System.getenv("REFERRAL_USER_PROD_API_KEY") == null ? "123" : System.getenv("REFERRAL_USER_PROD_API_KEY");
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("referral", REFERRAL_USER_PROD_API_KEY);
    }

    /**
     * Test creating a Referral object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        Referral referralUser = createReferral();

        assertInstanceOf(Referral.class, referralUser);
        assertTrue(referralUser.getId().startsWith("user_"));
        assertEquals(Fixture.referralUser().get("name"), referralUser.getName());
    }

    /**
     * Create a referral.
     *
     * @return Referral object
     */
    private static Referral createReferral() throws EasyPostException {
        return Referral.create(Fixture.referralUser());
    }

    /**
     * Test updating a Referral object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        Referral referralUser = createReferral();
        boolean response = Referral.updateEmail("email@example.com", referralUser.getId());

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

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixture.pageSize());

        List<Referral> referralUsers = Referral.all(params);

        assertTrue(referralUsers.size() <= Fixture.pageSize());
        assertTrue(referralUsers.stream().allMatch(referral -> referral instanceof Referral));
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

        Map<String, String> creditCardDetails = Fixture.creditCardDetails();
        CreditCard creditCard = Referral.addCreditCard(REFERRAL_USER_PROD_API_KEY, creditCardDetails.get("number"),
                Integer.parseInt(creditCardDetails.get("expiration_month")),
                Integer.parseInt(creditCardDetails.get("expiration_year")), creditCardDetails.get("cvc"),
                Referral.Priority.PRIMARY);

        assertInstanceOf(CreditCard.class, creditCard);
        assertTrue(creditCard.getId().startsWith("card_"));
        assertEquals(Fixture.creditCardDetails().get("number").substring(12), creditCard.getLast4());
    }
}
