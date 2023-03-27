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
        return ResponseEntity.badRequest().body(new GenericResponseDto(ResponseStatus.FAILED, exception.getTitle()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GenericResponseDto> handleNoSuchElementException(NoSuchElementException exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(new GenericResponseDto(ResponseStatus.SUCCESS, exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<GenericResponseDto> handleInvalidRequestBodyException(InvalidRequestBodyException exception) {
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(new GenericResponseDto(ResponseStatus.FAILED, exception.getMessage()));
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<GenericResponseDto> handleInvalidDateException(InvalidDateException exception) {
        exception.printStackTrace();
        return ResponseEntity.badRequest().body(new GenericResponseDto(ResponseStatus.FAILED, exception.getMessage()));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<GenericResponseDto> handleDuplicateKeyException(DuplicateKeyException exception) {
        exception.printStackTrace();
        return ResponseEntity.ok().body(new GenericResponseDto(ResponseStatus.FAILED,
                "This name has already been used. Try something different"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponseDto> handleRemainingExceptions(RuntimeException exception) {
        exception.printStackTrace();
        return ResponseEntity.internalServerError()
                             .body(new GenericResponseDto(ResponseStatus.FAILED, "Some error occurred!"));
    }
}
