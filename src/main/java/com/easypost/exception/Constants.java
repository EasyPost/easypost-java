package com.easypost.exception;

public abstract class Constants {
    public static final String EXTERNAL_API_CALL_FAILED = "Could not send card details to %s, please try again later";
    public static final String ENCODED_ERROR = "Encode error for %s";
    public static final String INVALID_API_KEY_TYPE = "Invalid API key type.";
    public static final String INVALID_PARAMETER = "Invalid parameter: %s.";
    public static final String INVALID_PAYMENT = "The chosen payment method is not a credit card. Please try again.";
    public static final String INVALID_WEBHOOK_SIGNATURE = "Webhook does not contain a valid HMAC signature.";
    public static final String MISSING_REQUIRED_PARAMETER = "Missing required parameter: %s.";
    public static final String NO_OBJECT_FOUND = "No %s found.";
    public static final String NO_PAYMENT_METHODS =
        "No payment methods are set up. Please add a payment method and try again.";
    public static final String API_DID_NOT_RETURN_ERROR_DETAILS = "API did not return error details.";
    public static final String WEBHOOK_DOES_NOT_MATCH =
        "Webhook received did not originate from EasyPost or had a webhook secret mismatch.";

    public abstract class ErrorCode {
        public static final int REDIRECT_CODE_BEGIN = 300;
        public static final int REDIRECT_CODE_END = 308;
        public static final int UNAUTHORIZED_ERROR = 401;
        public static final int PAYMENT_ERROR = 402;
        public static final int FORBIDDEN_ERROR = 403;
        public static final int NOT_FOUND_ERROR = 404;
        public static final int METHOD_NOT_ALLOWED_ERROR = 405;
        public static final int TIMEOUT_ERROR = 408;
        public static final int INVALID_REQUEST_ERROR = 422;
        public static final int RATE_LIMIT_ERROR = 429;
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final int SERVICE_UNAVAILABLE_ERROR = 503;
        public static final int GATEWAY_TIMEOUT_ERROR = 504;
    }
}
