package com.ReservaVehiculos.models.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class HttpGenericException extends RuntimeException{


    private final HttpStatus httpStatus;

    public HttpGenericException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Map<String, Object> getErrorData() {
        return Map.of(
                "type", "error",
                "message", getMessage());
    }

}
