package com.talataa.movies.controller;

import com.talataa.movies.dto.PlayListDTO;
import com.talataa.movies.dto.PlayListProps;
import com.talataa.movies.services.PlayListService;
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
@RequestMapping("/api/v1/play-list")
public class PlayListController {

    @Autowired
    PlayListService playListService;

    @Operation(summary = "Generate a play list")
    @PutMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(description = "The ID of your list", example = "{\"listId\": 8080}"))})
    public ResponseEntity<Map<String, Object>> createPlayList(@RequestHeader("sessionId") String sessionId, @RequestBody PlayListProps props) {
        Map<String, Object> response = new HashMap<>();
        Integer listId = playListService.createList(sessionId, props);
        response.put("listId", listId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a play list by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayListDTO> playList(@PathVariable Integer id, @RequestParam(defaultValue = "1") Integer page) {
        PlayListDTO res = playListService.playListByPage(id, page);
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Add movie to play list by ids")
    @PutMapping(path = "/{id}/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> addMovieToPlayList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id, @PathVariable Integer movieId) {
        Map<String, String> response = new HashMap<>();
        playListService.addMovieToList(sessionId, id, movieId);
        response.put("message", "Success: Add movie to play list");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove movie to play list by ids")
    @DeleteMapping(path = "/{id}/movies/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> removeMovieToPlayList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id, @PathVariable Integer movieId) {
        Map<String, String> response = new HashMap<>();
        playListService.removeMovieToList(sessionId, id, movieId);
        response.put("message", "Success: Remove movie to play list");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Clear movies to play list by id")
    @DeleteMapping(path = "/{id}/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> clearPlayList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        playListService.clearMoviesList(sessionId, id);
        response.put("message", "Success: Clear movies to play list");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a play list by id")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json", schema = @Schema(example = "{\"message\": \"Success message\"}"))})
    public ResponseEntity<Map<String, String>> deletePlayList(@RequestHeader("sessionId") String sessionId, @PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        playListService.deleteMoviesList(sessionId, id);
        response.put("message", "Success: Delete play list");
        return ResponseEntity.ok(response);
    }

}
