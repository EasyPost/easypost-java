package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class InsuranceCollection extends EasyPostResource {
    private List<Insurance> insurances;
    private Boolean hasMore;
}
