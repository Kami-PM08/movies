package com.talataa.movies.controller;

import com.talataa.movies.dto.MoviesListDTO;
import com.talataa.movies.entities.CreateMoviesListProps;
import com.talataa.movies.services.MoviesListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/list")
public class MoviesListController {

    @Autowired
    MoviesListService moviesListService;

    @Operation(summary = "Generate a movies list")
    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(description = "The ID of your list", example = "{\"listId\": 8080}"))})
    public ResponseEntity<Map<String, Object>> createList(@RequestHeader("sessionId") String sessionId, @RequestBody CreateMoviesListProps props) {
        Map<String, Object> response = new HashMap<>();
        Integer listId = moviesListService.createList(sessionId, props);
        response.put("listId", listId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a movies list by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MoviesListDTO> moviesList(@PathVariable Integer id, @RequestParam(defaultValue = "1") Integer page) {
        MoviesListDTO res = moviesListService.moviesListByPage(id, page);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Add movie to movies list by ids")
    @PutMapping(path = "/{id}/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> addMovieToMoviesList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id, @PathVariable Integer movieId) {
        Map<String, String> response = new HashMap<>();
        moviesListService.addMovieToList(sessionId, id, movieId);
        response.put("message", "Success: Add movie to movies list");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove movie to movies list by ids")
    @DeleteMapping(path = "/{id}/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> removeMovieToMoviesList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id, @PathVariable Integer movieId) {
        Map<String, String> response = new HashMap<>();
        moviesListService.removeMovieToList(sessionId, id, movieId);
        response.put("message", "Success: Remove movie to movies list");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Clear movies to movies list by id")
    @DeleteMapping(path = "/{id}/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> clearMoviesList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        moviesListService.clearMoviesList(sessionId, id);
        response.put("message", "Success: Clear movies to movies list");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a movies list by id")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> deleteMoviesList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        moviesListService.deleteMoviesList(sessionId, id);
        response.put("message", "Success: Delete movies list");
        return ResponseEntity.ok(response);
    }

}
