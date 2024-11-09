package com.JJsCarRent.models.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericResponse {

    private boolean status;
    private String message;

    public static GenericResponse ok(boolean status, String message) {
        return new GenericResponse(status, message);
    }
}
