package com.tekion.gameofcricket.utility.exceptionhandling;

import com.mongodb.MongoWriteException;
import com.tekion.gameofcricket.utility.ApiResponse;
import com.tekion.gameofcricket.utility.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * This class handles all the thrown exceptions
 */
@RestControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler(InvalidObjectIdException.class)
    public ResponseEntity<ApiResponse> handleInvalidObjectIdException(InvalidObjectIdException exception) {
        exception.printStackTrace();
        ApiResponse response = ApiResponse.builder().status(ResponseStatus.FAILED).message(exception.getTitle())
                                          .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNoSuchElementException(NoSuchElementException exception) {
        exception.printStackTrace();
        ApiResponse response = ApiResponse.builder().status(ResponseStatus.SUCCESS).message(exception.getMessage())
                                          .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<ApiResponse> handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
        exception.printStackTrace();
        ApiResponse response = ApiResponse.builder().status(ResponseStatus.FAILED).message(exception.getMessage())
                                          .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ApiResponse> handleInvalidDateException(InvalidDateException exception) {
        exception.printStackTrace();
        ApiResponse response = ApiResponse.builder().status(ResponseStatus.FAILED).message(exception.getMessage())
                                          .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<ApiResponse> handleMongoWriteException(MongoWriteException exception) {
        exception.printStackTrace();
        ApiResponse response = ApiResponse.builder().status(ResponseStatus.FAILED).build();
        if (exception.getCode() == 11000) {
            response.setMessage("This name has already been used. Try something different");
        }
        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleUnexpectedExceptions(Exception exception) {
        exception.printStackTrace();
        ApiResponse response = ApiResponse.builder().status(ResponseStatus.FAILED).message(exception.getMessage())
                                          .build();
        return ResponseEntity.ok().body(response);
    }
}
