/**
 * EasyPostResponse.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.net;

public class EasyPostResponse {

    private int responseCode;
    private String responseBody;

    /**
     * Constructor.
     *
     * @param responseCode HTTP response code
     * @param responseBody HTTP response body
     */
    public EasyPostResponse(final int responseCode, final String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    /**
     * Get HTTP response code.
     *
     * @return HTTP response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Set HTTP response code.
     *
     * @param responseCode HTTP response code
     */
    public void setResponseCode(final int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Get HTTP response body.
     *
     * @return HTTP response body
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * Set HTTP response body.
     *
     * @param responseBody HTTP response body
     */
    public void setResponseBody(final String responseBody) {
        this.responseBody = responseBody;
    }
}
