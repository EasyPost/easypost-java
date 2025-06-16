package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class LumaInfo {
    private List<AiResults> aiResults;
    private Integer matchingRuleIdx;
    private String rulesetDescription;
    private Rate lumaSelectedRate;
}
