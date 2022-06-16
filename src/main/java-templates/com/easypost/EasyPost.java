package com.easypost;

import com.easypost.easyvcr.VCR;

public abstract class EasyPost {
    public static final String VERSION = "${project.version}";
    public static String API_BASE = "https://api.easypost.com/v2";
    public static String BETA_API_BASE = "https://api.easypost.com/beta";
    public static String apiKey;
    public static String apiVersion;
    public static int readTimeout;

    /**
     * Set a VCR to be used for all HTTP requests.
     * <p>
     * NOTE: This is meant for unit testing purposes only. Do not use in production.
     */
    public static VCR _vcr = null;
}
