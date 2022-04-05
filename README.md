# EasyPost Java Client Library

[![CI](https://github.com/EasyPost/easypost-java/workflows/CI/badge.svg)](https://github.com/EasyPost/easypost-java/actions?query=workflow%3ACI)
[![Maven Central](https://img.shields.io/maven-central/v/com.easypost/easypost-api-client?label=Maven%20Central)](https://search.maven.org/artifact/com.easypost/easypost-api-client)

EasyPost, the simple shipping solution. You can sign up for an account at https://easypost.com.

## Install

**Maven**

Add this to your project's POM:

```xml
<dependency>
  <groupId>com.easypost</groupId>
  <artifactId>easypost-api-client</artifactId>
  <version>5.2.0</version>
</dependency>
```

**Gradle**

Add this to your project's build file:

```groovy
implementation "com.easypost:easypost-api-client:5.2.0"
```

**NOTE:** [Google Gson](http://code.google.com/p/google-gson/) is required.

## Usage

A simple create & buy shipment example:

```java
package shipments;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Shipment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateShipment {
  public static void main(String[] args) {
    EasyPost.apiKey = System.getenv("EASYPOST_API_KEY");

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

    Shipment shipment = Shipment.create(shipmentMap);

    shipment.buy(shipment.lowestRate())

    System.out.println(shipment.prettyPrint());
  }
}
```

## Documentation

API Documentation can be found at: https://easypost.com/docs/api.

## Development

### Tests

```bash
# Build project
mvn clean install -DskipTests

# Run tests
mvn clean test -B

# Run tests with coverage
mvn clean test -B jacoco:report
```
