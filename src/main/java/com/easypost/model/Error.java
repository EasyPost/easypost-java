package com.easypost.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public final class Error {
    private @Setter String message;
    private @Setter String code;
    private List<Error> errors;
    private String suggestion;
    private String field;
}
