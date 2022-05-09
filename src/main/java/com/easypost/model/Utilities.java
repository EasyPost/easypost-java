package com.easypost.model;

import java.util.List;

import com.easypost.exception.EasyPostException;

public final class Utilities {
    private Utilities() {
        // Do not instantiate this class.
        throw new IllegalStateException("Cannot be instantiated");
     }
    /**
     * Get the lowest rate from a list of rates.
     *
     * @param rates the list of rates.
     * @param carriers the carriers to use in the query.
     * @param services the services to use in the query.
     * @return Rate object
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
}
