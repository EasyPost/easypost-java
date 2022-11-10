# CHANGELOG

## V6 (Next release)

- Library is now thread-safe
  - Initialize a `EasyPostClient` object with an API key, connection and readtime milliseconds are optional, otherwise it will be set to default.
  - All methods (i.e. `create`, `retrieve`, retrieve `all` of a resource) exist in services, accessed via property of the client
    - E.g. Static method -> `Shipment shipment = client.address.create(params)`
    - E.g. Instance method -> `Shipment boughtShipment = client.shipment.buy(lowestRate, shipmentID)`
  - All functions are now taking object ID instead of an object, which means the functions are returning the response value directly instead of updating the current object
    - E.g. `Shipment boughtShipment = client.shipment.buy(lowestRate, shipmentID)`
- Improves error exception handling
  - Specific error types for each category of error
  - API error message may be an array rather than a string. Arrays will be concatenated (by comma) and returned as a string.
- Removes `createAndVerifyWithCarrier` function
- Removes `verifyWithCarrier` function
- Changes the type of Insurance `Amount` from Float to String
- Removes `getUpdate` and `setUpdate` from Tracker class
- Removes all beta features and corrsponding unit tests
- Removes `serviceCode` from `Rate` class since this value is internal use only
- Converts return type from boolean to void in empty response body functions
  - `fundWallet()` and `deletePaymentMethod()` in Billing class
  - `createList()` in Tracker class
  - `updateEmail()` in ReferralCustomer class
- Adds two missing attributes in the Event class: `pendingUrls` and `completedUrls`
- Changes the type `result` of Event from `EasyPostResource` to `Map<String, Object>`
- AppEngine default timeout is now the same as connection timeout
- Removes invalid function `User.create()` since creating a child-user requires a name

## v5.10.0 (2022-09-21)

- Adds support to pass an `EndShipper ID` when buying a shipment
- Removes the unusable overload shipment `buy` functions that did not contain a rate parameter
- Add White Label support:
  - Create a referral customer
  - Update a referral customer's email address
  - List all referral customers
  - Add a credit card to a referral customer's account

## v5.9.0 (2022-08-25)

- Moves EndShipper out of beta into the general library namespace
- Removes the unusable `ParcelCollection` and `all` function of the Parcel object

## v5.8.1 (2022-08-03)

- Fixes a bug where an erroneous API call to retrieve smartrates would be made behind the scenes whenever a shipment object was updated locally from the API (such as when regenerating rates) due to the deprecated function name being called `getSmartrates` and the library assuming this was a getter and attempting to use it to update the shipment data

## v5.8.0 (2022-08-02)

- Adds Carbon Offset support
  - Adds the ability to create a shipment with carbon offset
  - Adds the ability to buy a shipment with carbon offset
  - Adds the ability to one-call-buy a shipment with carbon offset
  - Adds the ability to re-rate a shipment with carbon offset
- Adds `validateWebhook` function that returns your webhook or raises an error if there is a webhook secret mismatch

## v5.7.0 (2022-07-18)

- Adds ability to generate a shipment form via `generate_form()` function

## v5.6.0 (2022-07-11)

- Adds `Billing.retrievePaymentMethods()`, `Billing.fundWallet()`, and `Billing.deletePaymentMethod()` functions
- Deprecates some existing payment method and credit card functions and features (repleaced by new Billing functions):
  - Deprecates `CreditCard.fund()` and `CreditCard.delete()` functions
  - Deprecates `PaymentMethod.all()` function
  - Deprecates certain `PaymentMethod` object functions
- Some `getX` functions have been deprecated and renamed to simply `X` to avoid confusion with proper getter/setter
  functions.
  - `getSmartrates()` -> `smartrates()`
  - `getLowestSmartRate()` -> `findLowestSmartrate()`
- Adds OS specific details to the user-agent header

## v5.5.0 (2022-06-21)

