package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.CarrierAccount;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrierAccountService {
    private final EasyPostClient client;

    /**
     * CarrierAccountService constructor.
     *
     * @param client The client object.
     */
    CarrierAccountService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a carrier account.
     *
     * @param params Map of parameters to create.
     * @return created CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public CarrierAccount create(final Map<String, Object> params) throws EasyPostException {
        String type = (String) params.get("type");
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put(selectTopLayerKey(type), params);
        String endpoint = selectCarrierAccountCreationEndpoint(type);

        return Requestor.request(RequestMethod.POST, endpoint, wrappedParams, CarrierAccount.class, client);
    }

    /**
     * Retrieve a carrier account from the API.
     *
     * @param id id of the carrier account.
     * @return CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public CarrierAccount retrieve(final String id) throws EasyPostException {
        String endpoint = "carrier_accounts/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, CarrierAccount.class, client);
    }

    /**
     * List all carrier accounts.
     *
     * @return List of CarrierAccount objects.
     * @throws EasyPostException when the request fails.
     */
    public List<CarrierAccount> all() throws EasyPostException {
        return all(null);
    }

    /**
     * List all carrier accounts.
     *
     * @param params filters to apply to the list.
     * @return List of CarrierAccount objects.
     * @throws EasyPostException when the request fails.
     */
    public List<CarrierAccount> all(final Map<String, Object> params) throws EasyPostException {
        String endpoint = "carrier_accounts";

        CarrierAccount[] response =
                Requestor.request(RequestMethod.GET, endpoint, params, CarrierAccount[].class, client);

        return Arrays.asList(response);
    }

    /**
     * Update this carrier account.
     *
     * @param id     The ID of carrier account
     * @param params parameters to update.
     * @return updated CarrierAccount object.
     * @throws EasyPostException when the request fails.
     */
    public CarrierAccount update(String id, final Map<String, Object> params) throws EasyPostException {
        CarrierAccount carrierAccount = this.retrieve(id);
        String type = (String) carrierAccount.getType();
        Map<String, Object> wrappedParams = new HashMap<String, Object>();
        wrappedParams.put(selectTopLayerKey(type), params);

        String endpoint = (Constants.UpsAccountTypes.CARRIER_TYPES_WITH_CUSTOM_WORKFLOW.contains(type) 
                  ? "ups_oauth_registrations/"
                  : "carrier_accounts/") + id;

        return Requestor.request(RequestMethod.PUT, endpoint, wrappedParams, CarrierAccount.class,
                client);
    }


    /**
     * Delete this carrier account.
     *
     * @param id The ID of carrier account.
     * @throws EasyPostException when the request fails.
     */
    public void delete(String id) throws EasyPostException {
        String endpoint = "carrier_accounts/" + id;

        Requestor.request(RequestMethod.DELETE, endpoint, null, CarrierAccount.class, client);
    }

    /**
     * Select the endpoint for the carrier account creation request based on the
     * carrier type.
     *
     * @param carrierAccountType The type of carrier account to create.
     * @return The endpoint for the carrier account creation request.
     */
    private static String selectCarrierAccountCreationEndpoint(final String carrierAccountType) {
        if (Constants.CarrierAccountTypes.CARRIER_TYPES_WITH_CUSTOM_WORKFLOW.contains(carrierAccountType)) {
            return "carrier_accounts/register";
        } else if (carrierAccountType.equals("UpsAccount")) {
            return "ups_oauth_registrations";
        } else {
            return "carrier_accounts";
        }
    }

    /**
     * Select the top-layer key for the carrier account creation/update request based on the
     * carrier type.
     *
     * @param carrierAccountType The type of carrier account to create.
     * @return The top-layer key for the carrier account creation/update request.
     */
    private static String selectTopLayerKey(final String carrierAccountType) throws EasyPostException {
        if (carrierAccountType == null) {
            throw new MissingParameterError(
                    String.format(Constants.ErrorMessages.MISSING_REQUIRED_PARAMETER, "carrier account type"));
        }

        return Constants.UpsAccountTypes.CARRIER_TYPES_WITH_CUSTOM_WORKFLOW.contains(carrierAccountType)
            ? "ups_oauth_registrations"
            : "carrier_account";
    }
}
