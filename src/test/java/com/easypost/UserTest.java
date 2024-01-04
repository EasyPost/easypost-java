package com.easypost;

import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.EndOfPaginationError;
import com.easypost.model.Brand;
import com.easypost.model.ChildUser;
import com.easypost.model.ChildUserCollection;
import com.easypost.model.User;
import org.junit.jupiter.api.AfterEach;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class UserTest {
    private static String testUserId = null;
    private static TestUtils.VCR vcr;

    /**
     * Set up the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setUp() throws EasyPostException {
        vcr = new TestUtils.VCR("user", TestUtils.ApiKey.PRODUCTION);
    }

    /**
     * Clean up test attributes after each unit test.
     */
    @AfterEach
    public void cleanup() {
        if (testUserId != null) {
            try {
                User user = vcr.client.user.retrieve(testUserId);
                vcr.client.user.delete(user.getId());
                testUserId = null;
            } catch (Exception e) {
                // in case we try to delete something that's already been deleted
            }
        }
    }

    /**
     * Test creating a child user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException {
        vcr.setUpTest("create");

        User user = createUser();

        assertInstanceOf(User.class, user);
        assertTrue(user.getId().startsWith("user_"));
        assertEquals("Test User", user.getName());
    }

    /**
     * Create a user.
     *
     * @return User object
     */
    private static User createUser() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Test User");
        User user = vcr.client.user.create(params);
        testUserId = user.getId(); // trigger deletion after test
        return user;
    }

    /**
     * Test retrieving a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        vcr.setUpTest("retrieve");

        User authenticatedUser = retrieveMe();

        String userId = authenticatedUser.getId();

        User user = vcr.client.user.retrieve(userId);

        assertInstanceOf(User.class, user);
        assertTrue(user.getId().startsWith("user_"));
        assertEquals(userId, user.getId());
    }

    /**
     * Retrieve current user.
     *
     * @return User object
     */
    private static User retrieveMe() throws EasyPostException {
        return vcr.client.user.retrieveMe();
    }

    /**
     * Test retrieving user itself.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieveMe() throws EasyPostException {
        vcr.setUpTest("retrieve_me");

        User user = vcr.client.user.retrieveMe();

        assertInstanceOf(User.class, user);
        assertTrue(user.getId().startsWith("user_"));
    }

    /**
     * Test updating a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdate() throws EasyPostException {
        vcr.setUpTest("update");

        User user = createUser();

        String testName = "New Name";
        Map<String, Object> params = new HashMap<>();

        params.put("name", testName);

        User updatedUser = vcr.client.user.update(user.getId(), params);

        assertInstanceOf(User.class, updatedUser);
        assertTrue(updatedUser.getId().startsWith("user_"));
        assertEquals(testName, updatedUser.getName());
    }

    /**
     * Test deleting a user.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testDelete() throws EasyPostException {
        vcr.setUpTest("delete");

        User user = createUser();

        assertDoesNotThrow(() -> vcr.client.user.delete(user.getId()));
    }

    /**
     * Test updating a brand.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testUpdateBrand() throws EasyPostException {
        vcr.setUpTest("update_brand");

        User user = createUser();

        String color = "#123456";

        Map<String, Object> params = new HashMap<>();
        params.put("color", color);

        Brand brand = vcr.client.user.updateBrand(user.getId(), params);

        assertInstanceOf(Brand.class, brand);
        assertTrue(brand.getId().startsWith("brd_"));
        assertEquals(color, brand.getColor());
    }

    /**
     * Test retrieving a paginated list of children.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAllChildren() throws EasyPostException {
        vcr.setUpTest("all_children");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());

        ChildUserCollection children = vcr.client.user.allChildren(params);

        List<ChildUser> childrenList = children.getChildren();

        assertTrue(childrenList.size() <= Fixtures.pageSize());
        assertNotNull(children.getHasMore());
        assertTrue(childrenList.stream().allMatch(children_user -> children_user != null));
    }

    /**
     * Test retrieving the next page of child users.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testGetNextPage() throws EasyPostException {
        vcr.setUpTest("get_next_page");

        Map<String, Object> params = new HashMap<>();
        params.put("page_size", Fixtures.pageSize());
        ChildUserCollection collection = vcr.client.user.allChildren(params);

        try {
            ChildUserCollection nextPage = vcr.client.user.getNextPage(collection, Fixtures.pageSize());

            assertNotNull(nextPage);

            String firstIdOfFirstPage = collection.getChildren().get(0).getId();
            String firstIdOfSecondPage = nextPage.getChildren().get(0).getId();

            assertNotEquals(firstIdOfFirstPage, firstIdOfSecondPage);
        } catch (EndOfPaginationError e) { // There's no next page, that's not a failure
            assertTrue(true);
        } catch (Exception e) { // Any other exception is a failure
            fail();
        }
    }
}
