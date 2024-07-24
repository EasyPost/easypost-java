# CHANGELOG

## v7.4.0 (2024-07-24)

- Add new `Claim` service for filing claims on EasyPost shipments and insurances

## v7.3.0 (2024-07-16)

- Adds new `shipment.recommendShipDate`, `smartrate.recommendShipDate`, and `smartrate.estimateDeliveryDate` functions
- Routes `UpsAccount`, `UpsMailInnovationsAccount`, and `UpsSurepostAccount` create/update requests to the new `/ups_oauth_registrations` endpoint
  - Starting `2024-08-05`, UPS accounts will require a new payload to register or update. See [UPS OAuth 2.0 Update](https://support.easypost.com/hc/en-us/articles/26635027512717-UPS-OAuth-2-0-Update?utm_medium=email&_hsenc=p2ANqtz-96MmFtWICOzy9sKRbbcZSiMovZSrY3MSX1_bgY9N3f9yLVfWQdLhjAGq-SmNcOnDIS6GYhZ0OApjDBrGkKyLLMx1z6_TFOVp6-wllhEFQINrkuRuc&_hsmi=313130292&utm_content=313130292&utm_source=hs_email) for more details

## v7.2.1 (2024-04-12)

- Fix `Fields` serialization bug causing carrier account operations to fail

## v7.2.0 (2024-04-10)

- Adds `refund` function in Insurance service for requesting a refund for a standalone insurance
- Fix payment method funding and deletion failures due to undetermined payment method type
- Fix `toString` method for all EasyPost models

## v7.1.1 (2024-03-21)

- Fix `EasyPostTimeInTransitData` class and `easypostTimeInTransitData` property of `EstimatedDeliveryDate` class being publicly inaccessible

## v7.1.0 (2024-01-08)

- Adds `allChildren` function in User service to get a paginated list of child users
- Adds `getNextPage` function in User service to get next paginated list of child users

## v7.0.1 (2023-12-08)

- Adds the `object` field to all models; previously, most models were missing this field.

## v7.0.0 (2023-12-06)

- Removes `withCarbonOffset` parameter from `create`, `buy`, and `regenerateRates` functions of the Shipment service as EasyPost now offers Carbon Neutral shipments by default for free
- Removes the undocumented `createAndBuy` function from the Batch service. The proper usage is to create a batch first and buy it separately
- Changes return type of `all()` in webhook service from `WebhookCollection` to `a list of webhooks`
- `BetaCarrierMetadata` service has been removed. Please use `CarrierMetadata` instead.

## v6.9.1 (2023-11-16)

- Fixes a bug where address verification errors were not being properly deserialized

## v6.9.0 (2023-10-11)

- Deprecates `user.apiKeys` which will be removed in a future release, please use the new `apiKey.all` and `apiKey.retrieveApiKeysForUser` functions instead instead
- Fixes various build warnings

## v6.8.1 (2023-09-05)

- Fix endpoint for creating a FedEx Smartpost carrier account

## v6.8.0 (2023-07-28)

- Adds new `RequestHook` and `ResponseHook` classes. (un)subscribe to them with the new `subscribeToRequestHook`, `subscribeToResponseHook`, `unsubscribeFromRequestHook`, or `unsubscribeFromResponseHook` methods of an `EasyPostClient`
- Maps 400 status codes to the new `BadRequestError` class

## v6.7.0 (2023-06-06)

- Retrieving carrier metadata is now generally available via `client.carrierMetadata.retrieve`
- Bump all dependencies

## v6.6.1 (2023-05-09)

- Test suite tweaked so EasyVCR is no longer a production dependency

## v6.6.0 (2023-05-02)

- Adds `retrieveEstimatedDeliveryDate` function to the Shipment class

## v6.5.0 (2023-04-18)

- Adds beta `retrieveCarrierMetadata` function
- Fixes the type for `requestBody` from Map to String for Payload class
- Add `retrieveCarrierMetadata` method to retrieve carrier metadata for carriers

## v6.4.0 (2023-04-04)

- Adds `getNextPage` function to each service which retrieves the next page of a collection when the `has_more` key is present in the response (eg: `client.address.getNextPage(addressCollection)`)
- Adds missing Event `status` property

## v6.3.1 (2023-03-22)

- Handle edge cases when deseralizing error JSON, closes Github issue [#239](https://github.com/EasyPost/easypost-java/issues/239)

## v6.3.0 (2023-02-21)

- Adds beta `retrieveStatelessRates` function to get stateless rates
- Adds `getLowestStatelessRate` function to filter the lowest stateless rate

## v6.2.0 (2023-01-18)

- Adds `all` function to `Pickup` to retrieve all pickups
- Adds `retrieveAllPayloads` and `retrievePayload` to retrieve all payloads or a single event payload

## v6.1.0 (2023-01-11)

- Adds new beta billing functionality for ReferralCustomer users
  - `addPaymentMethod` can add a pre-existing Stripe bank account or credit card to your EasyPost account
  - `refundByAmount` refunds your wallet by a dollar amount
  - `refundByPaymentLog` refunds you wallet by a PaymentLog ID

## v6.0.0 (2023-01-05)

Includes all the changes from `v6.0.0-rc1` listed below in addition to the following:

- All constants are now defined in the top-level `Constants` class (`com.easypost.Constants`)
  - Users who are utilizing the constants for exception message parsing should account for the new namespace
- Added support for creating/registering carrier accounts that require custom workflows (e.g. FedEx, UPS)
- Swapped the parameters for the following functions, making `id` the first argument
  - `Shipment.generateForm()`
  - `EndShipper.update()`
  - `CarrierAccount.update()`
- Fixed a typo, `ServiceUnavailablError` is now `ServiceUnavailableError`
- Fixed a typo: `Smartrate` is now `SmartRate`, `apikeys` is now `apiKey`, `scanform` is now `scanForm`
- Moved `validateWebook`, `findLowestSmartrate`, and `getLowestSmartRate` from Services to Utilities since it does not required an EasyPost client object
- Added a root level `APIException` class for all HTTP exceptions

## v6.0.0-rc1 (2022-11-30)

### Breaking Changes

- Library is now thread-safe
  - Initialize an `EasyPostClient` object with an API key. Optionally set connection and readtime params
  - All methods (i.e. `create`, `retrieve`, `all`) exist in services, accessed via property of the client (eg: `client.shipment.create()`)
    - E.g. Static method -> `Shipment shipment = client.address.create(params)`
    - E.g. Instance methods are now static -> `Shipment boughtShipment = client.shipment.buy(shipmentID, lowestRate)`
  - All functions are now taking object ID instead of an object, which means the functions are returning the response value directly instead of updating the current object
    - E.g. `Shipment boughtShipment = client.shipment.buy(shipmentID, lowestRate)`
- Improves error exception handling
  - Specific error types for each category of error
  - API error message may be an array rather than a string. Arrays will be concatenated (by comma) and returned as a string.
- Removes `createAndVerifyWithCarrier` function
- Removes `verifyWithCarrier` function
- Removes `getUpdate` and `setUpdate` from Tracker class
- Removes all beta features and corrsponding unit tests
- Removes `serviceCode` from `Rate` class since this value is internal use only
- Removes invalid function `User.create()` since creating a child-user requires a name
- Removes deprecated class `CreditCard` and its associated classes `PrimaryPaymentMethod`, `SecondaryPaymentMethod`, and `BaseCreditCard`, please use alternative `Billing` class
- Removes all the setters of each object
- Removes the `refresh()` function in all classes, please use `retrieve()` function instead
- Removes invalid functions that allow users to make an API request without providing required parameters
- Changes the type of Insurance `Amount` from Float to String
- Changes the type `hasMore` in ReferralCustomerCollection from `boolean` to `Boolean`
- Renames some getters
  - Pickup class: `getPickoutRates()` -> `getPickupRates()`
  - PaymentMethod class: `getPrimaryPaymentMethodObject()` -> `getPrimaryPaymentMethod()`
  - PaymentMethod class: `getSecondaryPaymentMethodObject()` -> `getSecondaryPaymentMethod()`
- Converts return type from a boolean to void when we receive an empty response body from the API
  - `fundWallet()` and `deletePaymentMethod()` in Billing class
  - `createList()` in Tracker class
  - `updateEmail()` in ReferralCustomer class
- Changes the type `result` of Event from `EasyPostResource` to `Map<String, Object>`

### Bug Fixes

- Adds two missing attributes in the Event class: `pendingUrls` and `completedUrls`
- AppEngine default timeout is now the same as connection timeout

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
