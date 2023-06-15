package com.easypost.mocking;

import com.easypost.Constants;
import com.easypost.easyvcr.MatchRules;
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
import com.easypost.exception.APIException;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.http.Requestor;
import com.easypost.service.EasyPostClient;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.easypost.http.Requestor.handleAPIError;

@Getter
public class MockClient extends EasyPostClient {
    private EasyPostClient client;
    private List<MockRequest> requests = new ArrayList<>();
    private boolean passThrough = false;

    public MockClient() throws MissingParameterError {
        // If no real client provided for wrapper, passthrough mode is automatically disabled to avoid failing with a fake API key
        this(new EasyPostClient("not_needed"), new ArrayList<>(), false);
    }

    public MockClient(EasyPostClient client) throws MissingParameterError {
        this(client, new ArrayList<>(), false);
    }

    public MockClient(EasyPostClient client, List<MockRequest> requests) throws MissingParameterError {
        this(client, requests, false);
    }

    public MockClient(EasyPostClient client, boolean passThrough) throws MissingParameterError {
        this(client, new ArrayList<>(), passThrough);
    }

    public MockClient(EasyPostClient client, List<MockRequest> requests, boolean passThrough) throws MissingParameterError {
        super("not_needed");
        this.client = client;
        this.requests = requests;
        this.passThrough = passThrough;
    }

    public void addRequest(MockRequest request) {
        this.requests.add(request);
    }

    public void addRequests(List<MockRequest> requests) {
        this.requests.addAll(requests);
    }

    public void clearRequests() {
        this.requests.clear();
    }

    private boolean endpointMatches(String endpoint, String regExPattern) {
        Pattern pattern = Pattern.compile(regExPattern);
        return pattern.matcher(endpoint).matches();
    }

    private MockRequest findMatchingRequest(Requestor.RequestMethod method, String endpoint, @Nullable String apiVersion) {
        if (apiVersion == null) {
            apiVersion = this.getApiVersion();
        }
        String fullEndpoint = apiVersion + "/" + endpoint;

        for (MockRequest request : this.requests) {
            MockRequestMatchRules rules = request.matchRules;
            if (method == rules.method && endpointMatches(fullEndpoint, rules.regex)) {
                return request;
            }
        }

        return null;
    }

    private <T> T processMockRequest(Requestor.RequestMethod method, String endpoint, Map<String, Object> params, Class<T> clazz,
                          @Nullable String apiVersion)
            throws InvalidRequestError, UnknownApiError, ServiceUnavailableError, GatewayTimeoutError, ForbiddenError,
            RateLimitError, NotFoundError, TimeoutError, RedirectError, UnauthorizedError, MethodNotAllowedError,
            InternalServerError, PaymentError, JsonError, HttpError, EncodingError {
        MockRequest matchingRequest = findMatchingRequest(method, endpoint, apiVersion);

        if (matchingRequest == null) {
            if (this.passThrough) { // if passThrough is true, then we want to make the actual API call if no mock request is found
                if (apiVersion == null) {
                    return this.client.request(method, endpoint, params, clazz);
                }
                return this.client.request(method, endpoint, params, clazz, apiVersion);
            }
            throw new InvalidRequestError("No matching mock request found.", null, 0, null);
        }

        MockResponse response = matchingRequest.getResponse();
        String content = response.content;
        int statusCode = response.statusCode;

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
