package com.dquinones.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Global excepction handler for App.
 *
 * This class handles exceptions thrown by the application and returns appropriate Http resposnes.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResponseStatusException and NoSuchElementException
     *
     * @param ex exception to handle
     * @return ResponseEntity containg the error response
     */
    @ExceptionHandler({ResponseStatusException.class, NoSuchElementException.class})
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("cause", ex.getReason());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

}
