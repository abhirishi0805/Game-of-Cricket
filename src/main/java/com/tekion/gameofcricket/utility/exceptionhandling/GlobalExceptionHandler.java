package com.tekion.gameofcricket.utility.exceptionhandling;

import com.tekion.gameofcricket.utility.ApiResponse;
import com.tekion.gameofcricket.utility.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

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

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNoSuchElementException(NoSuchElementException exception) {
        exception.printStackTrace();
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
