package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.ChildUserCollection;
import com.easypost.model.CustomerPortalAccountLink;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class CustomerPortalTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("customer_portal", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test creating a Portal Session.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAccountLink() throws EasyPostException {
        vcr.setUpTest("create_account_link");

        HashMap<String, Object> userParams = new HashMap<>();
        userParams.put("page_size", Fixtures.pageSize());
        ChildUserCollection children = vcr.client.user.allChildren(userParams);

        HashMap<String, Object> params = new HashMap<>();
        params.put("session_type", "account_onboarding");
        params.put("user_id", children.getChildren().get(0).getId());
        params.put("refresh_url", "https://example.com/refresh");
        params.put("return_url", "https://example.com/return");
        CustomerPortalAccountLink accountLink = vcr.client.customerPortal.createAccountLink(params);

        assertEquals("CustomerPortalAccountLink", accountLink.getObject());
    }
}
