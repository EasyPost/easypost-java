package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public class RecommendShipDateResponse {
    private List<RecommendShipDateForShipmentResult> rates;
}
