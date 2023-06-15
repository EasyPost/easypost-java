package com.easypost.mocking;

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
import com.easypost.service.EasyPostClient;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.easypost.http.Requestor.handleAPIError;

@Getter
public final class MockClient extends EasyPostClient {
    private final EasyPostClient client;
    private List<MockRequest> requests = new ArrayList<>();
    private boolean passThrough = false;

    /**
     * Construct a new MockClient.
     *
     * @throws MissingParameterError if the API key is not provided (not needed for mocking)
     */
    public MockClient() throws MissingParameterError {
        // If no real client provided for wrapper,
        // passthrough mode is automatically disabled to avoid failing with a fake API key
        this(new EasyPostClient("not_needed"), new ArrayList<>(), false);
    }

    /**
     * Construct a new MockClient.
     *
     * @param client The real client to use for passthrough mode.
     * @throws MissingParameterError if the API key is not provided (not needed for mocking)
     */
    public MockClient(EasyPostClient client) throws MissingParameterError {
        this(client, new ArrayList<>(), false);
    }

    /**
     * Construct a new MockClient.
     *
     * @param client   The real client to use for passthrough mode.
     * @param requests The list of requests to match against.
     * @throws MissingParameterError if the API key is not provided (not needed for mocking)
     */
    public MockClient(EasyPostClient client, List<MockRequest> requests) throws MissingParameterError {
        this(client, requests, false);
    }

    /**
     * Construct a new MockClient.
     *
     * @param client      The real client to use for passthrough mode.
     * @param passThrough Whether to execute requests that don't match the mock requests.
     * @throws MissingParameterError if the API key is not provided (not needed for mocking)
     */
    public MockClient(EasyPostClient client, boolean passThrough) throws MissingParameterError {
        this(client, new ArrayList<>(), passThrough);
    }

    /**
     * Construct a new MockClient.
     *
     * @param client      The real client to use for passthrough mode.
     * @param requests    The list of requests to match against.
     * @param passThrough Whether to execute requests that don't match the mock requests.
     * @throws MissingParameterError if the API key is not provided (not needed for mocking)
     */
    public MockClient(EasyPostClient client, List<MockRequest> requests, boolean passThrough)
            throws MissingParameterError {
        super("not_needed");
        this.client = client;
        this.requests = requests;
        this.passThrough = passThrough;
    }

    /**
     * Add a request to the list of requests to match against.
     *
     * @param request The request to add.
     */
    public void addRequest(MockRequest request) {
        this.requests.add(request);
    }

    /**
     * Add a list of requests to the list of requests to match against.
     *
     * @param requests The list of requests to add.
     */
    public void addRequests(List<MockRequest> requests) {
        this.requests.addAll(requests);
    }

    /**
     * Clear the list of requests to match against.
     */
    public void clearRequests() {
        this.requests.clear();
    }

    /**
     * Check if the provided endpoint matches the provided regex pattern.
     *
     * @param endpoint     The endpoint to check.
     * @param regExPattern The regex pattern to match against.
     * @return Whether the endpoint matches the regex pattern.
     */
    private boolean endpointMatches(String endpoint, String regExPattern) {
        Pattern pattern = Pattern.compile(regExPattern);
        return pattern.matcher(endpoint).matches();
    }

    /**
     * Find a matching request in the list of requests to match against.
     *
     * @param method     The request method.
     * @param endpoint   The request endpoint.
     * @param apiVersion The API version to use.
     * @return The matching request, or null if no match was found.
     */
    private MockRequest findMatchingRequest(Requestor.RequestMethod method, String endpoint,
                                            @Nullable String apiVersion) {
        if (apiVersion == null) {
            apiVersion = this.getApiVersion();
        }
        String fullEndpoint = apiVersion + "/" + endpoint;

        for (MockRequest request : this.requests) {
            MockRequestMatchRules rules = request.getMatchRules();
            if (method == rules.getMethod() && endpointMatches(fullEndpoint, rules.getRegex())) {
                return request;
            }
        }

        return null;
    }

    /**
     * Process a mock request.
     *
     * @param method     The request method.
     * @param endpoint   The request endpoint.
     * @param params     The request parameters.
     * @param clazz      The class to deserialize the response to.
     * @param apiVersion The API version to use.
     * @param <T>        The type of the response.
     * @return The deserialized response.
     */
    private <T> T processMockRequest(Requestor.RequestMethod method, String endpoint, Map<String, Object> params,
                                     Class<T> clazz, @Nullable String apiVersion)
            throws InvalidRequestError, UnknownApiError, ServiceUnavailableError, GatewayTimeoutError, ForbiddenError,
            RateLimitError, NotFoundError, TimeoutError, RedirectError, UnauthorizedError, MethodNotAllowedError,
            InternalServerError, PaymentError, JsonError, HttpError, EncodingError {
        MockRequest matchingRequest = findMatchingRequest(method, endpoint, apiVersion);

        if (matchingRequest == null) {
            if (this.passThrough) { // if true, we want to make the actual API call if no mock request is found
                if (apiVersion == null) {
                    return this.client.request(method, endpoint, params, clazz);
                }
                return this.client.request(method, endpoint, params, clazz, apiVersion);
            }
            throw new InvalidRequestError("No matching mock request found.", null, 0, null);
        }

        MockResponse response = matchingRequest.getResponse();
        String content = response.getContent();
        int statusCode = response.getStatusCode();

        if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
            handleAPIError(content, statusCode);
        }

        return Constants.Http.GSON.fromJson(content, clazz);

    }

    @Override
    public <T> T request(Requestor.RequestMethod method, String endpoint, Map<String, Object> params, Class<T> clazz)
            throws GatewayTimeoutError, RateLimitError, InvalidRequestError, NotFoundError, TimeoutError, EncodingError,
            UnauthorizedError, MethodNotAllowedError, InternalServerError, UnknownApiError, ServiceUnavailableError,
            ForbiddenError, JsonError, HttpError, RedirectError, PaymentError {
        return processMockRequest(method, endpoint, params, clazz, null);
    }

    @Override
    public <T> T request(Requestor.RequestMethod method, String endpoint, Map<String, Object> params, Class<T> clazz,
                         String apiVersion)
            throws GatewayTimeoutError, RateLimitError, InvalidRequestError, NotFoundError, TimeoutError, EncodingError,
            UnauthorizedError, MethodNotAllowedError, InternalServerError, UnknownApiError, ServiceUnavailableError,
            ForbiddenError, JsonError, HttpError, RedirectError, PaymentError {
        return processMockRequest(method, endpoint, params, clazz, apiVersion);
    }
}
