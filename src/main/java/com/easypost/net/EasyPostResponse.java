/**
 * EasyPostResponse.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.net;

public class EasyPostResponse {

    public int responseCode;
    public String responseBody;

    public EasyPostResponse(final int responseCode, final String responseBody) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;

        // System.out.println(this.responseBody);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(final int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(final String responseBody) {
        this.responseBody = responseBody;
    }
}