- Adds `billingType` attribute to `Rate` and `CarrierAccount` classes.
- Adds missing attributes to the `CarrierAcccount` object (closes #145)

## v5.4.0 (2022-06-03)

- Adds `PaymentMethod.all()`, `CreditCard.fund()`, and `CreditCard.delete()` functions.
- Fixes a bug that did not merge local and remote objects correctly on update (eg: User, Referral)

## v5.3.0 (2022-05-19)

- Adds the `EndShipper` Beta class with `create`, `retrieve`, `all`, and `update` functions
- Adds support for `columns` and `additional_columns` on Report creation
- Adds `declaraction` attribute in CustomsInfo class
- Adds `AddressVerifications` class
- Adds a `lowestRate()` function to Orders and Pickups
- Adds a `Shipment.getLowestSmartrate()` function and a `shipment.lowestSmartrate()` function
- Adds beta Referral class for White Label API with these new functions: `create()`, `updateEmail()`, `all()`, and `addCreditCard()`
- Adds `CreditCard` class for `addCreditCard()` function return type
- All SmartRate-related functions now return lists of Smartrate objects rather than lists of Rate objects
- Remove the dead `message` conditional check in `Address.createAndVerify()`

## v5.2.0 (2022-02-25)

- Adds a getter and setter for `eel_pfc` on the `CustomsInfo` object
- Adds a getter and setter for `id` on the `Brand` object
- Fixes a bug that didn't allow Refunds to be created
- Adds a complete test suite and upgrade JUnit4 to JUnit5
- Fixes the `AddressCollection` to return a list of `Address` objects instead of `Batch` objects

## v5.1.0 (2022-02-09)

- Adds support to one-call buy a Shipment by passing a `service` and `carrierAccounts` key
- Adds support to retrieve carrier types via `CarrierType.all()`
- Adds support to one-call buy an Order by passing a `service` and `carrierAccounts` key
- Adds support to update the user's brand via `User.updateBrand()`
- Allow user to override default API timeouts via getters and setters
- Bump all plugins and remove dead `oss-parent` plugin
- Various other small improvements and bug fixes

## v5.0.0 (2022-01-14)

Upgrading major versions of this project? Refer to the [Upgrade Guide](UPGRADE_GUIDE.md).

- Bump minimum Java version from 1.5 to 8
- Changed PUT/POST request bodies from url-form encoded to JSON encoded
- Added support for `SmartRate`
- Added support for `Tax Identifiers`
- Added `Checkstyle` as a plugin and linted the project
- Added new `rerate` shipment function
- Bumped project dependencies
- Include Java version in User-Agent header
- Remove `items` and `containers` which have not been supported for some time
- Corrects references of `contact@easypost.com` to `support@easypost.com`
- Updates the connection timeout to 30 seconds from 20 and the request timeout to 60 from 40

## v4.0.4 (2020-10-29)

- Actually bump User-Agent (which hadn't been bumped since 4.0.0)

## v4.0.3 (2020-10-26)

- Change `PUT` requests to be properly-encoded
- Handle null previous_attributes

## v4.0.2 (2020-03-30)

- Merged pull request #49
- Added EvenTest file
- Fixed test cases on EasyPostTest (testPickup, testShipmentWithPostageLabelWithOptions )
- Fixed intermittent WebHook callback event containing "previous_attributes": null

## v4.0.1 (2018-08-28)

- Add labelFile for postage_label_inline option

## v4.0.0 (2018-03-15)

- Added additional fields to Error object used in Address verification
- Added delivery details to Address verification
- Changed message field of ShipmentMessage to support non-string values
- Fixed currency field of Rate to handle null
- Fixed Report params to include start_date and end_date in the top-level JSON, as expected by the reports endpoint

## v3.4.1 (2018-03-09)

- Added createdAt, updatedAt and fees fields on all top-level objects
- Added batchId to Shipment objects

## v3.4.0 (2017-12-07)

- Updated version of junit from 4.1.10 to 4.1.12
- Updated version of maven-compiler-plugin from 1.5 to 1.8
- Added tests for new shipment option payment

## v3.3.5 (2017-05-23)

- Added orderId field to Shipments
- Allow Reports to be retrieved without passing a type

## v3.3.4 (2017-03-10)

- Updated X-EasyPost-Client-User-Agent to X-Client-User-Agent
- Added additional values to CarrierDetail
- Added statusDetail field to Trackers and TrackingDetails

## v3.3.3 (2017-02-14)

- Added newRates method to Orders to refresh rates

## v3.3.2 (2017-01-24)

- Allow Webhook update and index to be called with empty params

## v3.3.1 (2017-01-19)

- Fixed ScanForm create

## v3.3.0 (2017-01-17)

- Added basic CRUD methods for Webhooks

## v3.2.1 (2017-01-09)

- Fixed bug in Maven test dependencies

## v3.2.0 (2017-01-04)

- Changed Shipment and Order options from being a Map<String, String> to Map<String, Object> to support new non-string options

## v3.1.0 (2016-12-14)

- Added ability to create, retrieve, and index shipment, tracker and payment_log reports

## v3.0.5 (2016-11-21)

- Added delete() to Users (for children only)

## v3.0.4 (2016-10-12)

- Added getbatchId() and other methods to ScanForms

## v3.0.2 (2016-08-30)

- Added getCreatedAt() and getUpdatedAt() methods for Tracker objects

## v3.0.1 (2016-08-19)

- Removed some CRUD methods that are not (and never were) valid

## v3.0.0 (2016-07-25)

- Updated version of GSON from 2.2.4 to 2.7 to allow looser parsing of DateTime formats

## v2.2.7 (2016-07-25)

- Added standalone insurance objects

## v2.2.6 (2016-07-22)

- Allow the connection's read timeout to be set with EasyPost.readTimeout

## v2.2.5 (2016-07-08)

- Allow shipment_id to be null on rates

## v2.2.4 (2016-03-09)

- Bump version for deployment reasons

## v2.2.3 (2016-06-27)

- Updated POM.xml to fit Maven better and support the correct plugins
- Removed a flaky text

## v2.2.2 (2016-03-09)

- Updated PickupRate to properly include the carrier field

## v2.2.1 (2016-03-02)

- Updated Order Messages to be of the new, more descriptive format

## v2.2.0 (2016-02-25)

- Added "confirmation" field to ScanForm object, along with appropriate accessor methods
- Update Address verification errors array

## v2.1.9 (2016-02-08)

- Added ability to create new top-level users without API KEY

## v2.1.8 (2016-01-26)

- Added a Form attribute - submittedElectronically.

## v2.1.7 (2016-01-19)

- Fixed some types on the User object so that they match what is returned by the API (Number -> String).
- Added support for the new "verify" and "verify_strict" parameters for Address creation/validation.

## v2.1.6 (2015-12-21)

- Fixed Shipment Insure method so that it correctly uses http POST.

## v2.1.5 (2015-11-19)

- Added createList method to Tracker class

## v2.1.4 (2015-09-24)

- Added CarrierDetail object to tracker model.

## v2.1.3 (2015-07-31)

- Added Rate.listRate and Rate.listCurrency.

## v2.1.2 (2015-07-08)

- Added Carrier field to the tracker model.

## v2.1.1 (2015-06-19)

- Added Form model and Shipment.forms.

## v2.1.0 (2015-04-15)

- Added User model, and filled out CarrierAccount to be able to interact with
    both via the API.

## v2.0.14 (2015-04-08)

- Updated Tracker model to have weight, signedBy and estDeliveryDate fields

## v2.0.13 (2015-04-07)

- Fixed Address createAndVerify method
- Added Address verifyWithCarrier and createAndVerifyWithCarrier methods

## v2.0.12 (2015-03-03)

- Added Pickup classes and pickup create, buy, cancel methods

## v2.0.11 (2015-03-03)

- Orders now update themselves after a buy call.

## v2.0.10 (2015-01-27)

- Added Address.residential attribute, and Batch.buy function

## v2.0.9 (2014-12-22)

- Added a number of new Rate attributes and Batch.numShipments, Batch.reference, ScanForm.status

## v2.0.8 (2014-12-18)

- Added ShipmentMessage type for Shipment.messages

## v2.0.7 (2014-11-04)

- Added Tracker to shipment buy response
- Added Tracking Locations to Tracking Details
- Enhanced Tests

## v2.0.6 (2014-07-28)

- Added Container, Item, Order resources.
- Added Batch.createScanForm function for generating manifests from a batch.
- Refactored and added a lot of new attributes for objects across the board.

## v2.0.5 (2013-12-11)

- Bug fix. Made Batch.status public and removed the conflicting getStatus method.

## v2.0.4 (2013-10-14)

- Added Event resource for webhook digestion.
- Changed Batch.getStatus to Batch.getBatchStatus to avoid collisions with Tracker.getStatus in Event deserialization.

## v2.0.3 (2013-08-28)

- Bug fix: Fixed handleAPIError to return proper exception message.

## v2.0.2 (2013-08-02)

- API Addition: Tracker resource added. Trackers can be used to register any tracking code with EasyPost webhooks.
- Attribute Addition: Added new BatchStatus attribute, and additional label format attributes to PostageLabel.

## v2.0.1 (2013-07-05)

- Added unique carrier/service combination serviceCodes to Rate objects.
- Added function to Address to all creating and verifying at the same time.
- Add label function to Shipment to request specific label file_formats (pdf, epl2, zpl).
- Add insure function to Shipment. Add insurance to any shipment in one call!
