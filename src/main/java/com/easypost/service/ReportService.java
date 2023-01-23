package com.easypost.service;

import com.easypost.Constants;
import com.easypost.exception.API.EncodingError;
import com.easypost.exception.EasyPostException;
import com.easypost.exception.General.MissingParameterError;
import com.easypost.http.Requestor;
import com.easypost.http.Requestor.RequestMethod;
import com.easypost.model.Report;
import com.easypost.model.ReportCollection;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ReportService {
    private final EasyPostClient client;

    /**
     * ReportService constructor.
     *
     * @param client The client object.
     */
    ReportService(EasyPostClient client) {
        this.client = client;
    }

    /**
     * Create a Report from a map of parameters.
     *
     * @param params a map of parameters.
     * @return Report object.
     * @throws EncodingError         if the parameters cannot be encoded.
     * @throws MissingParameterError if required parameters are missing.
     * @throws EasyPostException     when the request fails.
     */
    public Report create(final Map<String, Object> params) throws EasyPostException {
        if (params.containsKey("type")) {
            String type = (String) params.get("type");
            HashMap<String, Object> paramsWithoutType = new HashMap<>(params);
            paramsWithoutType.remove("type");
            String endpoint = reportURL(type);
            return Requestor.request(RequestMethod.POST, endpoint, paramsWithoutType, Report.class, client);
        } else {
            throw new MissingParameterError("type");
        }
    }

    /**
     * Generate a report URL.
     *
     * @param type the type of report to generate.
     * @return the URL to generate the report.
     * @throws EncodingError when the request cannot be encoded properly.
     */
    protected String reportURL(final String type) throws EasyPostException {
        try {
            String urlType = URLEncoder.encode(type, "UTF-8").toLowerCase();
            return "reports/" + urlType + "/";  // Yes, the trailing backslash matters, it doesn't work otherwise.
        } catch (java.io.UnsupportedEncodingException e) {
            throw new EncodingError(String.format(Constants.ErrorMessages.ENCODED_ERROR, "report type"), e);
        }
    }

    /**
     * Retrieve a Report from the API.
     *
     * @param id the ID of the Report to retrieve.
     * @return Report object.
     * @throws EasyPostException when the request fails.
     */
    public Report retrieve(final String id) throws EasyPostException {
        String endpoint = "reports/" + id;

        return Requestor.request(RequestMethod.GET, endpoint, null, Report.class, client);
    }

    /**
     * Get a list of Reports from the API.
     *
     * @param params a map of parameters.
     * @return ReportCollection object.
     * @throws EasyPostException when the request fails.
     */
    public ReportCollection all(final Map<String, Object> params) throws EasyPostException {
        String type = (String) params.get("type");
        String endpoint = reportURL(type);

        return Requestor.request(RequestMethod.GET, endpoint, params, ReportCollection.class, client);
    }
}
