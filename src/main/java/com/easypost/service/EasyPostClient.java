package com.easypost.service;

import lombok.Getter;

import com.easypost.Constants;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.hooks.ResponseHook;
import com.easypost.hooks.ResponseHookResponses;
import com.easypost.hooks.RequestHook;
import com.easypost.hooks.RequestHookResponses;

import java.util.function.Function;

public class EasyPostClient {
    private final int connectTimeoutMilliseconds;
    private final int readTimeoutMilliseconds;
    private final String clientApiKey; // API key for all EasyPost API requests
    private final String apiVersion = "v2";
    private final String apiBase;
    public final AddressService address;
    public final ApiKeyService apiKey;
    public final BatchService batch;
    public final BetaReferralCustomerService betaReferralCustomer;
    public final BetaRateService betaRate;
    public final BillingService billing;
    public final CarrierAccountService carrierAccount;
    public final CarrierMetadataService carrierMetadata;
    public final CarrierTypeService carrierType;
    public final CustomsInfoService customsInfo;
    public final CustomsItemService customsItem;
    public final EndShipperService endShipper;
    public final EventService event;
    public final InsuranceService insurance;
    public final OrderService order;
    public final ParcelService parcel;
    public final PaymentMethodService paymentMethod;
    public final PickupService pickup;
    public final RateService rate;
    public final ReferralCustomerService referralCustomer;
    public final RefundService refund;
    public final ReportService report;
    public final ScanformService scanForm;
    public final ShipmentService shipment;
    public final SmartRateService smartRate;
    public final TrackerService tracker;
    public final UserService user;
    public final WebhookService webhook;
    @Getter
    private RequestHook requestHooks = new RequestHook();
    @Getter
    private ResponseHook responseHooks = new ResponseHook();

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey API key for API calls.
     * @throws MissingParameterError When the request fails.
     */
    public EasyPostClient(String apiKey) throws MissingParameterError {
        this(apiKey, Constants.Http.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey  API key for API calls.
     * @param apiBase API base for API calls.
     * @throws MissingParameterError When the request fails.
     */
    public EasyPostClient(String apiKey, String apiBase) throws MissingParameterError {
        this(apiKey, Constants.Http.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS,
                Constants.Http.DEFAULT_READ_TIMEOUT_MILLISECONDS, apiBase);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @throws MissingParameterError     When the request fails.
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds) throws MissingParameterError {
        this(apiKey, connectTimeoutMilliseconds, Constants.Http.API_BASE);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @param apiBase                    API base for API calls.
     * @throws MissingParameterError     When the request fails.
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, String apiBase) throws MissingParameterError {
        this(apiKey, connectTimeoutMilliseconds, Constants.Http.DEFAULT_READ_TIMEOUT_MILLISECONDS, apiBase);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @param readTimeoutMilliseconds    Timeout for read.
     * @throws MissingParameterError     When the request fails.
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, int readTimeoutMilliseconds)
            throws MissingParameterError {
        this(apiKey, connectTimeoutMilliseconds, readTimeoutMilliseconds, Constants.Http.API_BASE);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @param readTimeoutMilliseconds    Timeout for read.
     * @param apiBase                    API base for API calls.
     * @throws MissingParameterError     When the request fails.
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, int readTimeoutMilliseconds, String apiBase)
            throws MissingParameterError {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new MissingParameterError("apiKey");
        }

        this.apiBase = apiBase;
        this.clientApiKey = apiKey;
        this.connectTimeoutMilliseconds = connectTimeoutMilliseconds;
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;
        this.address = new AddressService(this);
        this.apiKey = new ApiKeyService(this);
        this.batch = new BatchService(this);
        this.betaReferralCustomer = new BetaReferralCustomerService(this);
        this.betaRate = new BetaRateService(this);
        this.billing = new BillingService(this);
        this.carrierAccount = new CarrierAccountService(this);
        this.carrierMetadata = new CarrierMetadataService(this);
        this.carrierType = new CarrierTypeService(this);
        this.customsInfo = new CustomsInfoService(this);
        this.customsItem = new CustomsItemService(this);
        this.endShipper = new EndShipperService(this);
        this.event = new EventService(this);
        this.insurance = new InsuranceService(this);
        this.order = new OrderService(this);
        this.parcel = new ParcelService(this);
        this.paymentMethod = new PaymentMethodService(this);
        this.pickup = new PickupService(this);
        this.rate = new RateService(this);
        this.referralCustomer = new ReferralCustomerService(this);
        this.refund = new RefundService(this);
        this.report = new ReportService(this);
        this.scanForm = new ScanformService(this);
        this.shipment = new ShipmentService(this);
        this.smartRate = new SmartRateService(this);
        this.tracker = new TrackerService(this);
        this.user = new UserService(this);
        this.webhook = new WebhookService(this);
    }

    /**
     * Subscribes to a request hook from the given function.
     * @param function The function to be subscribed to the request hook
     */
    public void subscribeToRequestHook(Function<RequestHookResponses, Object> function) {
        this.requestHooks.addEventHandler(function);
    }

    /**
     * Unsubscribes to a request hook from the given function.
     * @param function The function to be unsubscribed from the request hook
     */
    public void unsubscribeFromRequestHook(Function<RequestHookResponses, Object> function) {
        this.requestHooks.removeEventHandler(function);
    }

    /**
     * Subscribes to a response hook from the given function.
     * @param function The function to be subscribed to the response hook
     */
    public void subscribeToResponseHook(Function<ResponseHookResponses, Object> function) {
        this.responseHooks.addEventHandler(function);
    }

    /**
     * Unubscribes to a response hook from the given function.
     * @param function The function to be unsubscribed from the response hook
     */
    public void unsubscribeFromResponseHook(Function<ResponseHookResponses, Object> function) {
        this.responseHooks.removeEventHandler(function);
    }

    /**
     * Get connection timeout milliseconds for this EasyPostClient object.
     *
     * @return the connection timeout for this EasyPostClient object
     */
    public int getConnectionTimeoutMilliseconds() {
        return connectTimeoutMilliseconds;
    }

    /**
     * Get read timeout milliseconds for this EasyPostClient object.
     *
     * @return the read timeout for this EasyPostClient object
     */
    public int getReadTimeoutMilliseconds() {
        return readTimeoutMilliseconds;
    }

    /**
     * Get API key for this EasyPostClient object.
     *
     * @return the API key for this EasyPostClient object
     */
    public String getApiKey() {
        return clientApiKey;
    }

    /**
     * Get API version for this EasyPostClient object.
     *
     * @return the API version for this EasyPostClient object.
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * Get API base for this EasyPostClient object.
     *
     * @return the API base for this EasyPostClient object.
     */
    public String getApiBase() {
        return apiBase;
    }
}
