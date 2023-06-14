package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.API.EncodingError;
import com.easypost.exception.API.ForbiddenError;
import com.easypost.exception.API.GatewayTimeoutError;
import com.easypost.exception.API.HttpError;
import com.easypost.exception.API.InternalServerError;
import com.easypost.exception.API.InvalidRequestError;
import com.easypost.exception.API.JsonError;
import com.easypost.exception.API.MethodNotAllowedError;
import com.easypost.exception.API.NotFoundError;
import com.easypost.exception.API.PaymentError;
import com.easypost.exception.API.RateLimitError;
import com.easypost.exception.API.RedirectError;
import com.easypost.exception.API.ServiceUnavailableError;
import com.easypost.exception.API.TimeoutError;
import com.easypost.exception.API.UnauthorizedError;
import com.easypost.exception.API.UnknownApiError;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.http.Requestor;

import java.util.Map;

public class EasyPostClient {
    private final int connectTimeoutMilliseconds;
    private final int readTimeoutMilliseconds;
    private final String clientApiKey; // API key for all EasyPost API requests
    private final String apiVersion = "v2";
    private final String apiBase;
    public final AddressService address;
    public final ApiKeyService apiKey;
    public final BatchService batch;
    public final BetaCarrierMetadataService betaCarrierMetadata;
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
        this(apiKey, Constants.Http.DEFAULT_CONNECT_TIMEOUT_MILLISECONDS);
    }

    /**
     * EasyPostClient constructor.
     *
     * @param apiKey  API key for API calls.
     * @param apiBase API base for API calls.
     * @throws MissingParameterError
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
     * @throws MissingParameterError
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
     * @throws MissingParameterError
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
     * @throws MissingParameterError
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
     * @throws MissingParameterError
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
        this.betaCarrierMetadata = new BetaCarrierMetadataService(this);
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

    /**
     * Execute an HTTP request for a given method and URL with this EasyPostClient object.
     *
     * @param method   the HTTP method to use
     * @param endpoint the API endpoint to use
     * @param params   the parameters to use
     * @param clazz    the class to use for deserializing the response
     * @param <T>      the type of object to return
     * @return the deserialized response
     */
    public <T> T request(final Requestor.RequestMethod method, final String endpoint, final Map<String, Object> params,
                         final Class<T> clazz)
            throws GatewayTimeoutError, RateLimitError, InvalidRequestError, NotFoundError, TimeoutError, EncodingError,
            UnauthorizedError, MethodNotAllowedError, InternalServerError, UnknownApiError, ServiceUnavailableError,
            ForbiddenError, JsonError, HttpError, RedirectError, PaymentError {
        return Requestor.request(method, endpoint, params, clazz, this);
    }

    public <T> T request(final Requestor.RequestMethod method, final String endpoint, final Map<String, Object> params,
                         final Class<T> clazz, final String apiVersion)
            throws GatewayTimeoutError, RateLimitError, InvalidRequestError, NotFoundError, TimeoutError, EncodingError,
            UnauthorizedError, MethodNotAllowedError, InternalServerError, UnknownApiError, ServiceUnavailableError,
            ForbiddenError, JsonError, HttpError, RedirectError, PaymentError {
        return Requestor.request(method, endpoint, params, clazz, this, apiVersion);
    }
}
