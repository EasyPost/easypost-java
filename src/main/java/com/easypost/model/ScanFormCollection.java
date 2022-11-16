package com.easypost.model;

import java.util.List;
import lombok.Getter;

@Getter
public final class ScanFormCollection extends EasyPostResource {
    private List<ScanForm> scanForms;
    private Boolean hasMore;
}
