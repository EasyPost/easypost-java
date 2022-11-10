package com.easypost.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter // Setter is need for custom Error Deserializer.
public final class Error {
    private String message;
    private String code;
    private List<Error> errors;
    private String suggestion;
    private String field;
}
