package com.easypost;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.easypost.model.Shipment;
import com.easypost.model.Batch;
import com.easypost.model.Tracker;
import com.easypost.model.TrackingDetail;
import com.easypost.model.TrackingLocation;

import java.lang.InterruptedException;

import com.easypost.exception.EasyPostException;

public class EasyPostTest {

  static Map<String, Object> defaultFromAddress = new HashMap<String, Object>();
  static Map<String, Object> defaultToAddress = new HashMap<String, Object>();
  static Map<String, Object> defaultParcel = new HashMap<String, Object>();

  static Shipment createDefaultShipmentDomestic() throws EasyPostException {
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    Shipment shipment = Shipment.create(shipmentMap);
    return shipment;
  }

  @BeforeClass
  public static void setUp() {
    EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi"; // easypost public test key

    defaultFromAddress.put("name", "EasyPost");
    defaultFromAddress.put("street1", "164 Townsend St");
    defaultFromAddress.put("street2", "Unit 1");
    defaultFromAddress.put("city", "San Francisco");
    defaultFromAddress.put("state", "CA");
    defaultFromAddress.put("zip", "94107");
    defaultFromAddress.put("phone", "415-456-7890");

    defaultToAddress.put("company", "Airport Shipping");
    defaultToAddress.put("street1", "601 Brasilia Avenue");
    defaultToAddress.put("city", "Kansas City");
    defaultToAddress.put("state", "MO");
    defaultToAddress.put("zip", "64153");
    defaultToAddress.put("phone", "314-924-0383");

    defaultParcel.put("length", 10.8);
    defaultParcel.put("width", 8.3);
    defaultParcel.put("height", 6);
    defaultParcel.put("weight", 10);
  }

  @Test
  public void testBatchCreateScanForm() throws EasyPostException, InterruptedException {
    // create and buy shipments
    Shipment shipment1 = createDefaultShipmentDomestic();
    Shipment shipment2 = createDefaultShipmentDomestic();
    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("USPS");
    shipment1.buy(shipment1.lowestRate(buyCarriers));
    shipment2.buy(shipment2.lowestRate(buyCarriers));

    // create batch and wait until ready
    Batch batch = Batch.create();
    while(true) {
        batch = batch.refresh();
        if ("created".equals(batch.getState())) {
          break;
        }
        Thread.sleep(3000);
    }

    // add shipments to batch
    List<Shipment> shipments = new ArrayList<Shipment>();
    shipments.add(shipment1);
    shipments.add(shipment2);
    batch.addShipments(shipments);

    // create manifest and wait for it to be ready
    batch.createScanForm();
    while(true) {
        batch = batch.refresh();
        if (batch.getScanForm() != null) {
            break;
        }
        Thread.sleep(3000);
    }

    assertNotNull(batch.getScanForm());
  }

  @Test
  public void testShipmentWithTracker() throws EasyPostException {
    // create and buy shipment
    Shipment shipment = createDefaultShipmentDomestic();
    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("USPS");
    shipment = shipment.buy(shipment.lowestRate(buyCarriers));

    assertNotNull(shipment.getTracker());
  }

  @Test
  public void testTrackerCreateAndRetrieve() throws EasyPostException {
    // create test tracker
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("tracking_code","EZ2000000002");
    params.put("carrier","USPS");
    Tracker tracker = Tracker.create(params);

    assertNotNull(tracker);

    Tracker retrieved = Tracker.retrieve(tracker.getId());

    assertNotNull(retrieved);
    assertEquals("Tracker ids are not the same", tracker.getId(), retrieved.getId());
    assertEquals("Tracker statuses are not the same", tracker.getStatus(), retrieved.getStatus());

    TrackingDetail trackingDetail = tracker.getTrackingDetails().get(0);
    TrackingDetail retrievedDetail = tracker.getTrackingDetails().get(0);

    assertEquals("TrackingDetails are not the same", trackingDetail, retrievedDetail);

    TrackingLocation trackingLocation = trackingDetail.getTrackingLocation();
    TrackingLocation retrievedLocation = retrievedDetail.getTrackingLocation();

    assertEquals("TrackingLocations are not the same", trackingLocation, retrievedLocation);
  }

  @Test
  public void testShipmentWithRateError() throws EasyPostException {
    Map<String, Object> parcelMap = new HashMap<String, Object>();
    parcelMap.put("weight", 10);
    parcelMap.put("predefined_package", "Pak");

    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", parcelMap);
    Shipment shipment = Shipment.create(shipmentMap);

    assertNotNull(shipment.getMessages());
  }

}
