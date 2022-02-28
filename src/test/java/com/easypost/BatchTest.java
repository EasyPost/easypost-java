package com.easypost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.easypost.exception.EasyPostException;
import com.easypost.model.Batch;
import com.easypost.model.BatchCollection;
import com.easypost.model.Shipment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class BatchTest {
    private static Batch globalBatch;
    private static Map<String, Object> params = new HashMap<>();
    
    /**
     * Setup the testing environment for this file.
     *
     * @throws EasyPostException when the request fails.
     */
    @BeforeAll
    public static void setup() throws EasyPostException {
        EasyPost.apiKey = System.getenv("EASYPOST_TEST_API_KEY");

        List<Object> shipments = new ArrayList<>();

        shipments.add(Fixture.oneCallBuyShipment());
        params.put("shipments", shipments);

        globalBatch = Batch.create(params);
    }

    /**
     * Test creating a Batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreate() throws EasyPostException{
        Batch batch = Batch.create(params);

        assertTrue(batch instanceof Batch);
        assertTrue(batch.getId().startsWith("batch_"));
        assertNotNull(batch.getShipments());
    }

    /**
     * Test retrieving a Batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testRetrieve() throws EasyPostException {
        Batch retrievedBatch = Batch.retrieve(globalBatch.getId());

        assertTrue(retrievedBatch instanceof Batch);
        assertEquals(globalBatch.getId(), retrievedBatch.getId());
    }

    /**
     * Test retrieving all batches.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAll() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        
        params.put("page_size", Fixture.pageSize());

        BatchCollection batches = Batch.all(params);

        List<Batch> batchesList = batches.getBatches();

        assertTrue(batchesList.size() <= Fixture.pageSize());
        assertNotNull(batches.getHasMore());
        assertTrue(batchesList.stream().allMatch(batch -> batch instanceof Batch));
    }

    /**
     * Test creating and buying a Batch in a single call.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testCreateAndBuy() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        List<Object> shipmentData = new ArrayList<>();

        shipmentData.add(Fixture.oneCallBuyShipment());
        params.put("shipments", shipmentData);

        Batch batch = Batch.createAndBuy(params);

        assertTrue(batch instanceof Batch);
        assertTrue(batch.getId().startsWith("batch_"));
        assertEquals(1, batch.getNumShipments().intValue());
    }

    /**
     * Test buying a batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled
    public void testBuy() throws EasyPostException {
        List<Object> shipmentData = new ArrayList<>();
        Map<String, Object> shipments = new HashMap<>();

        shipmentData.add(Fixture.oneCallBuyShipment());
        shipments.put("shipments", shipmentData);

        Batch batch = Batch.create(shipments);

        batch = batch.buy();

        assertTrue(batch instanceof Batch);
        assertEquals(1, batch.getNumShipments().intValue());
    }

    /**
     * Test creating a scanform for a batch.
     *
     * @throws EasyPostException when the request fails.
     */
    public void testCreateScanForm() throws EasyPostException {
        Batch batchWithScanForm = globalBatch.createScanForm();

        // We can't assert anything meaningful here because the scanform gets queued for generation and may not be immediately available
        assertTrue(batchWithScanForm instanceof Batch);
    }

    /**
     * Test adding and removing a shipment from a batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    public void testAddRemoveShipment() throws EasyPostException {
        Shipment shipment = Shipment.create(Fixture.oneCallBuyShipment());
        
        Batch batch = Batch.create();

        List<Shipment> shipmentData = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        shipmentData.add(shipment);
        params.put("shipments", shipmentData);

        Batch batchWithAddedShipment = batch.addShipments(params);

        assertEquals(1, batchWithAddedShipment.getNumShipments().intValue());

        Batch batchWithoutShipment = batch.removeShipments(params);

        assertEquals(0, batchWithoutShipment.getNumShipments().intValue());
    }

    /**
     * Test generating a label for a Batch.
     *
     * @throws EasyPostException when the request fails.
     */
    @Test
    @Disabled // This test is skipped because we have to wait for the batch status to be `created` and the shipment's status to be `postage_purchased`.
    public void testLabel() throws EasyPostException {
        Map<String, Object> params = new HashMap<>();
        params.put("file_format", "ZPL");

        while (true) {
            globalBatch = globalBatch.refresh();
            if("created".equals(globalBatch.getState()) ){
                globalBatch.buy();
                break;
            }
        }

        while (true) {
            globalBatch = globalBatch.refresh();
            if ("postage_purchased".equals(globalBatch.getShipments().get(0).getBatchStatus())) {
                Batch batchWithLabel = globalBatch.label(params);

                // We can't assert anything meaningful here because the label gets queued for generation and may not be immediately available
                assertEquals(1, batchWithLabel.getNumShipments().intValue());
                assertTrue(batchWithLabel instanceof Batch);
                break;
            }
        }
    }
}
