package com.easypost;

import com.easypost.http.HashMapSerializer;
import com.easypost.model.AddressVerification;
import com.easypost.model.AddressVerificationDeserializer;
import com.easypost.model.DateDeserializer;
import com.easypost.model.Error;
import com.easypost.model.ErrorDeserializer;
import com.easypost.model.SmartrateCollection;
import com.easypost.model.SmartrateCollectionDeserializer;
import com.easypost.model.StatelessRate;
import com.easypost.model.StatelessRateDeserializer;
import com.easypost.model.Webhook;
import com.easypost.model.WebhookDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public abstract class Constants {

    public static final String EASYPOST_SUPPORT_EMAIL = "support@easypost.com";

    public abstract static class ErrorMessages {
        public static final String EXTERNAL_API_CALL_FAILED =
                "Could not send card details to %s, please try again later";
        public static final String ENCODED_ERROR = "Encode error for %s";
        public static final String INVALID_API_KEY_TYPE = "Invalid API key type.";
        public static final String INVALID_PARAMETER = "Invalid parameter: %s.";
        public static final String INVALID_PAYMENT =
                "The chosen payment method is not a credit card. Please try again.";
        public static final String INVALID_WEBHOOK_SIGNATURE = "Webhook does not contain a valid HMAC signature.";
        public static final String MISSING_REQUIRED_PARAMETER = "Missing required parameter: %s.";
        public static final String NO_OBJECT_FOUND = "No %s found.";
        public static final String NO_PAYMENT_METHODS =
                "No payment methods are set up. Please add a payment method and try again.";
        public static final String API_DID_NOT_RETURN_ERROR_DETAILS = "API did not return error details.";
        public static final String WEBHOOK_DOES_NOT_MATCH =
                "Webhook received did not originate from EasyPost or had a webhook secret mismatch.";

        public static final String NO_MORE_PAGES_TO_RETRIEVE = "There are no more pages to retrieve.";
    }

    public abstract static class ErrorCodes {
        public static final int REDIRECT_CODE_BEGIN = 300;
        public static final int REDIRECT_CODE_END = 308;
        public static final int BAD_REQUEST_ERROR = 400;
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

    public abstract static class CarrierAccountTypes {
        public static final List<String> CARRIER_TYPES_WITH_CUSTOM_WORKFLOW = ImmutableList.of("FedexAccount",
                "FedexSmartpostAccount");
    }

    public abstract static class UpsAccountTypes {
        public static final List<String> UPS_OAUTH_CARRIER_ACCOUNT_TYPES = ImmutableList.of("UpsAccount",
                "UpsMailInnovationsAccount", "UpsSurepostAccount");
    }

    public abstract static class Http {
        public static final String API_BASE = "https://api.easypost.com";
        public static final String CHARSET = "UTF-8";
        public static final int DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = 30000;
        public static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60000;

        public static final Gson GSON = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(HashMap.class, new HashMapSerializer())
                .registerTypeAdapter(SmartrateCollection.class, new SmartrateCollectionDeserializer())
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .registerTypeAdapter(AddressVerification.class, new AddressVerificationDeserializer())
                .registerTypeAdapter(StatelessRate[].class, new StatelessRateDeserializer())
                .registerTypeAdapter(Webhook[].class, new WebhookDeserializer())
                .registerTypeAdapter(Date.class, new DateDeserializer()).create();
        public static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }
}
