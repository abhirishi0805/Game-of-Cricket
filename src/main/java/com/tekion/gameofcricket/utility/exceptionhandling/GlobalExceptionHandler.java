package com.tekion.gameofcricket.utility.exceptionhandling;

import com.mongodb.MongoWriteException;
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

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
        exception.printStackTrace();
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.FAILED);
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ApiResponse> handleInvalidDateException(InvalidDateException exception) {
        exception.printStackTrace();
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        response.setStatus(ResponseStatus.FAILED);
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<ApiResponse> handleMongoWriteException(MongoWriteException exception) {
        exception.printStackTrace();
        ApiResponse response = applicationContext.getBean(ApiResponse.class);
        if (exception.getCode() == 11000) {
            response.setStatus(ResponseStatus.FAILED);
            response.setMessage("This name has already been used. Try something different");
        }
        return ResponseEntity.ok().body(response);
    }
}
