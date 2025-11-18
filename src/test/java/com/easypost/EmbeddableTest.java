package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.model.ChildUserCollection;
import com.easypost.model.EmbeddablesSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class EmbeddableTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("embeddable", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Test creating an Embeddables Session.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateSession() throws EasyPostException {
        vcr.setUpTest("create_session");

        HashMap<String, Object> userParams = new HashMap<>();
        userParams.put("page_size", Fixtures.pageSize());
        ChildUserCollection children = vcr.client.user.allChildren(userParams);

        HashMap<String, Object> params = new HashMap<>();
        params.put("origin_host", "https://example.com");
        params.put("user_id", children.getChildren().get(0).getId());
        EmbeddablesSession session = vcr.client.embeddable.createSession(params);

        assertEquals("EmbeddablesSession", session.getObject());
    }
}
