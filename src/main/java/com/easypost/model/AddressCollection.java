package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class AddressCollection extends EasyPostResource {
    private List<Address> addresses;
    private Boolean hasMore;
}
