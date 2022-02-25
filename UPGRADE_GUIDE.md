# Upgrade Guide

Use the following guide to assist in the upgrade process of the `easypost-java` library between major versions.

* [Upgrading from 4.x to 5.0](#upgrading-from-4x-to-50)

## Upgrading from 4.x to 5.0

### 5.0 High Impact Changes

* [Updating Dependencies](#50-updating-dependencies)
* [JSON Encoded Bodies](#50-json-encoded-bodies)

### 5.0 Medium Impact Changes

* [Default Timeouts for HTTP Requests](#50-default-timeouts-for-http-requests)

### 5.0 Low Impact Changes

* [Removal of Item and Container Objects](#50-removal-of-item-and-container-objects)

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
