package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class PickupCollection extends EasyPostResource {
    private List<Pickup> pickups;
    private Boolean hasMore;
}
