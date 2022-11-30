package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.http.Constant;

public class EasyPostClient {
    private final int connectTimeoutMilliseconds;
    private final int readTimeoutMilliseconds;
    private final String apiKey;
    private final String apiVersion = "v2";
    private final String apiBase;
    public final AddressService address;
    public final ApiKeyService apikeys;
    public final BatchService batch;
    public final BillingService billing;
    public final CarrierAccountService carrierAccount;
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
    public final ScanformService scanform;
    public final ShipmentService shipment;
    public final TrackerService tracker;
    public final UserService user;
    public final WebhookService webhook;

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey API key for API calls.
     * @throws MissingParameterError
     */
    public EasyPostClient(String apiKey) throws MissingParameterError {
        this(apiKey, Constant.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey  API key for API calls.
     * @param apiBase API base for API calls.
     * @throws MissingParameterError
     */
    public EasyPostClient(String apiKey, String apiBase) throws MissingParameterError {
        this(apiKey, Constant.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS, Constant.DEFAULT_READ_TIMEOUT_MILLISECONDS,
                apiBase);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @throws MissingParameterError
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds) throws MissingParameterError {
        this(apiKey, connectTimeoutMilliseconds, Constant.API_BASE);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @param apiBase                    API base for API calls.
     * @throws MissingParameterError
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, String apiBase) throws MissingParameterError {
        this(apiKey, connectTimeoutMilliseconds, Constant.DEFAULT_READ_TIMEOUT_MILLISECONDS, apiBase);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @param readTimeoutMilliseconds    Timeout for read.
     * @throws MissingParameterError
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, int readTimeoutMilliseconds)
            throws MissingParameterError {
        this(apiKey, connectTimeoutMilliseconds, readTimeoutMilliseconds, Constant.API_BASE);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey                     API key for API calls.
     * @param connectTimeoutMilliseconds Timeout for connection.
     * @param readTimeoutMilliseconds    Timeout for read.
     * @param apiBase                    API base for API calls.
     * @throws MissingParameterError
     */
    public EasyPostClient(String apiKey, int connectTimeoutMilliseconds, int readTimeoutMilliseconds, String apiBase)
            throws MissingParameterError {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new MissingParameterError(Constants.ErrorMessages.INVALID_API_KEY_TYPE);
        }

        this.apiBase = apiBase;
        this.apiKey = apiKey;
        this.connectTimeoutMilliseconds = connectTimeoutMilliseconds;
        this.readTimeoutMilliseconds = readTimeoutMilliseconds;
        this.address = new AddressService(this);
        this.apikeys = new ApiKeyService(this);
        this.batch = new BatchService(this);
        this.billing = new BillingService(this);
        this.carrierAccount = new CarrierAccountService(this);
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
        this.scanform = new ScanformService(this);
        this.shipment = new ShipmentService(this);
        this.tracker = new TrackerService(this);
        this.user = new UserService(this);
        this.webhook = new WebhookService(this);
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
        return apiKey;
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
