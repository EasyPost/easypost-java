package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.http.Requestor;
import com.easypost.model.AddressCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class PaginatedCollectionTest {
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the setup fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        vcr = new TestUtils.VCR("paginated_collections", TestUtils.ApiKey.TEST);
    }

    /**
     * Test getting the next page of a paginated collection.
     * This getNextPage method is implemented on a per-service,
     * but rather than testing in each service, we'll test it once here.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        AddressCollection addresses = vcr.client.address.all(params);

        AddressCollection nextPage = vcr.client.address.getNextPage(addresses);

        assertNotNull(nextPage);

        String firstIdOfFirstPage = addresses.getAddresses().get(0).getId();
        String firstIdOfSecondPage = nextPage.getAddresses().get(0).getId();

        assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
    }

    /**
     * Test that the getNextPage method works with page limits.
     * This method is implemented on a per-service, but rather than testing in each service, we'll test it once here..
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPageSizeLimit() throws EasyPostException {
        vcr.setUpTest("next_page_size_limit");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        AddressCollection addresses = vcr.client.address.all(params);

        AddressCollection nextPage = vcr.client.address.getNextPage(addresses, Fixtures.pageSize());

        assertNotNull(nextPage);

        assertTrue(nextPage.getAddresses().size() <= Fixtures.pageSize());
    }

    /**
     * Test that the getNextPage method will throw an EndOfPaginationError when it reaches the end of the list.
     * This method is implemented on a per-service, but rather than testing in each service, we'll test it once here.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPageReachEnd() throws EasyPostException {
        vcr.setUpTest("next_page_reach_end");

        final MockedStatic<Requestor> requestMock = Mockito.mockStatic(Requestor.class);

        String fakeData = "{ \"addresses\": [], \"has_more\": false }";
        AddressCollection fakeCollection = Constants.Http.GSON.fromJson(fakeData, AddressCollection.class);

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        requestMock.when(
                        () -> Requestor.request(
                                Requestor.RequestMethod.GET, "addresses",
                                params, AddressCollection.class, vcr.client))
                .thenReturn(fakeCollection);

        AddressCollection collection = vcr.client.address.all(params);

        boolean hitEnd = false;

        while (!hitEnd) {
            try {
                collection = vcr.client.address.getNextPage(collection);
            } catch (EndOfPaginationError e) {  // only catch the error we're expecting
                hitEnd = true;
            }
        }

        assertTrue(hitEnd);

        requestMock.close();
    }
}
