package com.tekion.gameofcricket.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private ApplicationContext applicationContext;

    @ExceptionHandler(InvalidObjectIdException.class)
    public ResponseEntity<ApiResponse> handleInvalidObjectIdException(InvalidObjectIdException exception) {
        exception.printStackTrace();
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.FAILED);
        response.setMessage(exception.getTitle());
        return ResponseEntity.badRequest().body(response);
    }
}
