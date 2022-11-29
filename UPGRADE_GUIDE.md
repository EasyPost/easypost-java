# Upgrade Guide

Use the following guide to assist in the upgrade process of the `easypost-java` library between major versions.

- [Upgrading from 5.x to 6.0](#upgrading-from-5x-to-60)
- [Upgrading from 4.x to 5.0](#upgrading-from-4x-to-50)

## Upgrading from 5.x to 6.0

### 6.0 High Impact Changes

- [Client Instance](#60-client-instance)
- [Error Types](#60-error-types)
- [Empty Response Function Return Types](#60-empty-response-function-return-types)
- [Services and Resources](#60-services-and-resources)

### 6.0 Medium Impact Changes

- [Removal of Setters](#60-removal-of-setters)
- [Rename Some Getters](#60-rename-some-getters)

### 6.0 Low Impact Changes

- [Type Changes](#60-type-changes)

## 6.0 Client Instance

*Likelihood of Impact: **High***

The library is now designed around the idea of a `EasyPostClient`. Users will initialize an `EasyPostClient` instance with their API key and then use that instance to make API calls.

This change allows for multiple clients to be instantiated with different API keys which allows this library to be safely used in a multi-threaded environment.

```java
// Old
EasyPost.apiKey = System.getenv("EASYPOST_API_KEY"); // global API key

// New
EasyPostClient client1 = new EasyPostClient("EASYPOST_API_KEY_1"); // per-client API key
EasyPostClient client2 = new EasyPostClient("EASYPOST_API_KEY_2"); // per-client API key
```

All functions are now accessed via the `EasyPostClient` instance. For example, to create a shipment:

```java
// Old
EasyPost.apiKey = System.getenv("EASYPOST_API_KEY");
Shipment shipment = Shipment.create(shipmentMap);

// New
EasyPostClient client = new EasyPostClient("EASYPOST_API_KEY_1");
Shipment shipment = client.shipment.create(shipmentMap);
```

## 6.0 Error Types

*Likelihood of Impact: **High***

Error handling has been overhauled and a number of specific exception types have been introduced.

All exceptions inherit from `EasyPost.Exception.EasyPostException` (which extends `Java Exception` class). Users can catch this exception type to handle all errors thrown by the library.

Subclasses of `EasyPostException` are grouped into two categories:

- `EasyPost.Exception.API` for errors returned by the API. Subclasses of this exception type are:
  - `ExternalApiError` - thrown when an issue occurs with an external API (e.g. Stripe)
  - `ForbiddenError` - thrown when you don't have permission to access this API resource
  - `GatewayTimeoutError` - thrown when the API gateway times out (504 status code)
  - `InternalServerError` - thrown when an internal server error occurs (500 status code)
  - `InvalidRequestError` - thrown when the API received an invalid request (422 status code)
  - `MethodNotAllowedError` - thrown when the API receives a request with an invalid HTTP method (
        405 status code)
  - `NotFoundError` - thrown when the API receives a request for a resource that does not exist (
        404 status code)
  - `PaymentError` - thrown when a payment error occurs (402 status code)
  - `ProxyError` - thrown when the library is unable to connect to the API via a proxy
  - `RateLimitError` - thrown when the API rate limit is exceeded (429 status code)
  - `RedirectError` - thrown when the http status is between 300 and 308.
  - `ServiceUnavailableError` - thrown when the API is unavailable (503 status code)
  - `TimeoutError` - thrown when the API request times out (408 status code)
  - `UnauthorizedError` - thrown when the API receives an unauthorized request (401 or 403 status
        code)
  - `UnknownApiError` - thrown when an unknown API error occurs (unexpected 4xx status code)
- `EasyPost.Exception.General` for Generic error returned by the client library. Subclasses of this exception type are:
  - `FilteringError` - thrown when an error occurs during filtering (e.g. calculating lowest rate)
  - `InvalidObjectError` - thrown when an invalid object is being used
  - `InvalidParameterError` - thrown when an invalid parameter is being used
  - `MissingPropertyError` - thrown when a required property is missing (e.g. `Id` for most objects)
  - `SignatureVerificationError` - thrown when the signature for a webhook is invalid

Any exception thrown by the EasyPost library will be one of the above types.

Any `EasyPost.Exception.API` exception will have the following properties:

- `String Code` - the HTTP status code returned by the API call (e.g. 404)
- `String Message` - the error message returned by the API (e.g. "PARAMETER.INVALID")
- `List<Error> Errors` - a list of errors returned by the API (e.g. "Invalid parameter: to_address")

Users can better anticipate exception information through utilities in the `EasyPost.Exception.Constants` file.

## 6.0 Empty Response Function Return Types

*Likelihood of Impact: **High***

The following functions return an empty body from the API have been changed to return void.

- `fundWallet()` and `deletePaymentMethod()` in Billing class
- `createList()` in Tracker class
- `updateEmail()` in ReferralCustomer class

## 6.0 Services and Resources

*Likelihood of Impact: **High***

Static and instance-based methods have been divided into separate services and resources, methods that have API interactions are in services now.

For example:

- The static method `Shipment.buy()` is now accessible in the Shipment service via `client.shipment.buy("shp_...")`. 
- The instance method `myShipment.lowestRate()` is still accessible only via a Shipment instance because there is no API interaction.
- The instance method of `refresh()` has been removed becasue you can no longer use object to make a HTTP request.

Instances of an object cannot be initialized directly. Instead, use the service to create the instance via a `create`, `retrieve`, or `all` API call.

## 6.0 Removal of Setters

*Likelihood of Impact: **Medium***

Each object no longer has setters. The use case of the setter is extremely low, but if you need to use the setter for a specific reason, try to use `reflection` in Java.

## 6.0 Rename Some Getters

*Likelihood of Impact: **Medium***

Below getters have been renamed:

- Pickup class: `getPickoutRates()` -> `getPickupRates()`
- PaymentMethod class: `getPrimaryPaymentMethodObject()` -> `getPrimaryPaymentMethod()`
- PaymentMethod class: `getSecondaryPaymentMethodObject()` -> `getSecondaryPaymentMethod()`

## 6.0 Type Changes

*Likelihood of Impact: **Low***

Change the below type of objects:

- `result` of Event from `EasyPostResource` to `Map<String, object>`
- `amount` of Insurance from `Float` to `String`

## Upgrading from 4.x to 5.0

### 5.0 High Impact Changes

- [Updating Dependencies](#50-updating-dependencies)
- [JSON Encoded Bodies](#50-json-encoded-bodies)

### 5.0 Medium Impact Changes

- [Default Timeouts for HTTP Requests](#50-default-timeouts-for-http-requests)

### 5.0 Low Impact Changes

- [Removal of Item and Container Objects](#50-removal-of-item-and-container-objects)

## 5.0 Updating Dependencies

Likelihood of Impact: High

**Java 8 Required**

easypost-java now requires Java 8 or greater.

**Dependencies**

All dependencies had minor version bumps.

## 5.0 JSON Encoded Bodies

Likelihood of Impact: High

All `POST` and `PUT` request bodies are now JSON encoded instead of form-encoded. You may see subtle inconsistencies to how certain data types were previously sent to the API. We have taken steps to mitigate and test against these edge cases.

## 5.0 Default Timeouts for HTTP Requests

Likelihood of Impact: Medium

Default timeouts for all HTTP requests are now set to 30 seconds for connection and 60 seconds for requests. If you require longer timeouts, you can set them by overriding the defaults:

```java
// Timeouts are in milliseconds
EasyPostResource.setConnectTimeoutMilliseconds(30000);
EasyPostResource.setReadTimeoutMilliseconds(60000);
```

## 5.0 Removal of Item and Container Objects

Likelihood of Impact: Low

The `Item` and `Container` objects were removed as they have not been supported for some time.
