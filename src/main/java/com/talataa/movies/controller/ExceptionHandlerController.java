package com.talataa.movies.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Error message\"}"))})
    public ResponseEntity<Map<String, String>> handleMissingHeader(MissingRequestHeaderException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Missing header: " + ex.getHeaderName());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ApiResponse(responseCode = "401", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Error message\"}"))})
    public ResponseEntity<Map<String, String>> handleNotAuthorized(NotAuthorizedException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "You do not have permission to access this resource: " + ex.getMessage());
        log.error("You do not have permission to access this resource: ".concat(ex.getMessage()), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Error message\"}"))})
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Resource not found: " + ex.getMessage());
        log.error("Resource not found: ".concat(ex.getMessage()), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponse(responseCode = "500", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Error message\"}"))})
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Internal error: " + ex.getMessage());
        log.error("Internal error: ".concat(ex.getMessage()), ex);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

}
