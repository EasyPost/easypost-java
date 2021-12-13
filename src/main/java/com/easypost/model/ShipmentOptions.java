/**
 * ShipmentOptions.java
 * This file is a part of EasyPost API SDK.
 * (c) 2021 EasyPost
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.easypost.model;

public final class ShipmentOptions {
    String smartpostHub;
    String smartpostManifest;

    public String getSmartpostHub() {
        return smartpostHub;
    }

    public void setSmartpostHub(final String smartpostHub) {
        this.smartpostHub = smartpostHub;
    }

    public String getSmartpostManifest() {
        return smartpostManifest;
    }

    public void setSmartpostManifest(final String smartpostManifest) {
        this.smartpostManifest = smartpostManifest;
    }
}
