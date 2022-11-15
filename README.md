# EasyPost Java Client Library

[![CI](https://github.com/EasyPost/easypost-java/workflows/CI/badge.svg)](https://github.com/EasyPost/easypost-java/actions?query=workflow%3ACI)
[![Maven Central](https://img.shields.io/maven-central/v/com.easypost/easypost-api-client?label=Maven%20Central)](https://search.maven.org/artifact/com.easypost/easypost-api-client)

EasyPost, the simple shipping solution. You can sign up for an account at <https://easypost.com>.

## Install

### Maven

Add this to your project's POM:

```xml
<dependency>
  <groupId>com.easypost</groupId>
  <artifactId>easypost-api-client</artifactId>
  <version>5.10.0</version>
</dependency>
```

### Gradle

Add this to your project's build file:

```groovy
implementation "com.easypost:easypost-api-client:5.10.0"
```

**NOTE:** [Google Gson](http://code.google.com/p/google-gson/) is required.

## Usage

A simple create & buy shipment example:

```java
package shipments;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;
import com.easypost.service.EasyPostClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateShipment {
  public static void main(String[] args) {
    EasyPostClient client = new EasyPostClient(System.getenv("EASYPOST_API_KEY"));

    Map<String, Object> fromAddressMap = new HashMap<String, Object>();
    fromAddressMap.put("company", "EasyPost");
    fromAddressMap.put("street1", "417 MONTGOMERY ST");
    fromAddressMap.put("street2", "FLOOR 5");
    fromAddressMap.put("city", "SAN FRANCISCO");
    fromAddressMap.put("state", "CA");
    fromAddressMap.put("country", "US");
    fromAddressMap.put("zip", "94104");
    fromAddressMap.put("phone", "415-123-4567");

    Map<String, Object> toAddressMap = new HashMap<String, Object>();
    toAddressMap.put("name", "Dr. Steve Brule");
    toAddressMap.put("street1", "179 N Harbor Dr");
    toAddressMap.put("city", "Redondo Beach");
    toAddressMap.put("state", "CA");
    toAddressMap.put("country", "US");
    toAddressMap.put("zip", "90277");
    toAddressMap.put("phone", "310-808-5243");

    Map<String, Object> parcelMap = new HashMap<String, Object>();
    parcelMap.put("weight", 22.9);
    parcelMap.put("height", 12.1);
    parcelMap.put("width", 8);
    parcelMap.put("length", 19.8);

    Map<String, Object> shipmentMap = new HashMap<String, Object>();
    shipmentMap.put("from_address", fromAddressMap);
    shipmentMap.put("to_address", toAddressMap);
    shipmentMap.put("parcel", parcelMap);

    Shipment shipment = client.shipment.create(shipmentMap);

    Shipment boughtShipment = client.shipment.buy(shipment.lowestRate(), shipment.getId());

    System.out.println(boughtShipment.prettyPrint());
  }
}
```

## Documentation

API Documentation can be found at: <https://easypost.com/docs/api>.

Upgrading major versions of this project? Refer to the [Upgrade Guide](UPGRADE_GUIDE.md).

## Development

### Tests

```bash
# Build project
make build

# Lint project
make lint

# Run tests
EASYPOST_TEST_API_KEY=123... EASYPOST_PROD_API_KEY=123... make test

# Run tests with coverage
EASYPOST_TEST_API_KEY=123... EASYPOST_PROD_API_KEY=123... make coverage

# Run security analysis
make scan

# Generate library documentation
make docs

# Update submodules
git submodule init
git submodule update --remote
```

### Testing

The test suite in this project was specifically built to produce consistent results on every run, regardless of when they run or who is running them.

This project uses [EasyVCR](https://github.com/EasyPost/easyvcr-java) to record and replay HTTP requests and responses via "cassettes". When the suite is run, the HTTP requests and responses for each test function will be saved to a cassette if they do not exist already and replayed from this saved file if they do, which saves the need to make live API calls on every test run.

**Sensitive Data:** We've made every attempt to include scrubbers for sensitive data when recording cassettes so that PII or sensitive info does not persist in version control; however, please ensure when recording or re-recording cassettes that prior to committing your changes, no PII or sensitive information gets persisted by inspecting the cassette.

**Making Changes:** If you make an addition to this project, the request/response will get recorded automatically for you. When making changes to this project, you'll need to re-record the associated cassette to force a new live API call for that test which will then record the request/response used on the next run.

**Test Data:** The test suite has been populated with various helpful fixtures that are available for use, each completely independent from a particular user **with the exception of the USPS carrier account ID** (see [Unit Test API Keys](#unit-test-api-keys) for more information) which has a fallback value of our internal testing user's ID. Some fixtures use hard-coded dates that may need to be incremented if cassettes get re-recorded (such as reports or pickups).

#### Unit Test API Keys

The following are required on every test run:

- `EASYPOST_TEST_API_KEY`
- `EASYPOST_PROD_API_KEY`

Some tests may require an EasyPost user with a particular set of enabled features such as a `Partner` user when creating referrals. We have attempted to call out these functions in their respective docstrings. The following are required when you need to re-record cassettes for applicable tests:

- `USPS_CARRIER_ACCOUNT_ID` (eg: one-call buying a shipment for non-EasyPost employees)
- `PARTNER_USER_PROD_API_KEY` (eg: creating a referral user)
- `REFERRAL_USER_PROD_API_KEY` (eg: adding a credit card to a referral user)
