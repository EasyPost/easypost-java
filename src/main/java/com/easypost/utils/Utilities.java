package com.easypost.utils;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.FilteringError;
import com.easypost.exception.General.SignatureVerificationError;
import com.easypost.model.Event;
import com.easypost.model.Rate;
import com.easypost.model.SmartRate;
import com.easypost.model.SmartrateAccuracy;
import com.easypost.model.StatelessRate;

import java.util.List;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public abstract class Utilities {
    /**
     * Get the lowest stateless rate from a list of stateless rates.
     *
     * @param rates    the list of stateless rates.
     * @param carriers the carriers to use in the filter.
     * @param services the services to use in the filter.
     * @return lowest StatelessRate object
     * @throws FilteringError when the filters could not be applied.
     */
    public static StatelessRate getLowestStatelessRate(List<StatelessRate> rates, List<String> carriers,
            List<String> services)
            throws FilteringError, NumberFormatException {
        StatelessRate lowestRate = null;

        if (carriers != null) {
            carriers.replaceAll(String::toLowerCase);
        }

        if (services != null) {
            services.replaceAll(String::toLowerCase);
        }

        for (StatelessRate rate : rates) {
            if ((carriers != null && !carriers.contains(rate.getCarrier().toLowerCase())) ||
                    (services != null && !services.contains(rate.getService().toLowerCase()))) {
                continue;
            }

            if (lowestRate == null || Float.parseFloat(rate.getRate()) < Float.parseFloat(lowestRate.getRate())) {
                lowestRate = rate;
            }
        }

        if (lowestRate == null) {
            throw new FilteringError(String.format(
                    Constants.ErrorMessages.NO_OBJECT_FOUND, "rates"));
        }

        return lowestRate;
    }

    /**
     * Get the lowest rate from a list of rates.
     *
     * @param rates    the list of rates.
     * @param carriers the carriers to use in the filter.
     * @param services the services to use in the filter.
     * @return lowest Rate object
     * @throws FilteringError when the filters could not be applied.
     */
    public static Rate getLowestObjectRate(List<Rate> rates, List<String> carriers, List<String> services)
            throws FilteringError {
        Rate lowestRate = null;

        if (carriers != null) {
            carriers.replaceAll(String::toLowerCase);
        }

        if (services != null) {
            services.replaceAll(String::toLowerCase);
        }

        for (Rate rate : rates) {
            if ((carriers != null && !carriers.contains(rate.getCarrier().toLowerCase())) ||
                    (services != null && !services.contains(rate.getService().toLowerCase()))) {
                continue;
            }

            if (lowestRate == null || rate.getRate() < lowestRate.getRate()) {
                lowestRate = rate;
            }
        }

        if (lowestRate == null) {
            throw new FilteringError(String.format(
                    Constants.ErrorMessages.NO_OBJECT_FOUND, "rates"));
        }

        return lowestRate;
    }

    /**
     * Validate a webhook by comparing the HMAC signature header sent from EasyPost
     * to your shared secret.
     * If the signatures do not match, an error will be raised signifying
     * the webhook either did not originate from EasyPost or the secrets do not
     * match.
     * If the signatures do match, the `event_body` will be returned as JSON.
     *
     * @param eventBody     Data to validate
     * @param headers       Headers received from the webhook
     * @param webhookSecret Shared secret to use in validation
     * @return JSON string of the event body if the signatures match, otherwise an
     *         error will be raised.
     * @throws EasyPostException when the request fails.
     */
    public static Event validateWebhook(byte[] eventBody, Map<String, Object> headers, String webhookSecret)
            throws EasyPostException {

        String providedSignature = null;
        try {
            providedSignature = headers.get("X-Hmac-Signature").toString();
        } catch (NullPointerException ignored) { // catch error raised if header key doesn't exist
        }

        if (providedSignature != null) {
            String calculatedDigest = Cryptography.toHMACSHA256HexDigest(eventBody, webhookSecret,
                    Normalizer.Form.NFKD);
            String calculatedSignature = "hmac-sha256-hex=" + calculatedDigest;

            if (Cryptography.signaturesMatch(providedSignature, calculatedSignature)) {
                // Serialize data into a JSON string, then into an Event object
                String json = new String(eventBody, StandardCharsets.UTF_8);
                return Constants.Http.GSON.fromJson(json, Event.class);
            } else {
                throw new SignatureVerificationError(Constants.ErrorMessages.WEBHOOK_DOES_NOT_MATCH);
            }
        } else {
            throw new SignatureVerificationError(Constants.ErrorMessages.INVALID_WEBHOOK_SIGNATURE);
        }
    }

    /**
     * Get the lowest Smartrate from a list of Smartrates.
     *
     * @param smartrates       List of Smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     * @deprecated Use {@link #findLowestSmartrate(List, int, SmartrateAccuracy)} instead.
     * @InlineMe Deprecated: v5.5.0 - v7.0.0
     */
    @Deprecated
    public static SmartRate getLowestSmartRate(final List<SmartRate> smartrates, int deliveryDay,
            String deliveryAccuracy) throws EasyPostException {
        return findLowestSmartrate(smartrates, deliveryDay, SmartrateAccuracy.getByKeyName(deliveryAccuracy));
    }

    /**
     * Find the lowest Smartrate from a list of Smartrates.
     *
     * @param smartrates       List of Smartrates to filter from.
     * @param deliveryDay      Delivery days restriction to use when filtering.
     * @param deliveryAccuracy Delivery days accuracy restriction to use when
     *                         filtering.
     * @return lowest Smartrate object
     * @throws EasyPostException when the request fails.
     */
    public static SmartRate findLowestSmartrate(final List<SmartRate> smartrates, int deliveryDay,
            SmartrateAccuracy deliveryAccuracy) throws EasyPostException {
        SmartRate lowestSmartrate = null;

        for (SmartRate rate : smartrates) {
            int smartrateDeliveryDay = rate.getTimeInTransit().getBySmartrateAccuracy(deliveryAccuracy);

            if (smartrateDeliveryDay > deliveryDay) {
                continue;
            } else if (lowestSmartrate == null || rate.getRate() < lowestSmartrate.getRate()) {
                lowestSmartrate = rate;
            }
        }

        if (lowestSmartrate == null) {
            throw new FilteringError(String.format(Constants.ErrorMessages.NO_OBJECT_FOUND, "rate"));
        }

        return lowestSmartrate;
    }
}
