package com.svvj.rajbika.rajbikaapi.shared.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Integer statusCode, Object responseObject) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status",httpStatus);
        response.put("data", responseObject);
        response.put("statusCode", statusCode);
        return new ResponseEntity<>(response, httpStatus);
    }
}
