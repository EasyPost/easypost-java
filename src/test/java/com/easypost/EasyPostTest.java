package com.easypost;

import com.easypost.model.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.lang.InterruptedException;

import com.easypost.exception.EasyPostException;

import static org.junit.Assert.*;

public class EasyPostTest {

  static Map<String, Object> defaultFromAddress = new HashMap<String, Object>();
  static Map<String, Object> defaultToAddress = new HashMap<String, Object>();
  static Map<String, Object> defaultParcel = new HashMap<String, Object>();
  static Map<String, Object> defaultCustomsItem = new HashMap<String, Object>();
  static Map<String, Object> defaultCustomsInfo = new HashMap<String, Object>();
  static Map<String, Object> canadaToAddress = new HashMap<String, Object>();

  static Shipment createDefaultShipmentDomestic() throws EasyPostException {
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    Shipment shipment = Shipment.create(shipmentMap);
    return shipment;
  }

  static Map<String, Object> orderShipment() throws EasyPostException {
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("parcel", defaultParcel);
    return shipmentMap;
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

    defaultCustomsItem.put("description", "Item descrpition");
    defaultCustomsItem.put("origin_country", "US");
    defaultCustomsItem.put("quantity", 1);
    defaultCustomsItem.put("value", 10.50);
    defaultCustomsItem.put("weight", 9.9);

    List<Map<String, Object>> customsItems = new ArrayList<Map<String, Object>>();
    customsItems.add(defaultCustomsItem);
    defaultCustomsInfo.put("customs_certify", true);
    defaultCustomsInfo.put("customs_signer", "Shipping Manager");
    defaultCustomsInfo.put("contents_type", "merchandise");
    defaultCustomsInfo.put("non_delivery_option", "return");
    defaultCustomsInfo.put("restriction_type", "none");
    defaultCustomsInfo.put("customs_items", customsItems);

    canadaToAddress.put("company", "Canada Receiving");
    canadaToAddress.put("street1", "1 Larkspur Cres");
    canadaToAddress.put("city", "St. Albert");
    canadaToAddress.put("state", "AB");
    canadaToAddress.put("zip", "t8n2m4");
    canadaToAddress.put("country", "CA");
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
  public void testShipmentWithPostageLabelWithOptions() throws EasyPostException {
    // create and buy shipment
    Map<String, Object> optionsMap = new HashMap<String, Object>();
    optionsMap.put("label_format", "ZPL");
    optionsMap.put("label_size", "4X4.5");

    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    shipmentMap.put("options", optionsMap);
    Shipment shipment = Shipment.create(shipmentMap);

    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("USPS");
    shipment = shipment.buy(shipment.lowestRate(buyCarriers));

    PostageLabel label = shipment.getPostageLabel();

    assertNotNull(label);
    assertNotNull(label.getId());
    assertNotNull(label.getLabelUrl());
    assertEquals(label.getLabelSize(), "4x4.5");
    assertEquals(label.getLabelFileType(), "application/zpl");
  }

  @Test
  public void testShipmentWithInsurance() throws EasyPostException {
    // create and buy shipment
    Shipment shipment = createDefaultShipmentDomestic();

    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("USPS");
    shipment = shipment.buy(shipment.lowestRate(buyCarriers));

    Map<String, Object> insureMap = new HashMap<String, Object>();
    insureMap.put("amount", 100.00);
    shipment = shipment.insure(insureMap);

    assertNotNull(shipment.getInsurance());
  }

  @Test
  public void testCreateShipmentWithInsurance() throws EasyPostException {
    // create and buy shipment
    Shipment shipment = createDefaultShipmentDomestic();

    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("USPS");
    shipment = shipment.buy(shipment.lowestRate(buyCarriers));


  }

  @Test
  public void testTrackerCreateAndRetrieve() throws EasyPostException, ParseException {
    // create test tracker
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("tracking_code","EZ4000000004");
    params.put("carrier", "FedEx");
    Tracker tracker = Tracker.create(params);

    assertNotNull(tracker);

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss zzz");
    Date date = sdf.parse("08/26/2014 17:00:00 PDT");
    assertEquals("Est delivery dates don't match", tracker.getEstDeliveryDate(), date);
    assertEquals("Weights don't match", tracker.getWeight(), 17.6, 0.0001);
    assertEquals("Signed By doesn't match", tracker.getSignedBy(), "John Tester");
    assertEquals("Service levels don't match", tracker.getCarrierDetail().getService(), "FEDEX_GROUND");
    assertEquals("Containers don't match", tracker.getCarrierDetail().getContainerType(), "YOUR_PACKAGING");
    assertNotNull("Est delivery Date local is null", tracker.getCarrierDetail().getEstDeliveryDateLocal());
    assertNotNull("Est delivery Time local is null", tracker.getCarrierDetail().getEstDeliveryTimeLocal());
    assertNotNull("Created at is null", tracker.getCreatedAt());
    assertNotNull("Updated at is null", tracker.getUpdateAt());

    Tracker retrieved = Tracker.retrieve(tracker.getId());

    assertNotNull(retrieved);
    assertEquals("Tracker ids are not the same", tracker.getId(), retrieved.getId());
    assertEquals("Tracker statuses are not the same", tracker.getStatus(), retrieved.getStatus());

    assertNotNull(tracker.getCarrier());
    assertNotNull(retrieved.getCarrier());
    assertEquals("Tracker carriers are not the same", tracker.getCarrier(), retrieved.getCarrier());

    TrackingDetail trackingDetail = tracker.getTrackingDetails().get(0);
    TrackingDetail retrievedDetail = tracker.getTrackingDetails().get(0);

    assertEquals("TrackingDetails are not the same", trackingDetail, retrievedDetail);

    TrackingLocation trackingLocation = trackingDetail.getTrackingLocation();
    TrackingLocation retrievedLocation = retrievedDetail.getTrackingLocation();

    assertEquals("TrackingLocations are not the same", trackingLocation, retrievedLocation);
  }

  @Test
  public void testBatchTrackerCreate() throws EasyPostException {
    List<String> trackingCodes = new ArrayList<String>();
    trackingCodes.add("EZ1000000001");
    trackingCodes.add("EZ2000000002");
    trackingCodes.add("EZ3000000003");

    List<HashMap<String, Object>> trackingCodeParams = new ArrayList<HashMap<String, Object>>();
    HashMap<String, Object> code;

    for(int i = 0; i < trackingCodes.size(); i++){
            code = new HashMap<String, Object>();
            code.put("tracking_code", trackingCodes.get(i));
            trackingCodeParams.add(code);
    }

    Map createListParams = new HashMap<String, Object>();
    createListParams.put("trackers", trackingCodeParams);

    Tracker.createList(createListParams);
  }

  @Test
  public void testTrackerIndex() throws EasyPostException, ParseException {
    // create test tracker
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("tracking_code","EZ2000000002");
    params.put("carrier", "FedEx");
    Tracker tracker = Tracker.create(params);

    assertNotNull(tracker);

    // retrieve tracker by id
    Tracker tracker2 = Tracker.retrieve(tracker.id);

    assertEquals("Tracker ids are not the same", tracker.getId(), tracker2.getId());

    // retrieve all trackers by tracking_code
    Map<String, Object> index_params = new HashMap<String, Object>();
    index_params.put("tracking_code","EZ2000000002");
    TrackerCollection trackers = Tracker.all(index_params);

    assertEquals("Incorrect length received", trackers.getTrackers().size(), 30);
    assertTrue("'has_more' should be true", trackers.getHasMore());
    assertEquals("Tracker ids in create response and all response are not the same", trackers.getTrackers().get(0).id, tracker.id);

    // create another test tracker
    Tracker tracker3 = Tracker.create(params);

    assertNotSame("Tracker ids are not the same", tracker.id, tracker3.id);

    // retrieve all created since 'tracker'
    Map<String, Object> index_params2 = new HashMap<String, Object>();
    index_params2.put("after_id",tracker.id);
    TrackerCollection trackers2 = Tracker.all(index_params2);

    assertEquals("Incorrect length received", trackers2.getTrackers().size(), 1);
    assertFalse("'has_more' should be true", trackers2.getHasMore());
    assertEquals("Tracker ids in create response and all response are not the same", trackers2.getTrackers().get(0).id, tracker3.id);
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

  @Test
  public void testRateDeserialization() throws EasyPostException {
    Shipment shipment = createDefaultShipmentDomestic();
    assertNotNull(shipment.getRates().get(0).getCarrierAccountId());
  }

  @Test
  public void testAddressResidentialOption() throws EasyPostException {
    // shipment residential_to_address option
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    Map<String, Object> shipmentOptionsMap = new HashMap<String, Object>();
    shipmentOptionsMap.put("residential_to_address", 1);
    shipmentMap.put("options", shipmentOptionsMap);

    Shipment shipment = Shipment.create(shipmentMap);
    assertTrue(shipment.getToAddress().getResidential());
  }

  @Test
  public void testAddressResidentialAttribute() throws EasyPostException {
    // toAddress.residential flag
    defaultToAddress.put("residential", 1);
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);

    Shipment shipment = Shipment.create(shipmentMap);
    assertTrue(shipment.getToAddress().getResidential());
  }

  @Test
     public void testAddressVerifies() throws EasyPostException {
    Map<String, Object> addressHash = new HashMap<String, Object>();
    addressHash.put("street1", "164 Townsend St");
    addressHash.put("street2", "Unit 1");
    addressHash.put("city", "San Francisco");
    addressHash.put("state", "CA");
    addressHash.put("zip", "19087");

    Address address = Address.create(addressHash);
    Address verified = address.verify();

    assertNull("Verified Address has no error message", verified.getMessage());
    assertEquals("Address did not verify as expected", verified.getZip(), "94107-1990");

    Address createdAndVerified = Address.createAndVerify(addressHash);

    assertNull("Verified Address has no error message", createdAndVerified.getMessage());
    assertEquals("Address did not verify as expected", createdAndVerified.getZip(), "94107-1990");
  }

  @Test
  public void testAddressVerifiesWithCarrier() throws EasyPostException {
    Map<String, Object> addressHash = new HashMap<String, Object>();
    addressHash.put("street1", "164 Townsend St");
    addressHash.put("street2", "Unit 1");
    addressHash.put("city", "San Francisco");
    addressHash.put("state", "CA");
    addressHash.put("zip", "19087");

    Address address = Address.create(addressHash);
    Address verified = address.verifyWithCarrier("usps");

    assertNull("Verified Address has no error message", verified.getMessage());
    assertEquals("Address did not verify as expected", verified.getZip(), "94107-1990");

    Address createdAndVerified = Address.createAndVerifyWithCarrier(addressHash, "usps");

    assertNull("Verified Address has no error message", createdAndVerified.getMessage());
    assertEquals("Address did not verify as expected", createdAndVerified.getZip(), "94107-1990");
  }

  @Test
  public void testAddressCreateWithVerifyPasses() throws EasyPostException {
    Map<String, Object> addressHash = new HashMap<String, Object>();

    List<String> verificationList = new ArrayList<String>();
    verificationList.add("delivery");
    addressHash.put("verify", verificationList);

    addressHash.put("street1", "118 2 St");
    addressHash.put("street2", "4");
    addressHash.put("city", "SF");
    addressHash.put("state", "CA");
    addressHash.put("zip", "94105");
    addressHash.put("country", "US");
    addressHash.put("company", "EasyPost");
    addressHash.put("phone", "415-123-4567");

    Address address = Address.create(addressHash);

    assertNotNull("Id is null", address.getId());
    assertEquals("City did not verify", address.getCity(), "SAN FRANCISCO");

    Map<String,AddressVerification> verifications = address.getVerifications();
    assertEquals("Verification did not succeed.", verifications.get("delivery").getSuccess(), true);
    assertEquals("Verification had errors.", verifications.get("delivery").getErrors(), Collections.emptyList());
  }

  @Test
  public void testAddressCreateWithVerifyFails() throws EasyPostException {
    Map<String, Object> addressHash = new HashMap<String, Object>();

    List<String> verificationList = new ArrayList<String>();
    verificationList.add("delivery");
    addressHash.put("verify", verificationList);

    addressHash.put("street1", "UNDELIVERABLE ST");
    addressHash.put("city", "SAN FRANCISCO");
    addressHash.put("state", "CA");
    addressHash.put("zip", "94105");
    addressHash.put("country", "US");
    addressHash.put("company", "EasyPost");
    addressHash.put("phone", "415-123-4567");

    Address address = Address.create(addressHash);

    assertNotNull("Id is null", address.getId());
    assertEquals("City did not verify", address.getCity(), "SAN FRANCISCO");

    Map<String,AddressVerification> verifications = address.getVerifications();
    assertEquals("Verification did not succeed.", verifications.get("delivery").getSuccess(), false);

    List<com.easypost.model.Error> errors = verifications.get("delivery").getErrors();

    assertTrue("At least two errors are present", errors.size() >= 2);
  }

  @Rule
  public ExpectedException verifyStrictException = ExpectedException.none();
  @Test
  public void testAddressCreateWithVerifyStrictFails() throws EasyPostException {
    Map<String, Object> addressHash = new HashMap<String, Object>();

    List<String> verificationList = new ArrayList<String>();
    verificationList.add("delivery");
    addressHash.put("verify_strict", verificationList);

    addressHash.put("street1", "UNDELIVERABLE ST");
    addressHash.put("city", "SAN FRANCISCO");
    addressHash.put("state", "CA");
    addressHash.put("zip", "94105");
    addressHash.put("country", "US");
    addressHash.put("company", "EasyPost");
    addressHash.put("phone", "415-123-4567");

    verifyStrictException.expect(com.easypost.exception.EasyPostException.class);

    Address address = Address.create(addressHash);
  }

  @Rule
  public ExpectedException exception = ExpectedException.none();
  @Test
  public void testAddressErrorParses() throws EasyPostException {
    Map<String, Object> addressHash = new HashMap<String, Object>();
    addressHash.put("street1", "118 Milky Way");
    addressHash.put("zip", "19087");

    Address address = Address.create(addressHash);

    exception.expect(com.easypost.exception.EasyPostException.class);
    address.verify();
  }

  @Test
  public void testBatchBuy() throws EasyPostException, InterruptedException {
    List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    shipmentMap.put("carrier", "USPS");
    shipmentMap.put("service", "Priority");
    shipments.add(shipmentMap);

    // create batch and wait until ready
    Map<String, Object> batchMap = new HashMap<String, Object>();
    batchMap.put("shipments", shipments);
    Batch batch = Batch.create(batchMap);
    while(true) {
      batch = batch.refresh();
      if ("created".equals(batch.getState())) {
        break;
      }
      Thread.sleep(3000);
    }

    batch.buy();
    while(true) {
      batch = batch.refresh();
      if ("purchased".equals(batch.getState())) {
        break;
      }
      Thread.sleep(3000);
    }

    assertEquals("Batch state is not purchased.", batch.getState(), "purchased");
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

    ScanForm scanForm = batch.getScanForm();

    assertNotNull(scanForm);

    assertNotSame(scanForm.getLabelUrl(), "");
  }

  @Test
  public void testPickup() throws EasyPostException, InterruptedException {
    Shipment shipment = createDefaultShipmentDomestic();
    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("UPS");
    shipment = shipment.buy(shipment.lowestRate(buyCarriers));

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

    String reference = "internal_id_1234";
    String instructions = "Special pickup instructions";

    Map<String, Object> pickupMap = new HashMap<String, Object>();
    pickupMap.put("address", defaultFromAddress);
    pickupMap.put("shipment", shipment);
    pickupMap.put("reference", reference);
    pickupMap.put("min_datetime", df.format(new Date()));
    pickupMap.put("max_datetime", df.format(new Date()));
    pickupMap.put("is_account_address", true);
    pickupMap.put("instructions", instructions);

    Pickup pickup = Pickup.create(pickupMap);

    assertNotNull(pickup);
    assertEquals(pickup.getMessages().size(), 0);
    assertEquals(pickup.getReference(), reference);
    assertEquals(pickup.getInstructions(), instructions);
    assertEquals(pickup.getStatus(), "unknown");
    assertTrue(pickup.getIsAccountAddress());

    assertEquals(pickup.getPickupRates().get(0).getCarrier(), "UPS");
    assertEquals(pickup.getPickupRates().get(0).getService(), "Same-day Pickup");

    Map<String, Object> buyMap = new HashMap<String, Object>();
    buyMap.put("carrier", pickup.getPickupRates().get(0).getCarrier());
    buyMap.put("service", pickup.getPickupRates().get(0).getService());
    pickup = pickup.buy(buyMap);

    assertNotNull(pickup);
    assertEquals(pickup.getMessages().size(), 0);
    assertEquals(pickup.getStatus(), "scheduled");

    Map<String, Object> cancelMap = new HashMap<String, Object>();
    cancelMap.put("id", pickup.getId());
    pickup = pickup.cancel(cancelMap);

    assertNotNull(pickup);
    assertEquals(pickup.getMessages().size(), 0);
    assertEquals(pickup.getStatus(), "canceled");

    Pickup retrieved = Pickup.retrieve(pickup.getId());
    pickup = pickup.refresh();

    assertNotNull(pickup);
    assertNotNull((retrieved));
    assertEquals(retrieved.getMessages().size(), 0);
    assertEquals(retrieved.getId(), pickup.getId());
    assertEquals(retrieved.getStatus(), pickup.getStatus());
  }

  @Test
  public void testOrder() throws EasyPostException {
    // create and buy multi-shipment order
    Map<String, Object> shipment_1 = orderShipment();
    Map<String, Object> shipment_2 = orderShipment();
    List<Map<String, Object>> shipments = new ArrayList<Map<String, Object>>();
    shipments.add(shipment_1);
    shipments.add(shipment_2);

    Map<String, Object> orderParams = new HashMap<String, Object>();
    orderParams.put("shipments", shipments);
    orderParams.put("from_address", defaultFromAddress);
    orderParams.put("to_address", defaultToAddress);
    Order order = Order.create(orderParams);

    Map<String, Object> buyParams = new HashMap<String, Object>();
    buyParams.put("carrier", "USPS");
    buyParams.put("service", "Priority");

    order.buy(buyParams);

    assertNotNull(order.getShipments().get(0).getTracker());
    assertNotNull(order.getShipments().get(0).getPostageLabel().getLabelUrl());
    assertNotNull(order.getShipments().get(1).getPostageLabel().getLabelUrl());
  }

  @Test
  public void testCustomsInfo() throws EasyPostException {
    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", canadaToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    shipmentMap.put("customs_info", defaultCustomsInfo);
    Map<String, Object> shipmentOptionsMap = new HashMap<String, Object>();
    shipmentOptionsMap.put("suppress_etd", true);
    shipmentMap.put("options", shipmentOptionsMap);

    Shipment shipment = Shipment.create(shipmentMap);

    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("USPS");
    shipment = shipment.buy(shipment.lowestRate(buyCarriers));

    assertTrue("Customs Form was not submitted electronically", shipment.getForms().get(0).getSubmittedElectronically());
  }

  @Test
  public void testInsurance() throws EasyPostException {
    Map<String, Object> insuraceMap = new HashMap<String, Object>();
    insuraceMap.put("to_address", defaultToAddress);
    insuraceMap.put("from_address", defaultFromAddress);
    insuraceMap.put("amount", 101.00);
    insuraceMap.put("carrier", "USPS");
    insuraceMap.put("tracking_code", "EZ2000000002");

    Insurance insurance = Insurance.create(insuraceMap);
    assertNotNull("ID is null", insurance.getId());
    assertNotNull("Tracking Code is null", insurance.getTrackingCode());
    assertNotNull("To Address is null", insurance.getToAddress());
    assertNotNull("From Address is null", insurance.getFromAddress());
    assertNotNull("Tracker is null", insurance.getTracker());

    Insurance insurance2 = Insurance.retrieve(insurance.getId());
    assertNotNull("ID is null", insurance2.getId());
    assertEquals("Create and Retrieve returned different ids", insurance.getId(), insurance2.getId());
    assertEquals("Create and Retrieve returned different tracking codes", insurance.getTrackingCode(), insurance2.getTrackingCode());


    Map<String, Object> index_params = new HashMap<String, Object>();
    index_params.put("tracking_code","EZ2000000002");
    index_params.put("page_size","5");
    InsuranceCollection insurances = Insurance.all(index_params);

    assertTrue("Wrong page_size returned", insurances.getInsurances().size() == 5);
    assertTrue("Insurances HasMore not set correctly", insurances.getHasMore());
  }


  /*
  // This test requires a FedEx account
  @Test
  public void testShipmentWithForm() throws EasyPostException {
    Map<String, Object> optionsMap = new HashMap<String, Object>();
    optionsMap.put("cod_amount", 10);

    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("to_address", defaultToAddress);
    shipmentMap.put("from_address", defaultFromAddress);
    shipmentMap.put("parcel", defaultParcel);
    shipmentMap.put("options", optionsMap);
    Shipment shipment = Shipment.create(shipmentMap);

    List<String> buyCarriers = new ArrayList<String>();
    buyCarriers.add("FEDEX");
    List<String> buyServices = new ArrayList<String>();
    buyServices.add("FEDEX_2_DAY");
    shipment.buy(shipment.lowestRate(buyCarriers, buyServices));

    assertNotNull(shipment.getForms().get(0).getFormUrl());
  }
  */

//  //  This test requires a production api key
//  @Test
//  public void testUserMethods() throws EasyPostException {
//    EasyPost.apiKey = "KEY"; // easypost private production key
//
//    User user = User.retrieveMe();
//
//    System.out.println(user.getPhoneNumber());
//    System.out.println(user.getRechargeAmount());
//    System.out.println(user.getSecondaryRechargeAmount());
//
//    user.setSecondaryRechargeAmount("$200.00");
//
//    Map<String, Object> userHash = new HashMap<String, Object>();
//    userHash.put("secondary_recharge_amount", 100000.00);
//
//    user.update(userHash);
//
//    System.out.println(user.getSecondaryRechargeAmount());
//
//    System.out.println(user.getRechargeThreshold());
//  }

//  //  This test requires a production api key
//  @Test
//  public void testCarrierAccountMutability() throws EasyPostException {
//    EasyPost.apiKey = "KEY"; // easypost private production key
//
//    Map<String, Object> listHash = new HashMap<String, Object>();
//    listHash.put("type", "UpsAccount");
//    List<CarrierAccount> carrierAccounts = CarrierAccount.all(listHash);
//    assertNotNull(carrierAccounts);
//
//    int originalNumCarrierAccounts = carrierAccounts.size();
//
//    String description = "A java client library test account";
//    String reference = "FedexJavaTest-1";
//    String accountNumber = "B2B2B2";
//    Map<String, Object> credentials = new HashMap<String, Object>();
//    credentials.put("account_number", accountNumber);
//    credentials.put("user_id", "UPSDOTCOM_USERNAME");
//    credentials.put("password", "UPSDOTCOM_PASSWORD");
//    credentials.put("access_license_number", "UPS_ACCESS_LICENSE_NUMBER");
//
//    Map<String, Object> carrierParams = new HashMap<String, Object>();
//    carrierParams.put("type", "UpsAccount");
//    carrierParams.put("description", description);
//    carrierParams.put("reference", reference);
//    carrierParams.put("credentials", credentials);
//
//    CarrierAccount carrierAccount = CarrierAccount.create(carrierParams);
//    CarrierAccount retrieved = CarrierAccount.retrieve(carrierAccount.getId());
//
//    assertNotNull(retrieved);
//    assertEquals(carrierAccount.getId(), retrieved.getId());
//    assertEquals(retrieved.getDescription(), description);
//    assertEquals(retrieved.getReference(), reference);
//    assertEquals(retrieved.getCredentials().get("account_number"), accountNumber);
//
//    List<CarrierAccount> updatedCarrierAccounts = CarrierAccount.all(listHash);
//    assertNotNull(updatedCarrierAccounts);
//
//    int updatedNumCarrierAccounts = updatedCarrierAccounts.size();
//    assertEquals(originalNumCarrierAccounts + 1, updatedNumCarrierAccounts);
//
//    String newDescription = "A java client library test account that has been edited";
//
//    String newAccountNumber = "C3C3C3C3";
//    credentials.put("account_number", newAccountNumber);
//
//    Map<String, Object> newParams = new HashMap<String, Object>();
//    newParams.put("description", newDescription);
//    newParams.put("credentials", credentials);
//
//    CarrierAccount mutated = retrieved.update(newParams);
//
//    assertNotNull(mutated);
//    assertEquals(carrierAccount.getId(), mutated.getId());
//    assertEquals(mutated.getDescription(), newDescription);
//    assertEquals(mutated.getCredentials().get("account_number"), newAccountNumber);
//
//    mutated.delete();
//
//    List<CarrierAccount> deletedCarrierAccounts = CarrierAccount.all(listHash);
//    assertNotNull(deletedCarrierAccounts);
//
//    int finalNumCarrierAccounts = deletedCarrierAccounts.size();
//    assertEquals(originalNumCarrierAccounts, finalNumCarrierAccounts);
//  }
}
