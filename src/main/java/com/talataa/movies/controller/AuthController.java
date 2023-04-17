package com.talataa.movies.controller;

import com.talataa.movies.dto.SessionDTO;
import com.talataa.movies.dto.TokenDTO;
import com.talataa.movies.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Operation(summary = "Generate a authentication token for login")
    @PutMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDTO> generateToken() {
        TokenDTO res = authService.generateToken();
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Generate a session")
    @PutMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SessionDTO> logIn(@RequestHeader("token") String token) {
        SessionDTO res = authService.logIn(token);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Close a session")
    @PostMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> logOut(@RequestHeader("sessionId") String sessionId) {
        Map<String, String> response = new HashMap<>();
        authService.logOut(sessionId);
        response.put("message", "Success logout");
        return ResponseEntity.ok(response);
    }

}
