package com.easypost.model;

import com.easypost.exception.EasyPostException;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public final class Utilities {
    private Utilities() {
        // Do not instantiate this class.
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
     * Get the lowest rate from a list of rates.
     *
     * @param rates    the list of rates.
     * @param carriers the carriers to use in the filter.
     * @param services the services to use in the filter.
     * @return lowest Rate object
     * @throws EasyPostException when the request fails.
     */
    public static Rate getLowestObjectRate(List<Rate> rates, List<String> carriers, List<String> services)
            throws EasyPostException {
        Rate lowestRate = null;

        if (carriers != null) {
            for (int i = 0; i < carriers.size(); i++) {
                carriers.set(i, carriers.get(i).toLowerCase());
            }
        }

        if (services != null) {
            for (int i = 0; i < services.size(); i++) {
                services.set(i, services.get(i).toLowerCase());
            }
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
            throw new EasyPostException("Unable to find lowest rate matching required criteria.");
        }

        return lowestRate;
    }

    /**
     * Create Encoded URL from a Map.
     *
     * @param params    Map of parameters to be encoded.
     * @param parentKey Parent key in the encoded URL.
     * @return Encoded URL for Stripe API call.
     * @throws Exception
     */
    public static String getEncodedURL(Map<String, String> params, String parentKey) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    result.append("&");
                }

                result.append(URLEncoder.encode(parentKey + "[" + entry.getKey() + "]", "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong during the URL encoding.");
        }

        return result.toString();
    }
}
