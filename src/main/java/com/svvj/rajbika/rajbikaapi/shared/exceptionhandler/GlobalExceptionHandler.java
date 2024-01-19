package com.svvj.rajbika.rajbikaapi.shared.exceptionhandler;

import com.svvj.rajbika.rajbikaapi.category.exception.CategoryNotFoundException;
import com.svvj.rajbika.rajbikaapi.category.exception.DuplicationCategoryNameException;
import com.svvj.rajbika.rajbikaapi.products.exception.ProductNotFoundException;
import com.svvj.rajbika.rajbikaapi.shared.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleCreateUserException(ProductNotFoundException ex) {
        log.info("Is it coming here?");
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("Error", ex.getMessage());
        return ResponseHandler.responseBuilder("Error fetching product.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicationCategoryNameException.class)
    public ResponseEntity<Object> handleDuplicateCategoryException(DuplicationCategoryNameException ex) {
        log.error("Duplicate category found");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        log.error("category not found");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("category not found");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return ResponseHandler.responseBuilder(ex.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errorMap);
    }
}
