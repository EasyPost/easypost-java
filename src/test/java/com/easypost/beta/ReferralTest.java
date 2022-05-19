package com.easypost.beta;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.EasyPost;
import com.easypost.Fixture;
import com.easypost.exception.EasyPostException;
import com.easypost.model.beta.CreditCard;
import com.easypost.model.beta.Referral;
import com.easypost.model.beta.Referral.Priority;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class ReferralTest {
    private static String REFERRAL_USER_PROD_API_KEY = System.getenv("REFERRAL_USER_PROD_API_KEY")
        == null? "123": System.getenv("REFERRAL_USER_PROD_API_KEY");
    
    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_PROD_API_KEY");;
    }

    /**
     * Test creating a Referral object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        Referral referralUser = Referral.create(Fixture.referralUser());

        assertTrue(referralUser instanceof Referral);
        assertTrue(referralUser.getId().startsWith("user_"));
        assertEquals("8888888888", referralUser.getPhoneNumber());
    }

    /**
     * Test updating a Referral object.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        Referral referralUser = Referral.create(Fixture.referralUser());
        boolean response = Referral.updateEmail("email@example.com", referralUser.getId());

        assertTrue(response == true);
    }

    /**
     * Test retrieving all Referral objects.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
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
    public void testReferralAddCreditCard() throws Exception {
        Map<String, String> creditCardDetails = Fixture.creditCardDetails();
        CreditCard creditCard = Referral.addCreditCard(REFERRAL_USER_PROD_API_KEY, creditCardDetails.get("number"),
            Integer.parseInt(creditCardDetails.get("expiration_month")),
            Integer.parseInt(creditCardDetails.get("expiration_year")), creditCardDetails.get("cvc"), Referral.Priority.PRIMARY);

        assertTrue(creditCard instanceof CreditCard);
        assertTrue(creditCard.getId().startsWith("card_"));
        assertEquals("6170", creditCard.getLast4());
    }
}
