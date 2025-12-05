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
    // Services
    public final AddressService address = new AddressService(this);
    public final ApiKeyService apiKey = new ApiKeyService(this);
    public final BatchService batch = new BatchService(this);
    public final BetaRateService betaRate = new BetaRateService(this);
    public final BetaReferralCustomerService betaReferralCustomer = new BetaReferralCustomerService(this);
    public final BillingService billing = new BillingService(this);
    public final CarrierAccountService carrierAccount = new CarrierAccountService(this);
    public final CarrierMetadataService carrierMetadata = new CarrierMetadataService(this);
    public final FedexRegistrationService fedexRegistration = new FedexRegistrationService(this);
    public final CarrierTypeService carrierType = new CarrierTypeService(this);
    public final ClaimService claim = new ClaimService(this);
    public final CustomerPortalService customerPortal = new CustomerPortalService(this);
    public final CustomsInfoService customsInfo = new CustomsInfoService(this);
    public final CustomsItemService customsItem = new CustomsItemService(this);
    public final EmbeddableService embeddable = new EmbeddableService(this);
    public final EndShipperService endShipper = new EndShipperService(this);
    public final EventService event = new EventService(this);
    public final InsuranceService insurance = new InsuranceService(this);
    public final LumaService luma = new LumaService(this);
    public final OrderService order = new OrderService(this);
    public final ParcelService parcel = new ParcelService(this);
    public final PaymentMethodService paymentMethod = new PaymentMethodService(this);
    public final PickupService pickup = new PickupService(this);
    public final RateService rate = new RateService(this);
    public final ReferralCustomerService referralCustomer = new ReferralCustomerService(this);
    public final RefundService refund = new RefundService(this);
    public final ReportService report = new ReportService(this);
    public final ScanformService scanForm = new ScanformService(this);
    public final ShipmentService shipment = new ShipmentService(this);
    public final SmartRateService smartRate = new SmartRateService(this);
    public final TrackerService tracker = new TrackerService(this);
    public final UserService user = new UserService(this);
    public final WebhookService webhook = new WebhookService(this);
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
