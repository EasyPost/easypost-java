/**
 * EasyPostResponse.java
 * This file is a part of EasyPost API SDK.
 * (c) 2022 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.easypost.http;

import lombok.Getter;

@Getter
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
}
