package com.tekion.gameofcricket.utility.exception;

import com.tekion.gameofcricket.responsebody.GenericResponseDto;
import com.tekion.gameofcricket.utility.enums.ResponseStatus;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * This class handles all the thrown exceptions
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public final class GlobalExceptionHandler {

    @ExceptionHandler(InvalidObjectIdException.class)
    public ResponseEntity<GenericResponseDto> handleInvalidObjectIdException(InvalidObjectIdException exception) {
        exception.printStackTrace();
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.FAILED)
                                                        .message(exception.getTitle()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GenericResponseDto> handleNoSuchElementException(NoSuchElementException exception) {
        exception.printStackTrace();
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.SUCCESS)
                                                        .message(exception.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<GenericResponseDto> handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
        exception.printStackTrace();
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.FAILED)
                                                        .message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<GenericResponseDto> handleInvalidDateException(InvalidDateException exception) {
        exception.printStackTrace();
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.FAILED)
                                                        .message(exception.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<GenericResponseDto> handleDuplicateKeyException(DuplicateKeyException exception) {
        exception.printStackTrace();
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.FAILED).message(
                "This name has already been used. Try something different").build();
        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponseDto> handleRemainingExceptions(RuntimeException exception) {
        exception.printStackTrace();
        GenericResponseDto response = GenericResponseDto.builder().status(ResponseStatus.FAILED)
                                                        .message("Some error occurred!").build();
        return ResponseEntity.internalServerError().body(response);
    }
}
